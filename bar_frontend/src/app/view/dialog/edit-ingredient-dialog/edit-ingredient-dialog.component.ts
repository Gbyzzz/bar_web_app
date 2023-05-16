import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogAction, DialogResult} from "../DialogResult";
import {Ingredient} from "../../../model/Ingredient";

@Component({
  selector: 'app-edit-ingredient-dialog',
  templateUrl: './edit-ingredient-dialog.component.html',
  styleUrls: ['./edit-ingredient-dialog.component.css']
})
export class EditIngredientDialogComponent implements OnInit {

  constructor(
    private dialogRef: MatDialogRef<EditIngredientDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: [Ingredient],
  ) { }

  ingredient: Ingredient;
  newIngredientName: string;
  newIngredientAlcohol: number;
  newUnitOfMeasurement: string;

  ngOnInit(): void {
    this.ingredient = this.data[0];

    this.newIngredientName = this.ingredient.ingredientName;
    this.newIngredientAlcohol = this.ingredient.ingredientAlcohol;
    this.newUnitOfMeasurement = this.ingredient.unitOfMeasurement;

  }

  confirm(): void {

    this.ingredient.ingredientName = this.newIngredientName;
    this.ingredient.ingredientAlcohol = this.newIngredientAlcohol;
    this.ingredient.unitOfMeasurement = this.newUnitOfMeasurement;

    this.dialogRef.close(new DialogResult(DialogAction.SAVE));
  }
}
