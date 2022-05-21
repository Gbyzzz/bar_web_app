import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {UserServiceImpl} from "../../../service/impl/UserServiceImpl";
import {User} from "../../../model/User";

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

  updateUser(user: User){
    this.userService.update(user).subscribe();
  }

  toggleEnabled(user: User) {

    if (user.enabled) {
      user.enabled = false;
    } else {
      user.enabled = true;
    }

    this.updateUser(user);
  }

}
