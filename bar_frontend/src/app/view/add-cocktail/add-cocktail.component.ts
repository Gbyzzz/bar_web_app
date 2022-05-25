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
  selectedIngredient: Ingredient[] = [];
  selectedUnit: string[] = [];
  cocktailForm: FormGroup;

  ingredients: Ingredient[];

  constructor(private httpClient: HttpClient,
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
      this.selectedIngredient.splice(0, 0, this.ingredients[0]);
      this.selectedUnit.splice(0, 0, this.selectedIngredient[0].unitOfMeasurement);
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

    this.ingredientsRecipe().push(this.newIngredientsRecipe());
  }

  removeIngredientsRecipe(i:number) {
    this.ingredientsRecipe().removeAt(i);
  }

  onSelectChange(event, i){
    console.log(event);    console.log(i);

    this.selectedIngredient.splice(i, i, event);
    this.selectedUnit.splice(i, i, this.selectedIngredient[i].unitOfMeasurement);
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
