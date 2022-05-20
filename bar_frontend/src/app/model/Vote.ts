import {User} from "./User";
import {Cocktail} from "./Cocktail";

export class Vote{
  voteId: number;
  user: User;
  cocktail: Cocktail;
  voteValue: number;


  constructor(voteId: number, user: User, cocktail: Cocktail, voteValue: number) {
    this.voteId = voteId;
    this.user = user;
    this.cocktail = cocktail;
    this.voteValue = voteValue;
  }
}
