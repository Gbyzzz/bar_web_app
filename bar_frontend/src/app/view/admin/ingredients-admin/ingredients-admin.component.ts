import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IngredientServiceImpl} from "../../../service/impl/IngredientServiceImpl";
import {MatTableDataSource} from "@angular/material/table";
import {PageEvent} from "@angular/material/paginator";
import {Ingredient} from "../../../model/Ingredient";
import {MatDialog} from "@angular/material/dialog";
import {UserServiceImpl} from "../../../service/impl/UserServiceImpl";
import {User} from "../../../model/User";
import {Sort} from "@angular/material/sort";
import {EditUserDialogComponent} from "../../dialog/edit-user-dialog/edit-user-dialog.component";
import {DialogAction} from "../../dialog/DialogResult";
import {EditIngredientDialogComponent} from "../../dialog/edit-ingredient-dialog/edit-ingredient-dialog.component";

@Component({
  selector: 'app-ingredients-admin',
  templateUrl: './ingredients-admin.component.html',
  styleUrls: ['./ingredients-admin.component.css']
})
export class IngredientsAdminComponent implements OnInit {
  ingredients: Ingredient[];
  sortedData: Ingredient[];
  constructor(private dialog: MatDialog,
              private ingredientService: IngredientServiceImpl) {
    this.ingredientService.findAll().subscribe(ingredients => {
      this.sortedData = ingredients;
      this.ingredients = ingredients;
    });
  }

  ngOnInit(): void {
  }
  updateIngredient(ingredient: Ingredient){
    this.ingredientService.update(ingredient).subscribe();
  }

  sortData(sort: Sort) {
    const data = this.ingredients.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'ingredientId':
          return compare(a.ingredientId, b.ingredientId, isAsc);
        case 'ingredientName':
          return compare(a.ingredientName, b.ingredientName, isAsc);
        case 'ingredientAlcohol':
          return compare(a.ingredientAlcohol, b.ingredientAlcohol, isAsc);
        case 'unitOfMeasurement':
          return compare(a.unitOfMeasurement, b.unitOfMeasurement, isAsc);
        default:
          return 0;
      }
    });
  }

  openEditDialog(ingredient: Ingredient): void {


    const dialogRef = this.dialog.open(EditIngredientDialogComponent, {
      data: [ingredient],
      autoFocus: false
    });

    dialogRef.afterClosed().subscribe(result => {

      if (!(result)) {
        return;
      }

      if (result.action === DialogAction.SAVE) {
        this.updateIngredient(ingredient);
        return;
      }
    });
  }
}

function compare(a: number | string | boolean | Date,
                 b: number | string | boolean | Date, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
