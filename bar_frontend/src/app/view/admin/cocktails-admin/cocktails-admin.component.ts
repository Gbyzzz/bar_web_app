import {Component, OnInit} from '@angular/core';
import {CocktailServiceImpl} from "../../../service/impl/CocktailServiceImpl";
import {MatDialog} from "@angular/material/dialog";
import {Sort} from "@angular/material/sort";
import {DialogAction} from "../../dialog/DialogResult";
import {Cocktail} from "../../../model/Cocktail";
import {EditCocktailDialogComponent} from "../../dialog/edit-cocktail-dialog/edit-cocktail-dialog.component";

@Component({
  selector: 'app-cocktails-admin',
  templateUrl: './cocktails-admin.component.html',
  styleUrls: ['./cocktails-admin.component.css']
})
export class CocktailsAdminComponent implements OnInit {

  cocktails: Cocktail[];
  sortedData: Cocktail[];
  constructor(private dialog: MatDialog,
              private cocktailService: CocktailServiceImpl) {
    this.cocktailService.findAll().subscribe(cocktails => {
      this.sortedData = cocktails;
      this.cocktails = cocktails;
    });
  }

  ngOnInit(): void {
  }
  updateCocktail(cocktail: Cocktail){
    this.cocktailService.update(cocktail).subscribe();
  }

  sortData(sort: Sort) {
    const data = this.cocktails.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'cocktailId':
          return compare(a.cocktailId, b.cocktailId, isAsc);
        case 'cocktailName':
          return compare(a.cocktailName, b.cocktailName, isAsc);
        case 'cocktailAuthor':
          return compare(a.cocktailAuthor.username, b.cocktailAuthor.username, isAsc);
        case 'cocktailRating':
          return compare(a.cocktailRating, b.cocktailRating, isAsc);
        case 'publicationDate':
          return compare(a.publicationDate, b.publicationDate, isAsc);
        case 'approxAlcoholPercentage':
          return compare(a.approxAlcoholPercentage, b.approxAlcoholPercentage, isAsc);
        default:
          return 0;
      }
    });
  }

  openEditDialog(cocktail: Cocktail): void {


    const dialogRef = this.dialog.open(EditCocktailDialogComponent, {
      data: [cocktail],
      autoFocus: false
    });

    dialogRef.afterClosed().subscribe(result => {

      if (!(result)) {
        return;
      }

      if (result.action === DialogAction.SAVE) {
        this.updateCocktail(cocktail);
        return;
      }
    });
  }
}

function compare(a: number | string | boolean | Date,
                 b: number | string | boolean | Date, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
