export enum Role {
  ADMIN = "ROLE_ADMIN",
  BARTENDER = "ROLE_BARTENDER",
  USER = "ROLE_USER",
}

export class User {


  userId: number;
  username: string;
  name?: string;
  surname?: string;
  phone?: string;
  email: string;
  userPic?: string;
  role: Role;
  enabled: boolean;
  regDate: Date;


  constructor(userId: number, username: string, email: string, role: Role, enabled: boolean, regDate: Date,
              name?: string, surname?: string, phone?: string, userPic?: string) {
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

