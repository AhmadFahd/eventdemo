import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {LoginModel} from "../login/login-model";

@Injectable({providedIn: 'root'})
export class AuthenticationService {
    constructor(private http: HttpClient) {
    }

    // /*Old Login function*/
    // login(username: string, password: string) {
    //     let headers = new HttpHeaders();
    //     headers = headers.append('Authorization', 'Basic ' + btoa(`${username}:${password}`));
    //     return this.http.get<any>('/UserData', {headers: headers})
    //         .pipe(map(user => {
    //             if (user) {
    //                 user.authdata = btoa(`${username}:${password}`);
    //                 localStorage.setItem('currentUser', JSON.stringify(user));
    //             }
    //             return user;
    //         }));
    // }
    //New Login function
    login(username: string, password: string) {
        return this.http.post<any>(`/UserData`, {username, password})
            .pipe(map(user => {
                if (user) {
                    user.authdata = btoa(`${username}:${password}`);
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }
                return user;
            }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }

    getAuthorities() {
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser) {
            return currentUser.userAuth;
        }
    }

    getUserId() {
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser) {
            return currentUser.userId;
        }
    }
}
