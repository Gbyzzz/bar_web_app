import {Inject, Injectable, InjectionToken} from '@angular/core';
import {CocktailService} from "../CocktailService";
import {Cocktail} from "../../../model/Cocktail";
import {Observable, Subject} from "rxjs";
import { HttpClient } from "@angular/common/http";
import {Pagination} from "../../../model/pagination/Pagination";
import {Recipe} from "../../../model/Recipe";

export const COCKTAIL_URL_TOKEN = new InjectionToken<string>('url');
export const COCKTAIL_SEARCH_URL_TOKEN = new InjectionToken<string>('searchUrl');



@Injectable({
  providedIn: 'root'
})
export class CocktailServiceImpl implements CocktailService{

  private readonly url: string;
  private readonly searchUrl: string;
  private cocktails = new Subject<any>;
  data$ = this.cocktails.asObservable();

  constructor(@Inject(COCKTAIL_URL_TOKEN) private baseUrl: string, @Inject(COCKTAIL_SEARCH_URL_TOKEN) private baseSearchUrl: string,
              private HttpClient: HttpClient) {
    this.url = baseUrl;
    this.searchUrl = baseSearchUrl;
  }

  setCocktails(cocktails: Cocktail[]):void{
    this.cocktails.next(cocktails);
  }
  delete(id: number): Observable<Cocktail> {
    return this.HttpClient.delete<Cocktail>(this.url+'/delete/'+ id);
  }

  findCocktailById(id: number): Observable<Cocktail> {
    return this.HttpClient.get<Cocktail>(this.url+'/' + id);
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

  addCocktail(formData: FormData): Observable<Cocktail> {
    return this.HttpClient.post<Cocktail>(this.url+'/add', formData);
  }

  updateCocktail(formData: FormData): Observable<Cocktail> {
    return this.HttpClient.put<Cocktail>(this.url+'/update', formData);
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

  search(query: string, pagination: Pagination): Observable<any>{
    const requestBody = {
      query: query, // or simply { query }
      pagination: pagination // or simply { pagination }
    };
    console.log(pagination)

    return this.HttpClient.post<any>(
      this.searchUrl,
      requestBody
    );

  }
}
