import {Component, OnInit} from '@angular/core';
import {Cocktail} from "./model/Cocktail";
import {CocktailServiceImpl} from "./service/impl/CocktailServiceImpl";
import {User} from "./model/User";
import {UserServiceImpl} from "./service/impl/UserServiceImpl";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  ngOnInit(): void {
  }

  constructor(private userService: UserServiceImpl) {

  }

}
