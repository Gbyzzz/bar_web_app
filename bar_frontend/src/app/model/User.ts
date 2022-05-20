import {Image} from "./Image";

export class User{
  userId: number;
  username: string;
  name?: string;
  surname?: string;
  phone?: string;
  email: string;
  userPic?: Image;
  role: string;
  enabled: boolean;
  regDate: Date;

  constructor(userId: number, username: string, email: string, role: string, enabled: boolean, regDate: Date,
              name?: string, surname?: string, phone?: string, userPic?: Image) {
    this.userId = userId;
    this.username = username;
    this.name = name;
    this.surname = surname;
    this.phone = phone;
    this.email = email;
    this.userPic = userPic;
    this.role = role;
    this.enabled = enabled;
    this.regDate = regDate;
  }
}
