import { HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from './service/authentication.service';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  private ignoreURL: string[] = ['login', 'register'];

  constructor(private authenticationService: AuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.containWords(req.url)) {
      return next.handle(req);
    } else {
      this.authenticationService.loadToken();
      const token = this.authenticationService.getToken();
      const modifiedRequest = req.clone({ setHeaders: { Authorization: token }});
      return next.handle(modifiedRequest);
    }
  }

  private containWords(url: string) {
    const pattern = new RegExp(this.ignoreURL.join("|"), "i");
    return pattern.test(url);
  }
};
