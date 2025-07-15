import { Component, OnInit } from '@angular/core';
import {User} from "../../model/User";
import {CocktailServiceImpl} from "../../service/entity/impl/CocktailServiceImpl";
import {UserServiceImpl} from "../../service/entity/impl/UserServiceImpl";
import {MatTableDataSource} from "@angular/material/table";

@Component({
    selector: 'app-admin',
    templateUrl: './admin.component.html',
    styleUrls: ['./admin.component.css'],
    standalone: false
})
export class AdminComponent implements OnInit {



  constructor() {
  }

  ngOnInit(): void {
  }

}
