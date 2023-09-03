import {Cocktail} from "./Cocktail";
import {Ingredient} from "./Ingredient";
import { Exclude } from 'class-transformer';


export class Recipe{
  recipeId: number;

  ingredient: Ingredient;
  quantity: number;


  constructor(recipeId?: number, ingredient?: Ingredient, quantity?: number) {
    this.recipeId = recipeId;
    this.ingredient = ingredient;
    this.quantity = quantity;
  }

  // toJSON(): any {
  //   const { recipeId, ...rest } = this;
  //   return {
  //     '@class': 'com.gbyzzz.bar_web_app.bar_backend.dto.RecipeDTO',
  //     ...rest
  //   };
  // }
}
