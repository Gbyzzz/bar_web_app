import { Component, OnInit } from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {CocktailServiceImpl} from "../../../service/entity/impl/CocktailServiceImpl";
import {Pagination, SortDirection} from "../../../model/pagination/Pagination";
import {Cocktail} from "../../../model/Cocktail";
import {ImageService} from "../../../service/entity/ImageService";
import {ImageServiceImpl} from "../../../service/entity/impl/ImageServiceImpl";
import {Image} from "../../../model/Image";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-cocktails',
  templateUrl: './cocktails.component.html',
  styleUrls: ['./cocktails.component.css']
})
export class CocktailsComponent implements OnInit {

  readonly defaultPageSize = 6;
  readonly defaultPageNumber = 0;
  readonly defaultSortDirection = SortDirection.DESC;

  cocktails: Cocktail[];
  pagination: Pagination;
  totalCocktailsFounded: number;

  constructor(private cocktailService: CocktailServiceImpl) {

    this.pagination = new Pagination(this.defaultPageSize, this.defaultPageNumber, this.defaultSortDirection);
    console.log(this.pagination);

    this.getPage();
  }

  ngOnInit(): void {
  }

  pageChanged(pageEvent: PageEvent) {

    if (this.pagination.pageSize != pageEvent.pageSize) {
      this.pagination.pageNumber = 0;
      console.log("true");

    } else {
      this.pagination.pageNumber = pageEvent.pageIndex;
    }

    this.pagination.pageSize = pageEvent.pageSize;

    this.getPage();
  }

  getPage() {
    this.cocktailService.findAllWithPages(this.pagination).subscribe(cocktails =>{
      this.cocktails = cocktails.content;
      console.log(cocktails);
      this.totalCocktailsFounded = cocktails.totalElements;
      console.log(this.totalCocktailsFounded);
    });
  }

  // paging(pageEvent: PageEvent) {
  //
  //   // если изменили настройку "кол-во на странице" - заново делаем запрос и показываем с 1й страницы
  //   if (this.taskSearchValues.pageSize !== pageEvent.pageSize) {
  //     this.taskSearchValues.pageNumber = 0; // новые данные будем показывать с 1-й страницы (индекс 0)
  //   } else {
  //     // если просто перешли на другую страницу
  //     this.taskSearchValues.pageNumber = pageEvent.pageIndex;
  //   }
  //
  //   this.taskSearchValues.pageSize = pageEvent.pageSize;
  //   this.taskSearchValues.pageNumber = pageEvent.pageIndex;
  //
  //   this.searchTasks(this.taskSearchValues); // показываем новые данные
  // }
}
