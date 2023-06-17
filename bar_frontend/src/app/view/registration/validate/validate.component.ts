import {OnInit} from "@angular/core";
import {Code} from "../../../model/registration/Code";
import {TokenStorageService} from "../../../service/auth/token-storage.service";
import {ValidateEmailService} from "../../../service/auth/validate-email.service";
import {
  Component,
  ElementRef,
  isDevMode,
  QueryList,
  ViewChildren,
} from '@angular/core';
import {
  AbstractControl,
  UntypedFormArray,
  UntypedFormControl,
  ValidationErrors,
} from '@angular/forms';
import {Router} from "@angular/router";

@Component({
  selector: 'app-validate',
  templateUrl: './validate.component.html',
  styleUrls: ['./validate.component.css']
})
export class ValidateComponent implements OnInit {

  code: Code = new Code();
  #size = 6;
  inputs = this.getFormArray(this.#size);
  #scheduledFocus: number = null;
  valid: boolean = undefined;

  @ViewChildren('inputEl') inputEls!: QueryList<ElementRef<HTMLInputElement>>;


  constructor(private tokenStorage: TokenStorageService, private router: Router,
              private validateService: ValidateEmailService) {
  }

  ngOnInit(): void {
    this.code.email = this.tokenStorage.getUser().email;
  }

  onChange?: (value: string) => void;
  onTouched?: () => void;


  getFormArray(size: number): UntypedFormArray {
    const arr = [];

    for (let i = 0; i < size; i++) {
      arr.push(new UntypedFormControl(''));
    }

    return new UntypedFormArray(arr);
  }

  writeValue(value: string): void {
    if (isDevMode() && value?.length) {
      throw new Error('Otp input is not supposed to be prefilled with data');
    }

    this.inputs.setValue(new Array(this.#size).fill(''));
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  setDisabledState(isDisabled: boolean): void {
    if (isDisabled) {
      this.inputs.disable();
    } else {
      this.inputs.enable();
    }
  }

  validate(control: AbstractControl): ValidationErrors | null {
    if (!control.value || control.value.length < this.#size) {
      return {
        otpInput: 'Value is incorrect',
      };
    }

    return null;
  }

  handleKeyDown(e: KeyboardEvent, idx: number) {
    if (e.key === 'Backspace' || e.key === 'Delete') {
      if (idx > 0) {
        this.#scheduledFocus = idx - 1;
      }
    }
  }
  handleInput() {
    this.#updateWiredValue();

    if (this.#scheduledFocus != null) {
      this.#focusInput(this.#scheduledFocus);
      this.#scheduledFocus = null;
    }
  }

  handleKeyPress(e: KeyboardEvent, idx: number) {
    const isDigit = /\d/.test(e.key);
    if (e.key === 'v' && e.metaKey) {
      return true;
    }

    if (isDigit && idx + 1 < this.#size) {
      this.#scheduledFocus = idx + 1;
    }

    if (isDigit && this.inputs.controls[idx].value) {
      this.inputs.controls[idx].setValue('');
    }

    return isDigit;
  }

  handlePaste(e: ClipboardEvent, idx: number) {
    e.preventDefault();

    if (idx !== 0) {
      return;
    }

    const pasteData = e.clipboardData?.getData('text');
    const regex = new RegExp(`\\d{${this.#size}}`);

    if (!pasteData || !regex.test(pasteData)) {
      return;
    }

    for (let i = 0; i < pasteData.length; i++) {
      this.inputs.controls[i].setValue(pasteData[i]);
    }

    this.#focusInput(this.inputEls.length - 1);
    this.#updateWiredValue();
    this.onTouched();
  }

  handleFocus(e: FocusEvent) {
    (e.target as HTMLInputElement).select();
  }

  #focusInput(idx: number) {
    setTimeout(() => this.inputEls.get(idx)?.nativeElement.focus());
  }

  #updateWiredValue() {
    setTimeout(() => this.onChange?.(this.inputs.value.join('')));
    if (this.isAllNumbers(this.inputs.value)) {
      this.code.code = parseInt(this.inputs.getRawValue().join(''));
      this.validateService.send(this.code).subscribe(valid => {
        this.valid = valid;
        if (valid) {
          const user = this.tokenStorage.getUser();
          user.enabled = true;
          this.tokenStorage.saveUser(user);
          this.router.navigate(['']);
        }
      });
    }
  }

  isAllNumbers(arr: string[]): boolean {
    return arr.every(value => /\d/.test(value));
  }
}
