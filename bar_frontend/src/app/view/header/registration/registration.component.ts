import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import Validation from "../../../utils/validation";
import {AuthService} from "../../../service/auth/auth.service";
import {UserServiceImpl} from "../../../service/entity/impl/UserServiceImpl";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registrationForm:  any = {
    username: null,
    email: null,
    password: null
  };
  submitted = false;
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  isUsernameAvailable: boolean;
  isUsernameValid: boolean;
  isEmailAvailable: boolean;
  isEmailValid: boolean;
  emailPattern = new RegExp("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$");
  isPasswordValid: boolean;
  isConfirmPasswordMatch: boolean;


  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private userService: UserServiceImpl) {
    this.registrationForm = this.formBuilder.group(
      {
        username: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(20)
          ]
        ],
        email: ['', [Validators.required, Validators.email]],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(40)
          ]
        ],
        confirmPassword: ['', Validators.required],
        acceptTerms: [false, Validators.requiredTrue]
      },
      {
        validators: [Validation.match('password', 'confirmPassword')]
      }
    );
  }

  ngOnInit(): void {
  }

  get f(): { [key: string]: AbstractControl } {
    return this.registrationForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    const { username, email, password } = this.registrationForm;

    if (this.registrationForm.invalid) {
      return;
    }

    console.log(JSON.stringify(this.registrationForm.value, null, 2));

    this.authService.register(username, email, password).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  onUsernameChange(event) {

    if(event.target.value.trim().length <= 20 && event.target.value.trim().length >= 6){
      this.isUsernameValid = true;
    } else {
      this.isUsernameValid = false;
    }

    this.userService.isUsernameAvailable(event.target.value, '', '').subscribe(res => {
      this.isUsernameAvailable = res;
    });
  }

  onEmailChange(event) {
    this.isEmailValid = this.emailPattern.test(event.target.value);
    console.log(this.isEmailValid);

    this.userService.isEmailAvailable('', event.target.value, '').subscribe(res => {
      this.isEmailAvailable = res;
    })

  }

  onPasswordChange(event) {
    if(event.target.value.length >= 6){
      this.isPasswordValid = true;
    } else {
      this.isPasswordValid = false;
    }
  }

  onConfirmPasswordChange(event) {
    console.log(event.target.value);
    console.log(this.registrationForm.get('password'));
    if(event.target.value == this.registrationForm.password){
      this.isConfirmPasswordMatch = true;
    } else {
      this.isConfirmPasswordMatch = false;
    }
  }
}
