import {Component, ElementRef, EventEmitter, Injectable, OnInit, ViewChild} from '@angular/core';
import {TranslocoService} from "@ngneat/transloco";
import {
  EditCocktailDialogComponent
} from "../dialog/edit-cocktail-dialog/edit-cocktail-dialog.component";
import {DialogAction} from "../dialog/DialogResult";
import {Cocktail} from "../../model/Cocktail";
import {MatDialog} from "@angular/material/dialog";
import {CocktailServiceImpl} from "../../service/entity/impl/CocktailServiceImpl";
import {Role} from "../../model/User";
import {AuthService} from "../../service/auth/auth.service";
import {TokenStorageService} from "../../service/auth/token-storage.service";
import {Router} from "@angular/router";
import {LoginSharedService} from "../../service/auth/login-shared.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  loggedInEvent = new EventEmitter<boolean>();

  signInForm: any = {
    username: null,
    password: null
  };
  recoverPasswordForm: any = {
    email: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  role: Role;
  targetUsername: string = '';
  isEmailFound: boolean = undefined;

  cocktail: Cocktail = new Cocktail();
  siteLanguage = 'English';
  languageList = [
    {code: 'en', label: 'English'},
    {code: 'ru', label: 'Русский'}
  ];

  @ViewChild('signInTab', { static: false, read: ElementRef })
  signInTab!: ElementRef<HTMLDivElement>;

  @ViewChild('recoverPasswordTab', { static: false, read: ElementRef })
  recoverPasswordInTab!: ElementRef<HTMLDivElement>;

  constructor(private dialog: MatDialog,
              private service: TranslocoService,
              private cocktailService: CocktailServiceImpl,
              private authService: AuthService,
              private router: Router,
              private tokenStorage: TokenStorageService,
              private sharedService: LoginSharedService) {
  }

  changeSiteLanguage(language: string): void {
    this.service.setActiveLang(language);
    this.siteLanguage = this.languageList
      .find(f => f.code === language)
      .label;
  }

  ngOnInit(): void {
    this.sharedService.eventLoggedSubject.subscribe((loggedIn: boolean) => {
      this.isLoggedIn = loggedIn;
      if (this.isLoggedIn) {
        const button = document.getElementById('close_sign_in');
        button.click();
      }
    });
    this.sharedService.eventUsernameSubject.subscribe((username: string) => {
      this.targetUsername = username;
      // Perform any other necessary actions when userLoggedIn changes
    });
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.role = this.tokenStorage.getUser().role;
      this.targetUsername = this.tokenStorage
        .getUser().username;
    }
  }

  onSubmit(): void {
    const {username, password} = this.signInForm;

    this.authService.login(username, password).subscribe(
      data => {
        this.login(data)
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  login(data) {
    this.tokenStorage.saveToken(data.token);
    this.tokenStorage.saveUser(data.user);
    this.isLoginFailed = false;
    this.isLoggedIn = true;
    this.role = this.tokenStorage.getUser().role;
    this.targetUsername = this.tokenStorage.getUser()
      .username;
    this.sharedService.emitLoggedEvent(true);
    this.sharedService.emitUsernameEvent(this.tokenStorage.getUser()
      .username);


    if (!this.tokenStorage.getUser().enabled) {
      this.router.navigate(['/validate/']);

    }
  }

  logout() {
    this.tokenStorage.signOut();
    this.authService.logout();
    this.reloadPage();
  }

  changeTabToForgetPassword() {
    this.signInTab.nativeElement.classList.remove('active');
    this.signInTab.nativeElement.classList.add('fade');
    this.recoverPasswordInTab.nativeElement.classList.remove('fade');
    this.recoverPasswordInTab.nativeElement.classList.add('active');
  }

  toSignInTab(){
    this.recoverPasswordInTab.nativeElement.classList.remove('active');
    this.recoverPasswordInTab.nativeElement.classList.add('fade');
    this.signInTab.nativeElement.classList.remove('fade');
    this.signInTab.nativeElement.classList.add('active');
  }

  onRecoverPassword(){
    console.log("recover");
    console.log(this.recoverPasswordForm.email);

    this.authService.sendRecoverPasswordEmail(this.recoverPasswordForm.email).subscribe(res =>
    {
      console.log(res);
      this.isEmailFound = res;
      if(this.isEmailFound) {
        const button = document.getElementById('close_sign_in');
        button.click();
      }

    });
  }
  reloadPage(): void {
    window.location.reload();
  }

  updateCocktail(cocktail: Cocktail) {
    this.cocktailService.update(cocktail).subscribe();
  }

  openAddDialog(): void {


    const dialogRef =
      this.dialog.open(EditCocktailDialogComponent,
        {
          data: [this.cocktail],
          autoFocus: false
        });

    dialogRef.afterClosed().subscribe(result => {

      if (!(result)) {
        return;
      }

      if (result.action === DialogAction.SAVE) {
        this.updateCocktail(this.cocktail);
        return;
      }
    });
  }
}

