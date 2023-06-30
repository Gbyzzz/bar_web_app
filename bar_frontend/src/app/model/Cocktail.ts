import {User} from "./User";
import {Image} from "./Image";

export class Cocktail {
  cocktailId: number;
  cocktailName: string;
  cocktailAuthor: User;
  cocktailRating: number;
  publicationDate: Date;
  cocktailImage: Image;
  cocktailRecipe: string;
  approxAlcoholPercentage: number;
  voteCount: number;


  constructor(cocktailId?: number, cocktailName?: string, cocktailAuthor?: User,
              cocktailRating?: number, publicationDate?: Date, cocktailImage?: Image,
              cocktailRecipe?: string, approxAlcoholPercentage?: number,
              voteCount?: number) {
    this.cocktailId = cocktailId;
    this.cocktailName = cocktailName;
    this.cocktailAuthor = cocktailAuthor;
    this.cocktailRating = cocktailRating;
    this.publicationDate = publicationDate;
    this.cocktailImage = cocktailImage;
    this.cocktailRecipe = cocktailRecipe;
    this.approxAlcoholPercentage = approxAlcoholPercentage;
    this.voteCount = voteCount;
  }


  // toJSON(): any {
  //   const { cocktailId, ...rest } = this;
  //   return {
  //     '@class': 'com.gbyzzz.bar_web_app.bar_backend.dto.CocktailDTO', // Manually include the JSON representation or null if cocktailImage is null/undefined
  //     ...rest
  //   };
  // }
}
