import {User} from "./User";
import {Cocktail} from "./Cocktail";

export class Vote{
  private _voteId: number;
  private _user: User;
  private _cocktail: Cocktail;
  private _voteValue: number;


  constructor(voteId: number, user: User, cocktail: Cocktail, voteValue: number) {
    this._voteId = voteId;
    this._user = user;
    this._cocktail = cocktail;
    this._voteValue = voteValue;
  }


  get voteId(): number {
    return this._voteId;
  }

  set voteId(value: number) {
    this._voteId = value;
  }

  get user(): User {
    return this._user;
  }

  set user(value: User) {
    this._user = value;
  }

  get cocktail(): Cocktail {
    return this._cocktail;
  }

  set cocktail(value: Cocktail) {
    this._cocktail = value;
  }

  get voteValue(): number {
    return this._voteValue;
  }

  set voteValue(value: number) {
    this._voteValue = value;
  }
}
