import {Image} from "./Image";

export enum Role{
  ADMIN = "ROLE_ADMIN",
  BARTENDER = "ROLE_BARTENDER",
  USER = "ROLE_USER",
}

export class User{

  private static _Role = Role;

  private _userId: number;
  private _username: string;
  private _name?: string;
  private _surname?: string;
  private _phone?: string;
  private _email: string;
  private _userPic?: Image;
  private _role: Role;
  private _enabled: boolean;
  private _regDate: Date;



  constructor(userId: number, username: string, email: string, role: Role, enabled: boolean, regDate: Date,
              name?: string, surname?: string, phone?: string, userPic?: Image) {
    this._userId = userId;
    this._username = username;
    this._name = name;
    this._surname = surname;
    this._phone = phone;
    this._email = email;
    this._userPic = userPic;
    this._role = role;
    this._enabled = enabled;
    this._regDate = regDate;
  }

  get userId(): number {
    return this._userId;
  }

  set userId(value: number) {
    this._userId = value;
  }

  get username(): string {
    return this._username;
  }

  set username(value: string) {
    this._username = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get surname(): string {
    return this._surname;
  }

  set surname(value: string) {
    this._surname = value;
  }

  get phone(): string {
    return this._phone;
  }

  set phone(value: string) {
    this._phone = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get userPic(): Image {
    return this._userPic;
  }

  set userPic(value: Image) {
    this._userPic = value;
  }

  get role(): Role {
    return this._role;
  }

  set role(value: Role) {
    this._role = value;
  }

  get enabled(): boolean {
    return this._enabled;
  }

  set enabled(value: boolean) {
    this._enabled = value;
  }

  get regDate(): Date {
    return this._regDate;
  }

  set regDate(value: Date) {
    this._regDate = value;
  }
}

