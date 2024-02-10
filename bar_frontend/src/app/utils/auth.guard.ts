import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import {TokenStorageService} from "../service/auth/token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard  {
  constructor(
    private router: Router,
    private authenticationService: TokenStorageService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.authenticationService.getUser();
    if (currentUser) {
      if (route.data['roles'] && route.data['roles'].indexOf(currentUser.role) === -1) {
        // role not authorised so redirect to home page
        this.router.navigate(['/']);
        return false;
      }

      return true;
    }

    this.router.navigate(['/'], { queryParams: { returnUrl: state.url } });
    return false;
  }
}
