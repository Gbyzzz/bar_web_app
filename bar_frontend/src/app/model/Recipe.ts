import {Cocktail} from "./Cocktail";
import {Ingredient} from "./Ingredient";

export class Recipe{
  recipeId: number;
  cocktail: Cocktail;
  ingredient: Ingredient;
  quantity: number;


  constructor(recipeId: number, cocktail: Cocktail, ingredient: Ingredient, quantity: number) {
    this.recipeId = recipeId;
    this.cocktail = cocktail;
    this.ingredient = ingredient;
    this.quantity = quantity;
  }
}
