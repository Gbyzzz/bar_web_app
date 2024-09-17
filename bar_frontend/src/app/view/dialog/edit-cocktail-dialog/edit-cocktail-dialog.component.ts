import {ChangeDetectorRef, Component, Inject, Injector, OnInit} from '@angular/core';
import {UntypedFormArray, UntypedFormBuilder, UntypedFormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Ingredient} from "../../../model/Ingredient";
import {IngredientServiceImpl} from "../../../service/entity/impl/IngredientServiceImpl";
import {EditIngredientDialogComponent} from "../edit-ingredient-dialog/edit-ingredient-dialog.component";
import {DialogAction, DialogResult} from "../DialogResult";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {Cocktail} from "../../../model/Cocktail";
import {Recipe} from "../../../model/Recipe";
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
  cocktailForm: UntypedFormGroup;
  newIngredient: Ingredient;

  ingredients: Ingredient[];
  targetCocktail: CocktailRecipeDTO;
  dialogRef: MatDialogRef<any>;
  data: CocktailRecipeDTO[];
  fileHolder: File | null;
  hasDuplicates: boolean = false;


  constructor(private cdr: ChangeDetectorRef,
              private dialog: MatDialog,
              private ingredientService: IngredientServiceImpl,
              private fb: UntypedFormBuilder,
              private injector: Injector,
              private router: Router,
              private tokenService: TokenStorageService,
              private cocktailService: CocktailServiceImpl,) {
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

      this.cocktailForm.get('cocktailName').setValue(this.targetCocktail.cocktailDTO.cocktailName);
      this.cocktailForm.get('cocktailImageFile').setValue(this.targetCocktail.cocktailDTO.cocktailImage);
      this.cocktailForm.get('cocktailRecipe').setValue(this.targetCocktail.cocktailDTO.cocktailRecipe);

      if (this.targetCocktail.recipesDTO) {
        this.targetCocktail.recipesDTO.forEach(recipe => {
          this.ingredientsRecipe().insert(this.addIndex, this.newIngredientsRecipe(recipe.ingredient, recipe.quantity));
          this.addIndex++;
        });
      }
      this.dialogRef.updateSize('100%', '100%');
    } else {
      this.targetCocktail = new CocktailRecipeDTO(new Cocktail(), [new Recipe()]);
    }
  }

  ngOnInit(): void {
  }

  ingredientsRecipe(): UntypedFormArray {
    return this.cocktailForm.get("ingredientsRecipe") as UntypedFormArray
  }

  newIngredientsRecipe(ingredient, quantity): UntypedFormGroup {
    return this.fb.group({
      ingredientSelect: [ingredient.ingredientName],
      quantity: [quantity],
      unitSelect: [ingredient.unitOfMeasurement]
    })
  }

  addIngredientsRecipe() {
    this.cdr.detectChanges();
    this.ingredientsRecipe().push(this.newIngredientsRecipe('', ''));
  }

  removeIngredientsRecipe(i: number) {
    this.ingredientsRecipe().removeAt(i);
  }

    onSelectChange(event, i) {

      const ingredientGroup = this.ingredientsRecipe().at(i) as UntypedFormGroup;
      const selectedIngredient = this.ingredients.find(ingredient => ingredient.ingredientName === event.target.value);
      ingredientGroup.get('ingredientSelect').setValue(event.target.value);
      ingredientGroup.get('unitSelect').setValue(selectedIngredient.unitOfMeasurement);
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
        this.updateCocktailValues();
        formData.append('image', this.fileHolder, this.fileHolder.name);
        formData.append('cocktail', new Blob([JSON.stringify(this.targetCocktail)], {
          type: 'application/json'
        }));
      } else {
        this.updateCocktailValues();
      }
      this.dialogRef.close(new DialogResult(DialogAction.SAVE));
    }
  }

  onAdd(): void {
    this.checkDuplicates();
    if(!this.hasDuplicates) {
      const formData = new FormData();

      // this.imageService.uploadImage(formData).subscribe(image => {
      //   this.targetCocktail.cocktailDTO.cocktailImage = image;
        this.targetCocktail.cocktailDTO.cocktailName = this.cocktailForm.get('cocktailName').value;
        this.targetCocktail.cocktailDTO.cocktailRecipe = this.cocktailForm.get('cocktailRecipe').value;
        this.updateCocktailValues();
        this.targetCocktail.cocktailDTO.cocktailAuthor = this.tokenService.getUser();
        console.log(this.targetCocktail);
        formData.append('cocktail', new Blob([JSON.stringify(this.targetCocktail)], {
          type: 'application/json'
        }));
        formData.append('image', this.fileHolder, this.fileHolder.name);
        this.cocktailService.addCocktail(formData).subscribe(res => {
          this.router.navigate(['/cocktails/cocktail/' + res.cocktailDTO.cocktailId]);
        });
      // });
    }
  }


  updateCocktailValues() :void {
    const ingredientsRecipe = this.cocktailForm.get('ingredientsRecipe') as UntypedFormArray;
    this.targetCocktail.recipesDTO = [];
    for (let i = 0; i < ingredientsRecipe.length; i++) {
      this.targetCocktail.recipesDTO.splice(i, 0, new Recipe(null,
        this.ingredients.find(ingredient => ingredient.ingredientName === ingredientsRecipe.value[i].ingredientSelect),
        Number(ingredientsRecipe.value[i].quantity)));
    }
  }

  checkDuplicates(): void {
    const ingredientsRecipe = this.cocktailForm.get('ingredientsRecipe') as UntypedFormArray;
    const duplicates = ingredientsRecipe.value.filter((value, index, self) => {
      return self.indexOf(value) !== index;
    });

    this.hasDuplicates = duplicates.length > 0;
  }
}
