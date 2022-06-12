import { Pipe, PipeTransform } from '@angular/core';
import {Cocktail} from "../../model/Cocktail";
import {Recipe} from "../../model/Recipe";

@Pipe({
  name: 'recipeFilterByCocktailPipe'
})
export class RecipeFilterByCocktailPipe implements PipeTransform {

  transform(items: Recipe[], filter: Cocktail): Recipe[] {
    if (!items || !filter) {
      return items;
    }
    return items.filter(item => item.cocktail == filter);
  }
}
