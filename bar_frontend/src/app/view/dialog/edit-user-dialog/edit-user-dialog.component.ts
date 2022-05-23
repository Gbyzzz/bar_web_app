import {Component, Inject, OnInit} from '@angular/core';
import { FormGroup } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Role, User} from "../../../model/User";
import {DialogAction, DialogResult} from "../DialogResult";

@Component({
  selector: 'app-edit-user-dialog',
  templateUrl: './edit-user-dialog.component.html',
  styleUrls: ['./edit-user-dialog.component.css']
})
export class EditUserDialogComponent implements OnInit {

  constructor(
    private dialogRef: MatDialogRef<EditUserDialogComponent>, // // для возможности работы с текущим диалог. окном
    @Inject(MAT_DIALOG_DATA) private data: [User], // данные, которые передаем в текущее диалоговое окно
  ) { }

  roles = Object.keys(Role);

  form: FormGroup;

  user: User;
  newUsername: string;
  newName: string;
  newSurname: string;
  newRole: Role;
  newPhone: string;
  newEmail: string;
  newEnabled: boolean;


  ngOnInit(): void {
    this.user = this.data[0];
    console.log(this.user);
    console.log(this.data);

    this.newUsername = this.user.username;
    this.newName = this.user.name;
    this.newSurname = this.user.surname;
    this.newRole = this.user.role;
    this.newPhone = this.user.phone;
    this.newEmail = this.user.email;
    this.newEnabled = this.user.enabled;
    console.log(this.newRole);

  }

  confirm(): void {

    this.user.username = this.newUsername;
    this.user.name = this.newName;
    this.user.surname = this.newSurname;
    this.user.role = this.newRole;
    this.user.phone = this.newPhone;
    this.user.email = this.newEmail;
    this.user.enabled = this.newEnabled;

    this.dialogRef.close(new DialogResult(DialogAction.SAVE));

  }

}
