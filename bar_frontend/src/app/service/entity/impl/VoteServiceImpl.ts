import {Inject, Injectable, InjectionToken} from '@angular/core';
import {VoteService} from "../VoteService";
import {Vote} from "../../../model/Vote";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import {Observable} from "rxjs";
import {Cocktail} from "../../../model/Cocktail";

export const VOTE_URL_TOKEN = new InjectionToken<string>('url');


@Injectable({
  providedIn: 'root'
})
export class VoteServiceImpl implements VoteService{

  private readonly url: string;

  constructor(@Inject(VOTE_URL_TOKEN) private baseUrl: string, private HttpClient: HttpClient) {
    this.url = baseUrl;
  }
  add(vote: Vote): Observable<Vote> {
    return this.HttpClient.post<Vote>(this.url + '/add', vote);
  }

  delete(id: number): Observable<Vote> {
    return undefined;
  }

  findAll(): Observable<Vote[]> {
    return undefined;
  }

  findById(id: number): Observable<Vote> {
    return undefined;
  }

  update(odj: Vote): Observable<Vote> {
    return undefined;
  }


  findByCocktailUserVote(vote: Vote): Observable<Vote> {
      return this.HttpClient.post<Vote>(this.url + '/find_by_cocktail_user', vote);
  }

  getVoteCountByCocktail(cocktail: Cocktail): Observable<number> {
    // @ts-ignore
    return this.HttpClient.post<number>(this.url + '/get_vote_count_by_cocktail', cocktail);
  }
}
