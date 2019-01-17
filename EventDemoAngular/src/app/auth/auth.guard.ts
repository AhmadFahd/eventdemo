import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import {AuthenticationService} from './authentication.service';
import {RoleEnum} from '../shared/roles';
import {isBoolean} from 'util';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

    constructor(private router: Router, private authentication: AuthenticationService) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const auths = this.authentication.getAuthorities();
        if (localStorage.getItem('currentUser')) {
            if (route.data.authority) {
                let check = false;
                auths.forEach(function (value) {
                    if (value.authority.includes(route.data.authority.toString())) {
                        check = true;
                    }
                });
                if (check) {
                    return check;
                } else {
                this.router.navigate(['/']);
                return check;
            }
            }
            return true;
        }
            // not logged in so redirect to login page with the return url
            this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
            return false;
        }
    }
