import {Inject, Injectable, InjectionToken} from '@angular/core';
import {CocktailService} from "../CocktailService";
import {Cocktail} from "../../model/Cocktail";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

export const COCKTAIL_URL_TOKEN = new InjectionToken<string>('url');


@Injectable({
  providedIn: 'root'
})
export class CocktailServiceImpl implements CocktailService{

  private readonly url: string;

  constructor(@Inject(COCKTAIL_URL_TOKEN) private baseUrl: string, private HttpClient: HttpClient) {
    this.url = baseUrl;
  }

  add(cocktail: Cocktail): Observable<Cocktail> {
    return this.HttpClient.post<Cocktail>(this.url+'/add', cocktail);
  }

  delete(id: number): Observable<Cocktail> {
    return this.HttpClient.delete<Cocktail>(this.url+'/delete/'+ id);
  }

  findById(id: number): Observable<Cocktail> {
    return this.HttpClient.get<Cocktail>(this.url+'/' + id);
  }

  findAll(): Observable<Cocktail[]> {
    return this.HttpClient.get<Cocktail[]>(this.url+'/all');
  }

  update(cocktail: Cocktail): Observable<Cocktail> {
    return this.HttpClient.put<Cocktail>(this.url+'/update/', cocktail);
  }
}
