import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IngredientServiceImpl} from "../../../service/impl/IngredientServiceImpl";
import {MatTableDataSource} from "@angular/material/table";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-ingredients-admin',
  templateUrl: './ingredients-admin.component.html',
  styleUrls: ['./ingredients-admin.component.css']
})
export class IngredientsAdminComponent implements OnInit {
  ingredientDataSource: any;
  ingredientDisplayedColumns: string[] = ['ingredientId', 'ingredientName', 'ingredientAlcohol',
  'unitOfMeasurement', 'edit'];

  totalIngredients: number;

  @Output()
  paging = new EventEmitter<PageEvent>();

  constructor(private ingredientService: IngredientServiceImpl) {
    this.ingredientService.findAll().subscribe(ingredients =>{
      this.ingredientDataSource = new MatTableDataSource(ingredients);
      this.totalIngredients = ingredients.length;
    });
  }

  ngOnInit(): void {
  }

  changePage(pageEvent: PageEvent) {
    this.paging.emit(pageEvent);
  }

}
