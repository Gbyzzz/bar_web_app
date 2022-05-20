import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {CocktailServiceImpl} from "../../../service/impl/CocktailServiceImpl";

@Component({
  selector: 'app-cocktails',
  templateUrl: './cocktails.component.html',
  styleUrls: ['./cocktails.component.css']
})
export class CocktailsComponent implements OnInit {
  cocktailDataSource : any = new MatTableDataSource();

  cocktailDisplayedColumns: string[] = ['cocktailId', 'cocktailImage', 'cocktailName', 'cocktailAuthor',
    'cocktailRating', 'publicationDate', 'cocktailRecipe', 'ingredients', 'approxAlcoholPercentage', 'edit'];

  constructor(private cocktailService: CocktailServiceImpl) {
    this.cocktailService.findAll().subscribe(cocktail =>{
      this.cocktailDataSource = new MatTableDataSource(cocktail)
      console.log(this.cocktailDataSource);
    });
  }

  ngOnInit(): void {
  }

}
