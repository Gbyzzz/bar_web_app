import {ChangeDetectorRef, Component, Inject, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Ingredient} from "../../../model/Ingredient";
import {IngredientServiceImpl} from "../../../service/impl/IngredientServiceImpl";
import {EditIngredientDialogComponent} from "../edit-ingredient-dialog/edit-ingredient-dialog.component";
import {DialogAction, DialogResult} from "../DialogResult";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {Cocktail} from "../../../model/Cocktail";
import {Image} from "../../../model/Image";
import {Recipe} from "../../../model/Recipe";
import {ImageServiceImpl} from "../../../service/impl/ImageServiceImpl";
import {CocktailServiceImpl} from "../../../service/impl/CocktailServiceImpl";
import {sequence} from "@angular/animations";

@Component({
  selector: 'app-edit-cocktail-dialog',
  templateUrl: './edit-cocktail-dialog.component.html',
  styleUrls: ['./edit-cocktail-dialog.component.css']
})
export class EditCocktailDialogComponent implements OnInit {

  imageSrc: string;
  addIndex: number = 0;
  selectedIngredient: Ingredient[] = [];
  selectedUnit: string[] = [];
  selectedQuantity: number[] = [];
  cocktailForm: FormGroup;
  newIngredient: Ingredient;

  ingredients: Ingredient[];
  targetCocktail: Cocktail;
  newCocktailName: string;
  newCocktailImage: Image;
  newCocktailRecipe: string;
  newRecipe: Recipe[];

  constructor(private httpClient: HttpClient,
              private cdr: ChangeDetectorRef,
              private dialog: MatDialog,
              private ingredientService: IngredientServiceImpl,
              private fb: FormBuilder,
              private dialogRef: MatDialogRef<EditIngredientDialogComponent>,
              @Inject(MAT_DIALOG_DATA) private data: [Cocktail],
              private imageService: ImageServiceImpl,
              private cocktailService: CocktailServiceImpl) {

    dialogRef.updateSize('100%', '100%');

    this.cocktailForm = this.fb.group({
      cocktailName: '',
      cocktailImageFile: '',
      cocktailRecipe: '',
      ingredientsRecipe: this.fb.array([]),
    });

    ingredientService.findAll().subscribe(ingredients => {
      this.ingredients = ingredients;
    });
    this.targetCocktail = data[0];
    this.newCocktailName = this.targetCocktail.cocktailName;
    this.newCocktailImage = this.targetCocktail.cocktailImage;
    this.newCocktailRecipe = this.targetCocktail.cocktailRecipe;
    this.newRecipe = this.targetCocktail.recipes;
    if (this.newRecipe.length > 0) {
      this.newRecipe.forEach(recipe => {
        this.selectedIngredient.splice(this.selectedIngredient.length, 0, recipe.ingredient);
        this.selectedUnit.splice(this.selectedUnit.length, 0, recipe.ingredient.unitOfMeasurement);
        this.selectedQuantity.splice(this.selectedQuantity.length, 0, recipe.quantity);
        this.ingredientsRecipe().insert(this.addIndex, this.newIngredientsRecipe());
        this.addIndex++;
      });
    }
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
    this.selectedUnit.splice(this.selectedUnit.length, 0, this.ingredients[0].unitOfMeasurement);
    this.selectedQuantity.splice(this.selectedQuantity.length, 0, 0);
    this.cdr.detectChanges();
    this.ingredientsRecipe().push(this.newIngredientsRecipe());
  }

  removeIngredientsRecipe(i: number) {
    this.selectedIngredient.splice(i, 1);
    this.selectedUnit.splice(i, 1);
    this.selectedQuantity.splice(i, 1);
    this.ingredientsRecipe().removeAt(i);
  }

  onSelectChange(event, i) {
    // console.log(this.selectedIngredient);
    console.log(event);
    // console.log(this.ingredients.find(ingr => ingr.ingredientName === event));
    this.selectedIngredient[i] = event;
    // console.log(this.selectedIngredient[i]);
    console.log(this.selectedIngredient[i].unitOfMeasurement);
    this.selectedUnit[i] = this.selectedIngredient[i].unitOfMeasurement;// problem here
    // console.log(this.selectedUnit[i]);
    // console.log(this.selectedUnit[i]);
  }

  equals(o1: Ingredient, o2: Ingredient) {
    return o1.ingredientId === o2.ingredientId;
  }

  onFileChange(event) {
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);

      reader.onload = () => {

        this.imageSrc = reader.result as string;
        console.log(this.imageSrc);
      };
      this.cocktailForm.get('cocktailImageFile').setValue(file);

    }
  }

  addIngredient(ingredient: Ingredient) {
    this.ingredientService.add(ingredient).subscribe(ingredient => {
      this.ingredients.splice(this.ingredients.length, 0, ingredient);
    });
  }

  openAddDialog(): void {
    this.newIngredient = new Ingredient('', 0, '');

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

        console.log(this.ingredients);
        return;
      }
    });
  }

  onSubmit() {
    console.log("submit")
    const formData = new FormData();
    formData.append('file', this.cocktailForm.get('cocktailImageFile').value);
    this.imageService.uploadImage(formData).subscribe(id => {
      console.log(id);
      this.newCocktailImage = new Image(id);
      this.targetCocktail.cocktailName = this.newCocktailName;
      this.targetCocktail.cocktailRecipe = this.newCocktailRecipe;
      this.targetCocktail.cocktailImage = this.newCocktailImage;
      this.newRecipe = [];
      for(let i = 0; i < this.selectedIngredient.length; i++){
        this.newRecipe.splice(i, 0, new Recipe(null,this.targetCocktail,this.selectedIngredient[i], this.selectedQuantity[i]));
      }
      this.targetCocktail.recipes = this.newRecipe;

      this.dialogRef.close(new DialogResult(DialogAction.SAVE));

    });
  }

  ngOnInit(): void {
  }
}
