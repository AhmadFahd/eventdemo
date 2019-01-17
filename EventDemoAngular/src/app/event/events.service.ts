import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Events} from './event.model';
import {Tickets} from './tickets/tickets.model';
const headers = new HttpHeaders().set('Content-Type', 'application/json');
const API_ARGS = {headers: headers};

@Injectable({
  providedIn: 'root'
})
export class EventsService {

    constructor(private http: HttpClient) {
    }

    getEvnets(): Observable<Events[]> {
        return this.http.get<Events[]>('/api/events/active');
    }

    getEvent(id: number): Observable<Events> {
        // return this.http.get<User>(API_URL + `api/users/view/` + id);
        return this.http.get<Events>(`/api/events/view/` + `${id}`);
    }
    addComment(comment, eid, uid): Observable<any> {
        // return console.log(`/api/comments/${eid}/${uid}`);
        return this.http.post(`/api/comments/add/${eid}/${uid}`, JSON.stringify(comment.value), API_ARGS);
    }

    getComments(eid): Observable<any> {
        return this.http.get(`/api/comments/view/${eid}`);
    }
    bookTicket(eid, uid): Observable<any> {
        return this.http.get(`/api/tickets/add/${eid}/${uid}`);
    }
    getMyTickets(uid): Observable<any> {
        return this.http.get(`api/tickets/mytickets/${uid}`);
    }
    addEvent(uid, value) {
        return this.http.post(`/api/events/${uid}/add`, value , API_ARGS);
    }
    getMyNonRatedTickets(uid): Observable<any> {
        return this.http.get(`api/tickets/nonrated/${uid}`);
    }
    cancelTicket(tid): Observable<any> {
        return this.http.get(`api/tickets/cancel/${tid}`);
    }

    getMyEvnets(id): Observable<Events[]> {
        return this.http.get<Events[]>(`/api/events/myevents/${id}`);
    }
    getUnaprrovedEvents(id): Observable<Events[]> {
        return this.http.get<Events[]>(`/api/events/mynonapproved/${id}`);
    }
    getEventTickets(id): Observable<Tickets[]> {
        return this.http.get<Tickets[]>(`/api/tickets/event/${id}`);
    }
    rate(r, uid, eid): Observable<any> {
        return this.http.get<any>(`/api/rating/add/` + `${r}/${uid}/${eid}`);
    }
    getAllNonApprovedEvents(): Observable<Events[]> {
    return this.http.get<Events[]>(`/api/events/nonapproved/`);
    }
    approve(id): Observable<any> {
        return this.http.get(`/api/events/approve/${id}`);
    }

}
