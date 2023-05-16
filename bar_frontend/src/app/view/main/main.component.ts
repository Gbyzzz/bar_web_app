import { Component, OnInit } from '@angular/core';
import {Cocktail} from "../../model/Cocktail";
import {CocktailServiceImpl} from "../../service/entity/impl/CocktailServiceImpl";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit{
  cocktails: Cocktail[];

  ngOnInit(): void {
  }

  constructor(private cocktailService: CocktailServiceImpl) {
    this.cocktailService.findForMain().subscribe(cocktails => {
      this.cocktails = cocktails;
      console.log(cocktails);

    });
  }
}

