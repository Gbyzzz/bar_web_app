import {Component, OnInit} from '@angular/core';
import {CocktailServiceImpl} from "../../../service/entity/impl/CocktailServiceImpl";
import {MatDialog} from "@angular/material/dialog";
import {Sort} from "@angular/material/sort";
import {DialogAction} from "../../dialog/DialogResult";
import {Cocktail} from "../../../model/Cocktail";
import {EditCocktailDialogComponent} from "../../dialog/edit-cocktail-dialog/edit-cocktail-dialog.component";
import {Pagination, SortDirection, SortDirectionUtil} from "../../../model/pagination/Pagination";
import {PageEvent} from "@angular/material/paginator";
import {RecipeServiceImpl} from "../../../service/entity/impl/RecipeServiceImpl";
import {Recipe} from "../../../model/Recipe";
@Component({
  selector: 'app-cocktails-admin',
  templateUrl: './cocktails-admin.component.html',
  styleUrls: ['./cocktails-admin.component.css']
})
export class CocktailsAdminComponent implements OnInit {

  readonly defaultPageSize = 10;
  readonly defaultPageNumber = 0;
  readonly defaultSortDirection = SortDirection.DESC;

  cocktails: Cocktail[];
  sortedData: Cocktail[];
  // recipes: Recipe[];
  pagination: Pagination;
  totalCocktailsFound: number;

  constructor(private dialog: MatDialog,
              private cocktailService: CocktailServiceImpl,
              private recipeService: RecipeServiceImpl,
              private sortDirectionUtil: SortDirectionUtil) {
    this.pagination = new Pagination(this.defaultPageSize, this.defaultPageNumber, this.defaultSortDirection);
    this.getPage();
  }

  ngOnInit(): void {
  }


  sortData(sort: Sort) {
    if (this.totalCocktailsFound > this.pagination.pageSize && (sort.active == 'cocktailId'
      || sort.active == 'publicationDate')) {

      if (!sort.active || sort.direction === '') {
        this.pagination.sortDirection = SortDirection.DESC;
      } else{
        this.pagination.sortDirection = this.sortDirectionUtil.change(this.pagination.sortDirection);
      }
      this.getPage();
    } else {
      const data = this.cocktails.slice();
      if (!sort.active || sort.direction === '') {
        this.sortedData = data;
        this.pagination.sortDirection = SortDirection.DESC;
        return;

      }

      this.sortedData = data.sort((a, b) => {
        const isAsc = sort.direction === 'asc';
        switch (sort.active) {
          case 'cocktailId':
            return compare(a.cocktailId, b.cocktailId, isAsc);
          case 'cocktailName':
            return compare(a.cocktailName, b.cocktailName, isAsc);
          case 'cocktailAuthor':
            return compare(a.cocktailAuthor.username, b.cocktailAuthor.username, isAsc);
          case 'cocktailRating':
            return compare(a.cocktailRating, b.cocktailRating, isAsc);
          case 'publicationDate':
            return compare(a.publicationDate, b.publicationDate, isAsc);
          case 'approxAlcoholPercentage':
            return compare(a.approxAlcoholPercentage, b.approxAlcoholPercentage, isAsc);
          default:
            return 0;
        }
      });
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
    this.cocktailService.findAllWithPagesAndRecipes(this.pagination).subscribe(cocktails =>{
      this.cocktails = cocktails.content;
      this.sortedData = cocktails.content;
      this.totalCocktailsFound = cocktails.totalElements;

    });
  }

  openEditDialog(cocktail: Cocktail): void {


    const dialogRef = this.dialog.open(EditCocktailDialogComponent, {
      data: [cocktail],
      autoFocus: false,
      // width: '400px',
      maxWidth: '100%',
      maxHeight: '80vh',
      position: { top: '10vh', bottom: '10vh' , left: '10%', right: '10%'}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.action === DialogAction.SAVE) {
        this.getPage();
        return;
      }
    });
  }
}

function compare(a: number | string | boolean | Date,
                 b: number | string | boolean | Date, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
