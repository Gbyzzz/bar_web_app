import {User} from "./User";
import {Image} from "./Image";

export class Cocktail{
  cocktailId: number;
  cocktailName: string;
  cocktailAuthor: User;
  cocktailRating: number;
  publicationDate: Date;
  cocktailImage: Image;
  cocktailRecipe: string;
  approxAlcoholPercentage: number;

  constructor(cocktailId?: number, cocktailName?: string, cocktailAuthor?: User, cocktailRating?: number,
              publicationDate?: Date, cocktailImage?: Image, cocktailRecipe?: string,
              approxAlcoholPercentage?: number) {
    this.cocktailId = cocktailId;
    this.cocktailName = cocktailName;
    this.cocktailAuthor = cocktailAuthor;
    this.cocktailRating = cocktailRating;
    this.publicationDate = publicationDate;
    this.cocktailImage = cocktailImage;
    this.cocktailRecipe = cocktailRecipe;
    this.approxAlcoholPercentage = approxAlcoholPercentage;
  }
}
