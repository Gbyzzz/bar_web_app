import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Ingredient} from "../../model/Ingredient";
import {IngredientServiceImpl} from "../../service/impl/IngredientServiceImpl";
import {EditIngredientDialogComponent} from "../dialog/edit-ingredient-dialog/edit-ingredient-dialog.component";
import {DialogAction} from "../dialog/DialogResult";
import {MatDialog} from "@angular/material/dialog";
import * as punycode from "punycode";

@Component({
  selector: 'app-add-cocktail',
  templateUrl: './add-cocktail.component.html',
  styleUrls: ['./add-cocktail.component.css']
})
export class AddCocktailComponent implements OnInit {

  imageSrc: string;
  selectedIngredient: Ingredient[] = [];
  selectedUnit: string[] = [];
  cocktailForm: FormGroup;
  newIngredient: Ingredient;

  ingredients: Ingredient[];

  constructor(private httpClient: HttpClient,
              private dialog: MatDialog,
              private ingredientService: IngredientServiceImpl,
              private fb: FormBuilder) {

    this.cocktailForm = this.fb.group({
      cocktailName: '',
      cocktailImageFile: '',
      cocktailRecipe: '',
      ingredientsRecipe: this.fb.array([]),
    });

    ingredientService.findAll().subscribe(ingredients =>{
      this.ingredients = ingredients;
    });
  }

  ingredientsRecipe(): FormArray {
    return this.cocktailForm.get("ingredientsRecipe") as FormArray
  }

  newIngredientsRecipe(): FormGroup {
    return this.fb.group({
      ingredientSelect: '',
      quantity: '',
    })
  }

  addIngredientsRecipe() {
    this.selectedIngredient.splice(this.selectedIngredient.length, 0, this.ingredients[0]);
    this.selectedUnit.splice(this.selectedUnit.length,0, this.ingredients[0].unitOfMeasurement);
    console.log(this.selectedIngredient);

    this.ingredientsRecipe().push(this.newIngredientsRecipe());
  }

  removeIngredientsRecipe(i:number) {
    this.ingredientsRecipe().removeAt(i);
  }

  onSelectChange(event, i){
    console.log(event);    console.log(i);
    this.selectedIngredient[i] = event;
    this.selectedUnit[i] = this.selectedIngredient[i].unitOfMeasurement;
    console.log(this.selectedUnit);
  }

  onFileChange(event) {
    const reader = new FileReader();

    if(event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);

      reader.onload = () => {

        this.imageSrc = reader.result as string;

      };
    }
  }
  addIngredient(ingredient: Ingredient){
    this.ingredientService.add(ingredient).subscribe();
  }

  openAddDialog(): void {
    this.newIngredient = new Ingredient('',0,'');

    const dialogRef = this.dialog.open(EditIngredientDialogComponent, {
      data: [this.newIngredient],
      autoFocus: false
    });

    dialogRef.afterClosed().subscribe(result => {

      if (!(result)) {
        return;
      }

      if (result.action === DialogAction.SAVE) {
        console.log(this.newIngredient);
        this.addIngredient(this.newIngredient);

        return;
      }
    });
  }

  ngOnInit(): void {
  }
}
