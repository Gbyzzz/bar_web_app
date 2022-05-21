import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatTableModule} from "@angular/material/table";
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {HeaderComponent} from './view/header/header.component';
import {FooterComponent} from './view/footer/footer.component';
import {COCKTAIL_URL_TOKEN} from "./service/impl/CocktailServiceImpl";
import {USER_URL_TOKEN} from "./service/impl/UserServiceImpl";
import {MainComponent} from './view/main/main.component';
import {AdminComponent} from './view/admin/admin.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {UsersComponent} from './view/admin/users/users.component';
import {CocktailsComponent} from './view/admin/cocktails/cocktails.component';
import {MatTabsModule} from "@angular/material/tabs";
import {INGREDIENT_URL_TOKEN} from "./service/impl/IngredientServiceImpl";
import { IngredientsAdminComponent } from './view/admin/ingredients-admin/ingredients-admin.component';
import {MatIconModule} from "@angular/material/icon";
import {MatCheckboxModule} from "@angular/material/checkbox";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MainComponent,
    AdminComponent,
    UsersComponent,
    CocktailsComponent,
    IngredientsAdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    BrowserAnimationsModule,
    MatTabsModule,
    MatIconModule,
    MatCheckboxModule
  ],
  providers: [

    {
      provide: COCKTAIL_URL_TOKEN,
      useValue: 'http://localhost:8080/cocktail'
    },

    {
      provide: USER_URL_TOKEN,
      useValue: 'http://localhost:8080/user'
    },

    {
      provide: INGREDIENT_URL_TOKEN,
      useValue: 'http://localhost:8080/ingredient'
    },

  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
