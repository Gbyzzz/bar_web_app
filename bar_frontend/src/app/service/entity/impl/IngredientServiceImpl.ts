import {Inject, Injectable, InjectionToken} from "@angular/core";
import {Observable} from "rxjs";
import {IngredientService} from "../IngredientService";
import {Ingredient} from "../../../model/Ingredient";
import {HttpClient} from "@angular/common/http";

export const INGREDIENT_URL_TOKEN = new InjectionToken<string>('url');

@Injectable({
  providedIn: 'root'
})
export class IngredientServiceImpl implements IngredientService {

  private readonly url: string;

  constructor(@Inject(INGREDIENT_URL_TOKEN) private baseUrl: string, private HttpClient: HttpClient) {
    this.url = baseUrl;
  }
  add(ingredient: Ingredient): Observable<Ingredient> {
    return this.HttpClient.post<Ingredient>(this.url+'/add', ingredient);
  }

  delete(id: number): Observable<Ingredient> {
    return this.HttpClient.delete<Ingredient>(this.url+'/delete/'+ id);
  }

  findById(id: number): Observable<Ingredient> {
    return this.HttpClient.get<Ingredient>(this.url+'/id/' + id);
  }

  findAll(): Observable<any> {
    console.log("find all");
    return this.HttpClient.get<Ingredient[]>(this.url+'/all');
  }

  update(ingredient: Ingredient): Observable<Ingredient> {
    return this.HttpClient.put<Ingredient>(this.url+'/update/', ingredient);
  }
}
