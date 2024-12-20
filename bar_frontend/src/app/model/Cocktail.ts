import {User} from "./User";
import {Recipe} from "./Recipe";

export class Cocktail {
  cocktailId: number;
  cocktailName: string;
  cocktailAuthor: User;
  cocktailRating: number;
  publicationDate: Date;
  cocktailImage: string;
  cocktailImageThumbnail: string;
  cocktailRecipe: string;
  approxAlcoholPercentage: number;
  voteCount: number;
  recipes: Recipe[];


  constructor(cocktailId?: number, cocktailName?: string, cocktailAuthor?: User,
              cocktailRating?: number, publicationDate?: Date, cocktailImage?: string,
              cocktailImageThumbnail?: string, cocktailRecipe?: string, approxAlcoholPercentage?: number,
              voteCount?: number, recipes?: Recipe[]) {
    this.cocktailId = cocktailId;
    this.cocktailName = cocktailName;
    this.cocktailAuthor = cocktailAuthor;
    this.cocktailRating = cocktailRating;
    this.publicationDate = publicationDate;
    this.cocktailImage = cocktailImage;
    this.cocktailImageThumbnail = cocktailImageThumbnail;
    this.cocktailRecipe = cocktailRecipe;
    this.approxAlcoholPercentage = approxAlcoholPercentage;
    this.voteCount = voteCount;
    this.recipes = recipes;
  }


  // toJSON(): any {
  //   const { cocktailId, ...rest } = this;
  //   return {
  //     '@class': 'com.gbyzzz.bar_web_app.bar_backend.dto.CocktailDTO', // Manually include the JSON representation or null if cocktailImage is null/undefined
  //     ...rest
  //   };
  // }
}
