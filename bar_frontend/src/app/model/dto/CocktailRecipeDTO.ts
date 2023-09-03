import {Cocktail} from "../Cocktail";
import {Recipe} from "../Recipe";

export class CocktailRecipeDTO{
  cocktailDTO: Cocktail;
  recipesDTO: Recipe[];


  constructor(cocktailDTO?: Cocktail, recipesDTO?: Recipe[]) {
    this.cocktailDTO = cocktailDTO;
    this.recipesDTO = recipesDTO;
  }
  // toJSON(): any {
  //   const { cocktailDTO, ...rest } = this;
  //   return {
  //     '@class': 'com.gbyzzz.bar_web_app.bar_backend.dto.CocktailRecipeDTO',
  //     'cocktailDTO': this.cocktailDTO,
  //     'recipesDTO': this.recipesDTO
  //   };
  // }
}
