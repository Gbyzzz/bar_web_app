import {Component, Inject, OnInit} from '@angular/core';
import { UntypedFormGroup } from '@angular/forms';
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
    private dialogRef: MatDialogRef<EditUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: [User],
  ) { }

  roles = Object.values(Role);

  form: UntypedFormGroup;

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
    this.newUsername = this.user.username;
    this.newName = this.user.name;
    this.newSurname = this.user.surname;
    this.newRole = this.user.role;
    this.newPhone = this.user.phone;
    this.newEmail = this.user.email;
    this.newEnabled = this.user.enabled;
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
