import {Component, OnInit} from '@angular/core';
import {PageEvent} from "@angular/material/paginator";
import {CocktailServiceImpl} from "../../../service/entity/impl/CocktailServiceImpl";
import {Pagination, SortDirection} from "../../../model/pagination/Pagination";
import {Cocktail} from "../../../model/Cocktail";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {query} from "@angular/animations";

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
  query: string;
  subscription: Subscription;

  constructor(private cocktailService: CocktailServiceImpl, private router: Router, private route: ActivatedRoute) {
  //   if(router.url.includes()){
  //   this.pagination = new Pagination(this.defaultPageSize, this.defaultPageNumber, this.defaultSortDirection);
  //   this.getPage();
  // }
    this.loadSearchResults();
    this.subscription = this.cocktailService.data$.subscribe(data => {
      this.setResults(data);

    });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(res => this.query = res['query'])
  }
  setResults(results: any): void {
    this.cocktails = results.content;
    this.totalCocktailsFounded = results.totalElements;
    this.pagination = new Pagination(this.defaultPageSize, this.defaultPageNumber, this.defaultSortDirection);
  }

  loadSearchResults() {
    this.pagination = new Pagination(this.defaultPageSize, this.defaultPageNumber, this.defaultSortDirection);
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras?.state) {
      this.setResults(navigation.extras.state['results']);
      this.query = navigation.extras.state['query'];
      console.log("Passed data: ", this.cocktails);
    } else {
      this.getPage();
    }
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
    !this.router.url.includes('/search') ? this.cocktailService.findAllWithPages(this.pagination).subscribe(cocktails => {
      this.setResults(cocktails);
    }) :
      this.cocktailService.search(this.query, this.pagination).subscribe(cocktails => {
        this.setResults(cocktails);
      });
  }
}
