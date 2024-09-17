import {Component, ElementRef, Injector, ViewChild} from '@angular/core';
import {UntypedFormBuilder, Validators} from "@angular/forms";
import {UserServiceImpl} from "../../../service/entity/impl/UserServiceImpl";
import {AuthService} from "../../../service/auth/auth.service";
import {TokenStorageService} from "../../../service/auth/token-storage.service";
import Validation from "../../../utils/validation";
import {PasswordChange} from "../../../model/registration/PasswordChange";
import {ActivatedRoute, Router} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogAction, DialogResult} from "../DialogResult";

@Component({
  selector: 'app-change-password-dialog',
  templateUrl: './change-password-dialog.component.html',
  styleUrls: ['./change-password-dialog.component.css']
})
export class ChangePasswordDialogComponent {
  changePasswordForm: any = {
    oldPassword: null,
    newPassword: null
  };
  isPasswordValid: boolean;
  isThereACode: boolean = false;
  code: string = '';
  isOldPasswordMatch: boolean;
  isConfirmPasswordMatch: boolean;
  isNewPasswordUniqueToOld: boolean;
  isPasswordChanged: boolean;
  oldPasswordValue: string = '';
  newPasswordValue: string = '';
  confirmPasswordValue: string = '';
  dialogRef: MatDialogRef<any>;

  @ViewChild('closeAlertButton', {static: false, read: ElementRef})
  closeAlertButton!: ElementRef<HTMLButtonElement>;

  constructor(private userService: UserServiceImpl,
              private authService: AuthService,
              private router: Router,
              private injector: Injector,
              private tokenStorage: TokenStorageService,
              private fb: UntypedFormBuilder,
              private route: ActivatedRoute) {
    this.dialogRef = this.injector.get(MatDialogRef, null);
    this.changePasswordForm = this.fb.group(
      {
        oldPassword: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(20)
          ]
        ],
        newPassword: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(40)
          ]
        ],
        confirmPassword: ['', Validators.required]
      },
      {
        validators: [Validation.match('newPassword', 'confirmPassword')]
      }
    );
  }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      this.code = params.get('validate_code');
      if (this.code != '' && this.code != null) {
        this.isThereACode = true;
      }
    });
  }

  onInputChange() {
    this.newPasswordValue = this.changePasswordForm
      .get('newPassword').value;

    this.oldPasswordValue = this.changePasswordForm
      .get('oldPassword').value;

    this.confirmPasswordValue = this.changePasswordForm
      .get('confirmPassword').value;

    if (this.newPasswordValue.length >= 6) {
      this.isPasswordValid = true;
    } else {
      this.isPasswordValid = false;
    }
    if (this.newPasswordValue != this.oldPasswordValue) {
      this.isNewPasswordUniqueToOld = true
    } else {
      this.isNewPasswordUniqueToOld = false
    }

    if (this.confirmPasswordValue == this.newPasswordValue) {
      this.isConfirmPasswordMatch = true;
    } else {
      this.isConfirmPasswordMatch = false;
    }
  }

  onOldPasswordInputChange() {
    console.log("oldPassChange")
    console.log(this.oldPasswordValue);
    this.onInputChange();
    this.authService.checkPassword(
      new PasswordChange(this.tokenStorage
        .getUser().email, this.oldPasswordValue)).subscribe(res => {
      this.isOldPasswordMatch = res;
    });
  }

  onSubmitChangePassword(): void {
    console.log("change pass");
    console.log(this.isThereACode);
    if (this.isThereACode) {
      this.authService.recoverPassword(new PasswordChange('', this.code, this.newPasswordValue)).subscribe(res =>
      {
        if(res) {
          this.router.navigate(['']);
        }
      });
    } else {
      this.authService.changePassword(new PasswordChange(this.tokenStorage
        .getUser().email, this.oldPasswordValue, this.newPasswordValue))
        .subscribe(res => {
          console.log(1);
          if (res) {
            console.log(2);
            this.dialogRef.close(new DialogResult(DialogAction.SAVE));
          }
        });
    }

  }
}
