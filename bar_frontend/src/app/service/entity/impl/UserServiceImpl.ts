import {Inject, Injectable, InjectionToken} from '@angular/core';
import {UserService} from "../UserService";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {User} from "../../../model/User";

export const USER_URL_TOKEN = new InjectionToken<string>('url');

@Injectable({
  providedIn: 'root'
})
export class UserServiceImpl  implements UserService{

  private readonly url: string;

  constructor(@Inject(USER_URL_TOKEN) private baseUrl: string, private HttpClient: HttpClient) {
    this.url = baseUrl;
  }
  add(user: User): Observable<User> {
    return this.HttpClient.post<User>(this.url+'/add', user);
  }

  delete(id: number): Observable<User> {
    return this.HttpClient.delete<User>(this.url+'/delete/'+ id);
  }

  findById(id: number): Observable<User> {
    return this.HttpClient.get<User>(this.url+'/id/' + id);
  }

  findAll(): Observable<User[]> {
    console.log("find all");
    return this.HttpClient.get<User[]>(this.url+'/all');
  }

  update(user: User): Observable<User> {
    return this.HttpClient.put<User>(this.url+'/update/', user);
  }
}
