import { Component, OnInit } from '@angular/core';
import {User} from "../../model/User";
import {CocktailServiceImpl} from "../../service/impl/CocktailServiceImpl";
import {UserServiceImpl} from "../../service/impl/UserServiceImpl";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {


  ingredientsDataSource : any = new MatTableDataSource();




  constructor() {
  }

  ngOnInit(): void {
  }

}
