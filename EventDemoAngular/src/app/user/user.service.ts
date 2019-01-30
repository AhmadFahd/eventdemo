import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from './user.model';
const headers = new HttpHeaders().set('Content-Type', 'application/json');
const API_ARGS = {headers: headers};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

    getUsers(): Observable<User[]> {

        return this.http.get<User[]>('/api/users/present');
    }
    getDelUsers(): Observable<User[]> {

        return this.http.get<User[]>('/api/users/disabled');
    }
  addUser(a): Observable<User> {
    return this.http.post<User>(
      `/api/users/create`, JSON.stringify(a.value), API_ARGS);
    }
    getUser(id: number): Observable<User> {
        // return this.http.get<User>(API_URL + `api/users/view/` + id);
        return this.http.get<User>(`/api/users/view/` + `${id}`);
    }

    delUser(id: number): Observable<any> {
        // return this.http.get<User>(API_URL + `api/users/view/` + id);
        return this.http.get(`/api/users/delete/` + `${id}`);
    }
    activeUser(id: number): Observable<any> {
        // return this.http.get<User>(API_URL + `api/users/view/` + id);
        return this.http.get(`/api/users/enable/` + `${id}`);
    }

    getUserByUsername(username: string): Observable<User> {
        // return this.http.get<User>(API_URL + `api/users/view/` + id);
        return this.http.get<User>(`/api/users/name/` + `${username}`);
    }
    updateUser(id: number, a): Observable<User> {
        // return this.http.get<User>(API_URL + `api/users/view/` + id);
        return this.http.put<User>(`/api/users/edit/` + `${id}`, JSON.stringify(a.value), API_ARGS);
    }

    // getAuthorities() {
    //    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    //     if (currentUser.userAuth) {
    //         return currentUser.userAuth;
    //     }
    // }
    isFollowed(uid, fid): Observable<any> {
        return this.http.get(`api/follow/isFollowed/${uid}/${fid}`);
    }
    Follow(uid, fid): Observable<any> {
        return this.http.get(`api/follow/${uid}/${fid}`);
    }
    unFollow(uid, fid): Observable<any> {
        return this.http.get(`api/follow/cancel/${uid}/${fid}`);
    }
    listOfFollowing(id): Observable<any> {
        return this.http.get(`api/follow/followinglist/${id}`);
    }
    resetRequest(name,body): Observable<any> {
      return this.http.post(`/api/reset/${name}`,body);
    }
    resetPassword(id, value): Observable<any> {
        return this.http.post(
            `/api/reset/send/${id}`, JSON.stringify(value.value), API_ARGS);
    }
    checkActive(id): Observable<any> {
        return this.http.get(`/api/reset/check/${id}`);
    }

}
