import {Inject, Injectable, InjectionToken} from '@angular/core';
import {CocktailService} from "../CocktailService";
import {Cocktail} from "../../../model/Cocktail";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Pagination} from "../../../model/pagination/Pagination";
import {Recipe} from "../../../model/Recipe";
import {CocktailRecipeDTO} from "../../../model/dto/CocktailRecipeDTO";

export const COCKTAIL_URL_TOKEN = new InjectionToken<string>('url');


@Injectable({
  providedIn: 'root'
})
export class CocktailServiceImpl implements CocktailService{

  private readonly url: string;

  constructor(@Inject(COCKTAIL_URL_TOKEN) private baseUrl: string, private HttpClient: HttpClient) {
    this.url = baseUrl;
  }
  delete(id: number): Observable<Cocktail> {
    return this.HttpClient.delete<Cocktail>(this.url+'/delete/'+ id);
  }

  findCocktailById(id: number): Observable<CocktailRecipeDTO> {
    return this.HttpClient.get<CocktailRecipeDTO>(this.url+'/' + id);
  }

  findAll(): Observable<Cocktail[]> {
    return this.HttpClient.get<Cocktail[]>(this.url+'/all');
  }

  findAllWithPages(pagination: Pagination): Observable<any>{
    return this.HttpClient.post<any>(this.url+'/all_pages', pagination);
  }

  findAllWithPagesAndRecipes(pagination: Pagination): Observable<any>{
    return this.HttpClient.post<any>(this.url+'/all_pages_with_recipes', pagination);
  }
  findForMain(): Observable<Cocktail[]>{
    return this.HttpClient.get<Cocktail[]>(this.url+'/main_page');
  }

  addCocktail(formData: FormData): Observable<CocktailRecipeDTO> {
    return this.HttpClient.post<CocktailRecipeDTO>(this.url+'/add', formData);
  }

  updateCocktail(formData: FormData): Observable<CocktailRecipeDTO> {
    return this.HttpClient.put<CocktailRecipeDTO>(this.url+'/update', formData);
  }

  add(obj: Cocktail): Observable<Cocktail> {
    return undefined;
  }

  findById(id: number): Observable<Cocktail> {
    return undefined;
  }

  update(odj: Cocktail): Observable<Cocktail> {
    return undefined;
  }
}
