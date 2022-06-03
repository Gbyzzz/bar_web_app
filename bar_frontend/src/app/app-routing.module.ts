import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CocktailComponent} from "./view/main/cocktail/cocktail.component";
import {CocktailsComponent} from "./view/main/cocktails/cocktails.component";
import {MainComponent} from "./view/main/main.component";
import {AboutComponent} from "./view/main/about/about.component";
import {ContactComponent} from "./view/main/contact/contact.component";
import {AdminComponent} from "./view/admin/admin.component";
import {RegistrationComponent} from "./view/header/registration/registration.component";

const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'cocktails', component: CocktailsComponent},
  {path: 'cocktails/cocktail/:id', component: CocktailComponent},
  {path: 'about', component: AboutComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'register', component: RegistrationComponent}



  // {
  //   path: '',
  //   component: MainComponent,
  //   children: [
  //     {path: 'cocktails', component: CocktailsComponent, children:[
  //         {path: 'cocktails/cocktail', component: CocktailComponent},
  //       ]},
  //     {path: 'cocktail', component: CocktailComponent},
  //   ]
  // },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
