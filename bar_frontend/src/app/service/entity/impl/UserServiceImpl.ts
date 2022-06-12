import {Inject, Injectable, InjectionToken} from '@angular/core';
import {UserService} from "../UserService";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../../../model/User";
import {Pagination} from "../../../model/pagination/Pagination";

export const USER_URL_TOKEN = new InjectionToken<string>('url');

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserServiceImpl  implements UserService{

  private readonly url: string;

  constructor(@Inject(USER_URL_TOKEN) private baseUrl: string, private HttpClient: HttpClient) {
    this.url = baseUrl;
  }

  add(obj: User): Observable<User> {
        throw new Error('Method not implemented.');
    }
  signUp(username: string, email: string, password: string): Observable<any> {
    return this.HttpClient.post(this.url+'/sign_up', {
      username,
      email,
      password
    }, httpOptions);
  }

  delete(id: number): Observable<User> {
    return this.HttpClient.delete<User>(this.url+'/delete/'+ id);
  }

  findById(id: number): Observable<User> {
    return this.HttpClient.get<User>(this.url+'/' + id);
  }

  findAll(): Observable<User[]> {
    console.log("find all");
    return this.HttpClient.get<User[]>(this.url+'/all');
  }

  update(user: User): Observable<User> {
    console.log("update");
    return this.HttpClient.put<User>(this.url+'/update', user);
  }

  isUsernameAvailable(username: string, email: string, password: string): Observable<boolean>{
    return this.HttpClient.post<boolean>(this.url + '/is_username_available', {username, email, password});
  }

  isEmailAvailable(username: string, email: string, password: string): Observable<boolean>{
    return this.HttpClient.post<boolean>(this.url + '/is_email_available', {username, email, password});
  }

  findAllWithPages(pagination: Pagination): Observable<any> {
    return this.HttpClient.post<any>(this.url + '/all_pages', pagination);
  }
}
