import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatTableModule} from "@angular/material/table";
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {HeaderComponent} from './view/header/header.component';
import {FooterComponent} from './view/footer/footer.component';
import {COCKTAIL_URL_TOKEN} from "./service/entity/impl/CocktailServiceImpl";
import {USER_URL_TOKEN} from "./service/entity/impl/UserServiceImpl";
import {MainComponent} from './view/main/main.component';
import {AdminComponent} from './view/admin/admin.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {UsersAdminComponent} from './view/admin/users-admin/users-admin.component';
import {CocktailsAdminComponent} from './view/admin/cocktails-admin/cocktails-admin.component';
import {MatTabsModule} from "@angular/material/tabs";
import {INGREDIENT_URL_TOKEN} from "./service/entity/impl/IngredientServiceImpl";
import {IngredientsAdminComponent} from './view/admin/ingredients-admin/ingredients-admin.component';
import {MatIconModule} from "@angular/material/icon";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {EditUserDialogComponent} from './view/dialog/edit-user-dialog/edit-user-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {EditIngredientDialogComponent} from './view/dialog/edit-ingredient-dialog/edit-ingredient-dialog.component';
import {EditCocktailDialogComponent} from './view/dialog/edit-cocktail-dialog/edit-cocktail-dialog.component';
import {IMAGE_URL_TOKEN} from "./service/entity/impl/ImageServiceImpl";
import { CocktailComponent } from './view/main/cocktail/cocktail.component';
import { CocktailsComponent } from './view/main/cocktails/cocktails.component';
import { TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { AboutComponent } from './view/main/about/about.component';
import { ContactComponent } from './view/main/contact/contact.component';
import { TranslocoRootModule } from './transloco-root.module';
import {RegistrationComponent} from "./view/header/registration/registration.component";
import {authInterceptorProviders} from "./service/auth/auth.interceptor";
import {UserPageComponent} from "./view/main/user-page/user-page.component";

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MainComponent,
    AdminComponent,
    UsersAdminComponent,
    CocktailsAdminComponent,
    IngredientsAdminComponent,
    EditUserDialogComponent,
    EditIngredientDialogComponent,
    EditCocktailDialogComponent,
    CocktailComponent,
    CocktailsComponent,
    AboutComponent,
    ContactComponent,
    RegistrationComponent,
    UserPageComponent
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
    MatCheckboxModule,
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatOptionModule,
    MatSelectModule,
    MatDialogModule,
    ReactiveFormsModule,
    TranslateModule,
    TranslocoRootModule
  ],
  providers: [
    authInterceptorProviders,

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

    {
      provide: IMAGE_URL_TOKEN,
      useValue: 'http://localhost:8080/image'
    },

  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
