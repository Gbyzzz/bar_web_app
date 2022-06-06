import { Component, OnInit } from '@angular/core';
import {UserServiceImpl} from "../../../service/entity/impl/UserServiceImpl";
import {TokenStorageService} from "../../../service/auth/token-storage.service";
import {User} from "../../../model/User";
import {Image} from "../../../model/Image";

@Component({
  selector: 'app-userpage',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  user: User;
  newName: string;
  newSurname: string;
  newPhone: string;
  newImage: Image;


  constructor(private userService: UserServiceImpl,
              private tokenStorage: TokenStorageService) {
    userService.findById(tokenStorage.getUser().id).subscribe(user =>{
      this.user = user;
      this.newName = user.name;
      this.newSurname = user.surname;
      this.newPhone = user.phone;
      this.newImage = user.userPic;
    });
  }

  ngOnInit(): void {
  }

}
