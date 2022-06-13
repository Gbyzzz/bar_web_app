import { Pipe, PipeTransform } from '@angular/core';
import {Cocktail} from "../../model/Cocktail";
import {Recipe} from "../../model/Recipe";

@Pipe({
  name: 'recipeFilterByCocktailPipe'
})
export class RecipeFilterByCocktailPipe implements PipeTransform {

  transform(recipes: Recipe[], cocktail: Cocktail): Recipe[] {
    if (!recipes || !cocktail) {
      return recipes;
    }
    console.log("pipe");
    console.log(recipes);
    console.log(cocktail);
    console.log(recipes.filter(item => item.cocktail === cocktail));
    return recipes.filter(item => JSON.stringify(item.cocktail) === JSON.stringify(cocktail));
  }
}
