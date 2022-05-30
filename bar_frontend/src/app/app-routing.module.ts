import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CocktailComponent} from "./view/main/cocktail/cocktail.component";
import {CocktailsComponent} from "./view/main/cocktails/cocktails.component";

const routes: Routes = [
  {path: 'target_cocktail', component: CocktailComponent},
  {path: 'cocktails', component: CocktailsComponent}]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
