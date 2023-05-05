import {User} from "./User";
import {Image} from "./Image";

export class Cocktail{
  private _cocktailId: number;
  private _cocktailName: string;
  private _cocktailAuthor: User;
  private _cocktailRating: number;
  private _publicationDate: Date;
  private _cocktailImage: Image;
  private _cocktailRecipe: string;
  private _approxAlcoholPercentage: number;

  constructor(cocktailId?: number, cocktailName?: string, cocktailAuthor?: User, cocktailRating?: number,
              publicationDate?: Date, cocktailImage?: Image, cocktailRecipe?: string,
              approxAlcoholPercentage?: number) {
    this._cocktailId = cocktailId;
    this._cocktailName = cocktailName;
    this._cocktailAuthor = cocktailAuthor;
    this._cocktailRating = cocktailRating;
    this._publicationDate = publicationDate;
    this._cocktailImage = cocktailImage;
    this._cocktailRecipe = cocktailRecipe;
    this._approxAlcoholPercentage = approxAlcoholPercentage;
  }

  get cocktailId(): number {
    return this._cocktailId;
  }

  set cocktailId(value: number) {
    this._cocktailId = value;
  }

  get cocktailName(): string {
    return this._cocktailName;
  }

  set cocktailName(value: string) {
    this._cocktailName = value;
  }

  get cocktailAuthor(): User {
    return this._cocktailAuthor;
  }

  set cocktailAuthor(value: User) {
    this._cocktailAuthor = value;
  }

  get cocktailRating(): number {
    return this._cocktailRating;
  }

  set cocktailRating(value: number) {
    this._cocktailRating = value;
  }

  get publicationDate(): Date {
    return this._publicationDate;
  }

  set publicationDate(value: Date) {
    this._publicationDate = value;
  }

  get cocktailImage(): Image {
    return this._cocktailImage;
  }

  set cocktailImage(value: Image) {
    this._cocktailImage = value;
  }

  get cocktailRecipe(): string {
    return this._cocktailRecipe;
  }

  set cocktailRecipe(value: string) {
    this._cocktailRecipe = value;
  }

  get approxAlcoholPercentage(): number {
    return this._approxAlcoholPercentage;
  }

  set approxAlcoholPercentage(value: number) {
    this._approxAlcoholPercentage = value;
  }
}
