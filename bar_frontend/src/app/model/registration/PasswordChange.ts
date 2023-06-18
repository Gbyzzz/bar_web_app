export class PasswordChange {
  email: String;
  oldPassword: String;
  newPassword: String;


  constructor(email: String, oldPassword?: String, newPassword?: String) {
    this.email = email;
    this.oldPassword = oldPassword;
    this.newPassword = newPassword;
  }
}
