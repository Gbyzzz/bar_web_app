import {Cocktail} from "./Cocktail";
import {Ingredient} from "./Ingredient";

export class Recipe{
  private _recipeId: number;
  private _cocktail: Cocktail;
  private _ingredient: Ingredient;
  private _quantity: number;


  constructor(recipeId: number, cocktail: Cocktail, ingredient: Ingredient, quantity: number) {
    this._recipeId = recipeId;
    this._cocktail = cocktail;
    this._ingredient = ingredient;
    this._quantity = quantity;
  }


  get recipeId(): number {
    return this._recipeId;
  }

  set recipeId(value: number) {
    this._recipeId = value;
  }

  get cocktail(): Cocktail {
    return this._cocktail;
  }

  set cocktail(value: Cocktail) {
    this._cocktail = value;
  }

  get ingredient(): Ingredient {
    return this._ingredient;
  }

  set ingredient(value: Ingredient) {
    this._ingredient = value;
  }

  get quantity(): number {
    return this._quantity;
  }

  set quantity(value: number) {
    this._quantity = value;
  }
}
