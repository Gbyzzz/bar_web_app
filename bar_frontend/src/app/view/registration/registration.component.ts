import {Component, Injectable, OnInit} from '@angular/core';
import {AbstractControl, UntypedFormBuilder, FormGroup, Validators} from "@angular/forms";
import Validation from "../../utils/validation";
import {AuthService} from "../../service/auth/auth.service";
import {UserServiceImpl} from "../../service/entity/impl/UserServiceImpl";
import {Router} from "@angular/router";
import {TokenStorageService} from "../../service/auth/token-storage.service";
import {HeaderComponent} from "../header/header.component";

@Component({
    selector: 'app-registration',
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.css'],
    standalone: false
})
export class RegistrationComponent implements OnInit {

  registrationForm:  any = {
    username: null,
    email: null,
    password: null
  };
  emailPattern = new RegExp("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$");
  submitted = false;
  errorMessage = '';
  isUsernameAvailable: boolean = undefined;
  isUsernameValid: boolean = undefined;
  isEmailAvailable: boolean = undefined;
  isEmailValid: boolean = undefined;
  isPasswordValid: boolean = undefined;
  isConfirmPasswordMatch: boolean = undefined;


  constructor(private formBuilder: UntypedFormBuilder,
              private authService: AuthService,
              private userService: UserServiceImpl,
              private router: Router,
              private tokenStorage: TokenStorageService,
              private headerComponent: HeaderComponent) {
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
    const username = this.registrationForm.get('username').value;
    const email = this.registrationForm.get('email').value;
    const password = this.registrationForm.get('password').value;
    this.authService.register(username,email,password).subscribe(data => {
      this.headerComponent.loggedInEvent.emit(true);
      this.headerComponent.login(data);
    });
  }

  onUsernameChange(event) {

    if(event.target.value.trim().length <= 20 && event.target.value.trim().length >= 6){
      this.isUsernameValid = true;
    } else {
      this.isUsernameValid = false;
    }

    this.userService.isUsernameAvailable(event.target.value, '', '')
      .subscribe(res => {
      this.isUsernameAvailable = res;
    });
  }

  onEmailChange(event) {
    this.isEmailValid = this.emailPattern.test(event.target.value);
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
    if(event.target.value == this.registrationForm.get('password').value){
      this.isConfirmPasswordMatch = true;
    } else {
      this.isConfirmPasswordMatch = false;
    }
  }
}
