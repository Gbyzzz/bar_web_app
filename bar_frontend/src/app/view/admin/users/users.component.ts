import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {UserServiceImpl} from "../../../service/impl/UserServiceImpl";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  userDataSource : any = new MatTableDataSource();
  userDisplayedColumns: string[] = ['userId', 'userPic', 'username', 'name', 'surname', 'phone', 'email',
    'role', 'enabled', 'regDate', 'edit'];

  constructor(private userService: UserServiceImpl) {
    this.userService.findAll().subscribe(users => {
      this.userDataSource = new MatTableDataSource(users);
      console.log(this.userDataSource);
    });
  }

  ngOnInit(): void {
  }

}
