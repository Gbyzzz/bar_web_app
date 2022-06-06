import { Component, OnInit } from '@angular/core';
import {Cocktail} from "../../model/Cocktail";
import {CocktailServiceImpl} from "../../service/entity/impl/CocktailServiceImpl";
import {ImageServiceImpl} from "../../service/entity/impl/ImageServiceImpl";
import {EditCocktailDialogComponent} from "../dialog/edit-cocktail-dialog/edit-cocktail-dialog.component";
import {DialogAction} from "../dialog/DialogResult";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit{
  cocktails: Cocktail[];

  ngOnInit(): void {
  }

  constructor(private cocktailService: CocktailServiceImpl,
              private imageService: ImageServiceImpl) {
    this.cocktailService.findAll().subscribe(cocktails => {
      this.cocktails = cocktails;
      console.log(cocktails);

    });
  }
}

