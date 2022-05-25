import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Ingredient} from "../../../model/Ingredient";
import {DialogAction, DialogResult} from "../DialogResult";
import {Cocktail} from "../../../model/Cocktail";
import {User} from "../../../model/User";

@Component({
  selector: 'app-edit-cocktail-dialog',
  templateUrl: './edit-cocktail-dialog.component.html',
  styleUrls: ['./edit-cocktail-dialog.component.css']
})
export class EditCocktailDialogComponent implements OnInit {

  constructor(
    private dialogRef: MatDialogRef<EditCocktailDialogComponent>, // // для возможности работы с текущим диалог. окном
    @Inject(MAT_DIALOG_DATA) private data: [Cocktail], // данные, которые передаем в текущее диалоговое окно
  ) { }

  cocktail: Cocktail;
  newCocktailName: string;
  newCocktailAuthor: User;
  newUnitOfMeasurement: string;

  ngOnInit(): void {
    this.cocktail = this.data[0];

    // this.newIngredientName = this.ingredient.ingredientName;
    // this.newIngredientAlcohol = this.ingredient.ingredientAlcohol;
    // this.newUnitOfMeasurement = this.ingredient.unitOfMeasurement;

  }

  confirm(): void {

    // this.ingredient.ingredientName = this.newIngredientName;
    // this.ingredient.ingredientAlcohol = this.newIngredientAlcohol;
    // this.ingredient.unitOfMeasurement = this.newUnitOfMeasurement;

    this.dialogRef.close(new DialogResult(DialogAction.SAVE));
  }
}

