import { Component, OnInit } from '@angular/core';
import {TranslocoService} from "@ngneat/transloco";
import {EditCocktailDialogComponent} from "../dialog/edit-cocktail-dialog/edit-cocktail-dialog.component";
import {DialogAction} from "../dialog/DialogResult";
import {Cocktail} from "../../model/Cocktail";
import {MatDialog} from "@angular/material/dialog";
import {CocktailServiceImpl} from "../../service/impl/CocktailServiceImpl";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  cocktail: Cocktail = new Cocktail();
  siteLanguage = 'English';
  languageList = [
    { code: 'en', label: 'English' },
    { code: 'ru', label: 'Русский' }
  ];
  constructor(private dialog: MatDialog,
              private service: TranslocoService,
              private cocktailService: CocktailServiceImpl) { }
  changeSiteLanguage(language: string): void {
    this.service.setActiveLang(language);
    this.siteLanguage = this.languageList.find(f => f.code === language).label;
  }

  ngOnInit(): void {
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
