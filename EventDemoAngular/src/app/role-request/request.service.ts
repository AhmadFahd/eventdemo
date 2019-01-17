import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(private http: HttpClient) { }

  getRoleRequests(): Observable<any> {
    return this.http.get(`/api/request/all`);
  }
    accept(id): Observable <any> {
      return this.http.get(`api/request/accept/${id}`);
    }

    requestAdmin(id): Observable <any> {
        return this.http.get(`api/request/admin/${id}`);
    }
    requestOrg(id): Observable <any> {
        return this.http.get(`api/request/org/${id}`);
    }
}
