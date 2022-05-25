import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Ingredient} from "../../model/Ingredient";
import {IngredientServiceImpl} from "../../service/impl/IngredientServiceImpl";

@Component({
  selector: 'app-add-cocktail',
  templateUrl: './add-cocktail.component.html',
  styleUrls: ['./add-cocktail.component.css']
})
export class AddCocktailComponent implements OnInit {

  imageSrc: string;
  selectedIngredient: Ingredient;
  selectedUnit: string;
  cocktailForm = new FormGroup({
    cocktailName: new FormControl('', [Validators.required]),
    cocktailImageFile: new FormControl('', [Validators.required]),
    cocktailRecipe: new FormControl('', [Validators.required]),
    ingredientsRecipe: new FormControl('', [Validators.required]),
      // this.fb.array([]),
  });

  // ingredientsRecipe(): FormArray {
  //   return this.cocktailForm.get("ingredientsRecipe") as FormArray
  // }
  //
  // newIngredientsRecipe(): FormGroup {
  //   return this.fb.group({
  //     quantity: '',
  //   })
  // }
  //
  // addIngredientsRecipe() {
  //   this.ingredientsRecipe().push(this.newIngredientsRecipe());
  // }

  ingredients: Ingredient[];

  constructor(private httpClient: HttpClient,
              private ingredientService: IngredientServiceImpl,
              private fb: FormBuilder) {

    ingredientService.findAll().subscribe(ingredients =>{
      this.ingredients = ingredients;
      this.selectedIngredient = ingredients[0];
      this.selectedUnit = this.selectedIngredient.unitOfMeasurement;
    });
  }

  onSelectChange(event){
    this.selectedIngredient = event;
    this.selectedUnit = this.selectedIngredient.unitOfMeasurement;
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

  ngOnInit(): void {
  }
}
