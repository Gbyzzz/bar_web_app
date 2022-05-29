import { Component, OnInit } from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {CocktailServiceImpl} from "../../../service/impl/CocktailServiceImpl";
import {Pagination} from "../../../model/pagination/Pagination";
import {Cocktail} from "../../../model/Cocktail";

@Component({
  selector: 'app-cocktails',
  templateUrl: './cocktails.component.html',
  styleUrls: ['./cocktails.component.css']
})
export class CocktailsComponent implements OnInit {

  readonly defaultPageSize = 5;
  readonly defaultPageNumber = 0;

  cocktails: Cocktail[];
  pagination: Pagination;
  totalCocktailsFounded: number;

  constructor(private cocktailService: CocktailServiceImpl) {

    this.cocktailService.findAllWithPages(this.pagination).subscribe(cocktails =>{
      this.cocktails = cocktails;
      this.pagination.pageNumber = this.defaultPageNumber;
      this.pagination.pageSize = this.defaultPageSize;
      this.totalCocktailsFounded = cocktails.length;
    })
  }

  ngOnInit(): void {
  }

  pageChanged(pageEvent: PageEvent) {

    // если изменили настройку "кол-во на странице" - заново делаем запрос и показываем с 1й страницы
    if (this.pagination.pageSize !== pageEvent.pageSize) {
      this.pagination.pageNumber = 0; // новые данные будем показывать с 1-й страницы (индекс 0)
    } else {
      // если просто перешли на другую страницу
      this.pagination.pageNumber = pageEvent.pageIndex;
    }

    this.pagination.pageSize = pageEvent.pageSize;
    this.pagination.pageNumber = pageEvent.pageIndex;

    this.cocktailService.findAllWithPages(this.pagination); // показываем новые данные
  }
}
