import {Inject, Injectable, InjectionToken} from '@angular/core';
import {Code} from "../../model/registration/Code";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";



export const VALIDATE_URL_TOKEN = new InjectionToken<string>('url');


@Injectable({
  providedIn: 'root'
})
export class ValidateEmailService {

  private readonly url: string;

  constructor(@Inject(VALIDATE_URL_TOKEN) private baseUrl: string,
              private HttpClient: HttpClient) {
    this.url = baseUrl;
  }

  send(code: Code): Observable<boolean> {
    return this.HttpClient.post<boolean>(this.url, code);
  }
}
