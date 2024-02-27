import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private host: string = environment.apiUrl;
  private token: string = '';
  private loggedUserName: string = '';
  private  jwthelper = new JwtHelperService();

  constructor(private http: HttpClient) { }

  public login(username: string, password: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    let url: string = this.host + '/login';
    let payload: any = { username: username, password: password };
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(url, payload, { observe: 'response' });
  }

  public register(username: string, password: string): Observable<HttpResponse<any> | HttpErrorResponse> {
    let url: string = this.host + '/register';
    let payload: any = { username: username, password: password };
    return this.http.post<HttpResponse<any> | HttpErrorResponse>(url, payload);
  }

  public logOut(): void {
    this.token = '';
    this.loggedUserName = '';
    localStorage.removeItem('user');
    localStorage.removeItem('token');
  }

  public saveToken(token: string): void {
    this.token = token;
    localStorage.setItem('token', token);
  }

  public loadToken(): void {
    this.token = String(localStorage.getItem('token'));
  }

  public getToken(): string {
    return this.token;
  }

  public addUserToLocalStorage(user:User): void {
    localStorage.setItem('user', JSON.stringify('user'));
  }

  public getUserFromLocalStorage(): User {
   return JSON.parse(String(localStorage.getItem('user')));
  }

  public isLoggedIn(): boolean {
    this.loadToken();
    if (this.token) {
      if (this.jwthelper.decodeToken(this.token).sub != null || '') {
        if (!this.jwthelper.isTokenExpired(this.token)) {
          this.loggedUserName = this.jwthelper.decodeToken(this.token).sub;
          return true;
        }
      }
    }
    this.logOut();
    return false;
  }

}
