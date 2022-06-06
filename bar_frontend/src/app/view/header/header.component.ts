import { Component, OnInit } from '@angular/core';
import {TranslocoService} from "@ngneat/transloco";
import {EditCocktailDialogComponent} from "../dialog/edit-cocktail-dialog/edit-cocktail-dialog.component";
import {DialogAction} from "../dialog/DialogResult";
import {Cocktail} from "../../model/Cocktail";
import {MatDialog} from "@angular/material/dialog";
import {CocktailServiceImpl} from "../../service/entity/impl/CocktailServiceImpl";
import {Role} from "../../model/User";
import {AuthService} from "../../service/auth/auth.service";
import {TokenStorageService} from "../../service/auth/token-storage.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  role: Role;
  targetUsername: string = '';

  cocktail: Cocktail = new Cocktail();
  siteLanguage = 'English';
  languageList = [
    { code: 'en', label: 'English' },
    { code: 'ru', label: 'Русский' }
  ];
  constructor(private dialog: MatDialog,
              private service: TranslocoService,
              private cocktailService: CocktailServiceImpl,
              private authService: AuthService,
              private tokenStorage: TokenStorageService) { }
  changeSiteLanguage(language: string): void {
    this.service.setActiveLang(language);
    this.siteLanguage = this.languageList.find(f => f.code === language).label;
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.role = this.tokenStorage.getUser().role;
      this.targetUsername = this.tokenStorage.getUser().username;
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;

    this.authService.login(username, password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token);
        console.log(data);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.role = this.tokenStorage.getUser().role;
        this.targetUsername = this.tokenStorage.getUser().username;
        console.log(this.role);
        // this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  logout() {
    this.tokenStorage.signOut();
    this.reloadPage();
  }

  reloadPage(): void {
    window.location.reload();
  }

  updateCocktail(cocktail: Cocktail){
    this.cocktailService.update(cocktail).subscribe();
  }

  openAddDialog(): void {


    const dialogRef = this.dialog.open(EditCocktailDialogComponent, {

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
