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

  // toJSON(): any {
  //   const { voteId, ...rest } = this;
  //   return {
  //     '@class': 'com.gbyzzz.bar_web_app.bar_backend.dto.VoteDTO',
  //     ...rest
  //   };
  // }
}
