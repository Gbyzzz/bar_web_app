import { Component, OnInit } from '@angular/core';
import {Cocktail} from "../../model/Cocktail";
import {CocktailServiceImpl} from "../../service/entity/impl/CocktailServiceImpl";
import {TokenStorageService} from "../../service/auth/token-storage.service";

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css'],
    standalone: false
})
export class MainComponent implements OnInit{
  cocktails: Cocktail[];

  ngOnInit(): void {
  }

  constructor(private cocktailService: CocktailServiceImpl,
  private tokenStorage: TokenStorageService) {
    this.cocktailService.findForMain().subscribe(cocktails => {
      this.cocktails = cocktails;
      console.log(cocktails);
      console.log(this.tokenStorage.getUser())

    });
  }
}

