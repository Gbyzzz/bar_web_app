import {ChangeDetectorRef, Component, Inject, Injector, OnInit} from '@angular/core';
import {UntypedFormArray, UntypedFormBuilder, UntypedFormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Ingredient} from "../../../model/Ingredient";
import {IngredientServiceImpl} from "../../../service/entity/impl/IngredientServiceImpl";
import {EditIngredientDialogComponent} from "../edit-ingredient-dialog/edit-ingredient-dialog.component";
import {DialogAction, DialogResult} from "../DialogResult";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {Cocktail} from "../../../model/Cocktail";
import {Image} from "../../../model/Image";
import {Recipe} from "../../../model/Recipe";
import {ImageServiceImpl} from "../../../service/entity/impl/ImageServiceImpl";
import {CocktailServiceImpl} from "../../../service/entity/impl/CocktailServiceImpl";
import {TokenStorageService} from "../../../service/auth/token-storage.service";
import {Router} from "@angular/router";
import {RecipeServiceImpl} from "../../../service/entity/impl/RecipeServiceImpl";
import {CocktailRecipeDTO} from "../../../model/dto/CocktailRecipeDTO";

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
  cocktailForm: UntypedFormGroup;
  newIngredient: Ingredient;

  ingredients: Ingredient[];
  targetCocktail: CocktailRecipeDTO;
  newCocktailName: string;
  newCocktailImage: Image;
  newCocktailRecipe: string;
  newRecipes: Recipe[];
  dialogRef: MatDialogRef<any>;
  data: CocktailRecipeDTO[];
  // recipes: Recipe[];
  fileHolder: File | null;
  hasDuplicates: boolean = false;


  constructor(private httpClient: HttpClient,
              private cdr: ChangeDetectorRef,
              private dialog: MatDialog,
              private ingredientService: IngredientServiceImpl,
              private fb: UntypedFormBuilder,
              private injector: Injector,
              private router: Router,
              private tokenService: TokenStorageService,
              private imageService: ImageServiceImpl,
              private cocktailService: CocktailServiceImpl) {
    this.dialogRef = this.injector.get(MatDialogRef, null);
    this.data = this.injector.get(MAT_DIALOG_DATA, null);

    this.cocktailForm = this.fb.group({
      cocktailName: '',
      cocktailImageFile: '',
      cocktailRecipe: '',
      ingredientsRecipe: this.fb.array([]),
    });

    ingredientService.findAll().subscribe(ingredients => {
      this.ingredients = ingredients;
    });
    if(this.data[0]) {
      this.targetCocktail = this.data[0];
      this.dialogRef.updateSize('100%', '100%');
    } else {
      this.targetCocktail = new CocktailRecipeDTO(new Cocktail(), [new Recipe()]);
    }
    console.log(this.targetCocktail);



    if(this.targetCocktail.cocktailDTO.cocktailId) {
      this.newCocktailName = this.targetCocktail.cocktailDTO.cocktailName;
      this.newCocktailImage = this.targetCocktail.cocktailDTO.cocktailImage;
      this.newCocktailRecipe = this.targetCocktail.cocktailDTO.cocktailRecipe;
      // this.recipeService.findByCocktail(this.targetCocktail).subscribe(res => {
      //   this.recipes = res;
      //   this.targetCocktail.recipes = res;
        if (this.targetCocktail.recipesDTO) {
          this.targetCocktail.recipesDTO.forEach(recipe => {
            this.selectedIngredient.splice(this.selectedIngredient.length, 0, recipe.ingredient);
            this.selectedUnit.splice(this.selectedUnit.length, 0, recipe.ingredient.unitOfMeasurement);
            this.selectedQuantity.splice(this.selectedQuantity.length, 0, recipe.quantity);
            this.ingredientsRecipe().insert(this.addIndex, this.newIngredientsRecipe());
            this.addIndex++;
          });
        }
      // });
    }
  }

  ngOnInit(): void {
  }

  ingredientsRecipe(): UntypedFormArray {
    return this.cocktailForm.get("ingredientsRecipe") as UntypedFormArray
  }

  newIngredientsRecipe(): UntypedFormGroup {
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
    this.selectedIngredient[i] = event;
    this.selectedUnit[i] = this.selectedIngredient[i].unitOfMeasurement;
  }

  equals(o1: Ingredient, o2: Ingredient) {
    return o1.ingredientId === o2.ingredientId;
  }

  onFileChange(event): void {
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.imageSrc = reader.result as string;
      };
      this.fileHolder = event.target.files[0];
    }
  }

  addIngredient(ingredient: Ingredient): void {
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
        this.addIngredient(this.newIngredient);
        return;
      }
    });
  }

  onSubmit(): void {
    this.checkDuplicates();
    if(!this.hasDuplicates) {
      if (!this.cocktailForm.get('cocktailImageFile').value.imageId) {
        const formData = new FormData();
        formData.append('file', this.fileHolder, this.fileHolder.name);
        this.imageService.uploadImage(formData).subscribe(image => {
          this.newCocktailImage = image;
          this.updateCocktailValues();
          // this.recipes = this.newRecipes;
          // this.recipeService.addAll(this.recipes)
          //   .subscribe(res => {
          // });
        });
      } else {
        this.updateCocktailValues();
        // this.recipes = this.newRecipes;
        // this.recipeService.addAll(this.recipes)
        //   .subscribe(res => {
        // });
      }
      this.dialogRef.close(new DialogResult(DialogAction.SAVE));
    }
  }

  onAdd(): void {
    this.checkDuplicates();
    if(!this.hasDuplicates) {
      console.log("1");
      const formData = new FormData();
      formData.append('file', this.fileHolder, this.fileHolder.name);
      this.imageService.uploadImage(formData).subscribe(image => {
        console.log("2");

        this.newCocktailImage = image;
        this.updateCocktailValues();
        console.log(this.targetCocktail);
        console.log(this.targetCocktail.cocktailDTO);
        console.log(this.targetCocktail.cocktailDTO.cocktailName);

        this.targetCocktail.cocktailDTO.cocktailAuthor = this.tokenService.getUser();
        console.log(this.targetCocktail);
        console.log(JSON.stringify(this.targetCocktail));
        this.cocktailService.addCocktail(this.targetCocktail).subscribe(res => {
          console.log("3");
          this.router.navigate(['/cocktails/cocktail/' + res.cocktailDTO.cocktailId]);
        });
      });
    }
  }


  updateCocktailValues() :void {
    this.targetCocktail.cocktailDTO.cocktailName = this.newCocktailName;
    this.targetCocktail.cocktailDTO.cocktailRecipe = this.newCocktailRecipe;
    this.targetCocktail.cocktailDTO.cocktailImage = this.newCocktailImage;
    console.log('recipes');
    this.newRecipes = [];
    for (let i = 0; i < this.selectedIngredient.length; i++) {
      this.newRecipes.splice(i, 0, new Recipe(null,
        this.selectedIngredient[i], Number(this.selectedQuantity[i])));
    }
    console.log(this.newRecipes);
    this.targetCocktail.recipesDTO = this.newRecipes;
  }

  checkDuplicates(): void {
    const duplicates = this.selectedIngredient.filter((value, index, self) => {
      return self.indexOf(value) !== index;
    });

    this.hasDuplicates = duplicates.length > 0;
  }
}
