  import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
  import {PasswordChange} from "../../model/registration/PasswordChange";

const AUTH_API = 'http://localhost:8080/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'sign_in', {
      username,
      password
    }, httpOptions);
  }

  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'sign_up', {
      username,
      email,
      password
    }, httpOptions);
  }

  logout(): Observable<any> {
    return this.http.post(AUTH_API + 'sign_out', httpOptions);
  }
  checkPassword(passwordChange: PasswordChange): Observable<any> {
    return this.http.post<any>(AUTH_API + 'check_password', passwordChange);
  }

  changePassword(passwordChange: PasswordChange): Observable<any> {
    return this.http.post<any>(AUTH_API + 'change_password', passwordChange);
  }

  recoverPassword(email: String): Observable<any> {
    return this.http.post<any>(AUTH_API + 'send_password_recover_email', email);
  }
}
