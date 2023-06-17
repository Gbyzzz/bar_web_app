import { Component, OnInit } from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {CocktailServiceImpl} from "../../../service/entity/impl/CocktailServiceImpl";
import {Pagination, SortDirection} from "../../../model/pagination/Pagination";
import {Cocktail} from "../../../model/Cocktail";

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
    this.getPage();
  }

  ngOnInit(): void {
  }

  pageChanged(pageEvent: PageEvent) {

    if (this.pagination.pageSize != pageEvent.pageSize) {
      this.pagination.pageNumber = 0;
    } else {
      this.pagination.pageNumber = pageEvent.pageIndex;
    }

    this.pagination.pageSize = pageEvent.pageSize;

    this.getPage();
  }

  getPage() {
    this.cocktailService.findAllWithPages(this.pagination).subscribe(cocktails =>{
      this.cocktails = cocktails.content;
      this.totalCocktailsFounded = cocktails.totalElements;
    });
  }
}
