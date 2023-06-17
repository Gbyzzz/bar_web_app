import {Component, OnInit} from '@angular/core';
import {IngredientServiceImpl} from "../../../service/entity/impl/IngredientServiceImpl";
import {Ingredient} from "../../../model/Ingredient";
import {MatDialog} from "@angular/material/dialog";
import {Sort} from "@angular/material/sort";
import {DialogAction} from "../../dialog/DialogResult";
import {EditIngredientDialogComponent} from "../../dialog/edit-ingredient-dialog/edit-ingredient-dialog.component";
import {Pagination, SortDirection, SortDirectionUtil} from "../../../model/pagination/Pagination";
import {User} from "../../../model/User";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-ingredients-admin',
  templateUrl: './ingredients-admin.component.html',
  styleUrls: ['./ingredients-admin.component.css']
})
export class IngredientsAdminComponent implements OnInit {

  readonly defaultPageSize = 10;
  readonly defaultPageNumber = 0;
  readonly defaultSortDirection = SortDirection.DESC;

  ingredients: Ingredient[];
  sortedData: Ingredient[];

  pagination: Pagination;
  totalIngredientsFound: number;

  constructor(private dialog: MatDialog,
              private ingredientService: IngredientServiceImpl,
              private sortDirectionUtil: SortDirectionUtil) {
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
    this.ingredientService.findAllWithPages(this.pagination).subscribe(ingredients =>{
      this.ingredients = ingredients.content;
      this.sortedData = ingredients.content;
      this.totalIngredientsFound = ingredients.totalElements;
    });
  }

  updateIngredient(ingredient: Ingredient){
    this.ingredientService.update(ingredient).subscribe();
  }

  sortData(sort: Sort) {
    if (this.totalIngredientsFound > this.pagination.pageSize && sort.active == 'ingredientId') {

      if (!sort.active || sort.direction === '') {
        this.pagination.sortDirection = SortDirection.DESC;
      } else {
        this.pagination.sortDirection = this.sortDirectionUtil.change(this.pagination.sortDirection);
      }
      this.getPage();
    } else {
      const data = this.ingredients.slice();
      if (!sort.active || sort.direction === '') {
        this.sortedData = data;
        return;
      }

      this.sortedData = data.sort((a, b) => {
        const isAsc = sort.direction === 'asc';
        switch (sort.active) {
          case 'ingredientId':
            return compare(a.ingredientId, b.ingredientId, isAsc);
          case 'ingredientName':
            return compare(a.ingredientName, b.ingredientName, isAsc);
          case 'ingredientAlcohol':
            return compare(a.ingredientAlcohol, b.ingredientAlcohol, isAsc);
          case 'unitOfMeasurement':
            return compare(a.unitOfMeasurement, b.unitOfMeasurement, isAsc);
          default:
            return 0;
        }
      });
    }
  }

  openEditDialog(ingredient: Ingredient): void {


    const dialogRef = this.dialog.open(EditIngredientDialogComponent, {
      data: [ingredient],
      autoFocus: false
    });

    dialogRef.afterClosed().subscribe(result => {

      if (!(result)) {
        return;
      }

      if (result.action === DialogAction.SAVE) {
        this.updateIngredient(ingredient);
        return;
      }
    });
  }
}

function compare(a: number | string | boolean | Date,
                 b: number | string | boolean | Date, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
