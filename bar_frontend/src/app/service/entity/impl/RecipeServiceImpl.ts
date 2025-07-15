import {Inject, Injectable, InjectionToken} from '@angular/core';
import {RecipeService} from "../RecipeService";
import {Recipe} from "../../../model/Recipe";
import {Observable} from "rxjs";
import {Cocktail} from "../../../model/Cocktail";
import { HttpClient } from "@angular/common/http";

export const RECIPE_URL_TOKEN = new InjectionToken<string>('url');

@Injectable({
  providedIn: 'root'
})
export class RecipeServiceImpl implements RecipeService{

  private readonly url: string;

  constructor(@Inject(RECIPE_URL_TOKEN) private baseUrl: string, private HttpClient: HttpClient) {
    this.url = baseUrl;
  }
  add(obj: Recipe): Observable<Recipe> {
    return this.HttpClient.post<Recipe>(this.url + '/add_or_update', obj);
  }

  delete(id: number): Observable<Recipe> {
    return undefined;
  }

  findAll(): Observable<Recipe[]> {
    return undefined;
  }

  findById(id: number): Observable<Recipe> {
    return undefined;
  }

  update(odj: Recipe): Observable<Recipe> {
    return undefined;
  }

  findByCocktail(cocktail: Cocktail): Observable<Recipe[]> {
    return this.HttpClient.post<Recipe[]>(this.url + '/find_by_cocktail', cocktail);
  }

  addAll(obj: Recipe[]): Observable<Recipe[]> {
    return this.HttpClient.post<Recipe[]>(this.url + '/add_or_update', obj);
  }

  findAllByCocktails(cocktails: Cocktail[]): Observable<Recipe[]>  {
    return this.HttpClient.post<Recipe[]>(this.url + '/find_all_by_cocktails', cocktails);
  }
}
