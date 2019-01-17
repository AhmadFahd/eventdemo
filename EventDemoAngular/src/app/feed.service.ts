import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Feed} from './feeds/feed.model';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FeedService {

  constructor(private http: HttpClient) { }

  myFeeds(id): Observable<Feed[]> {
    return this.http.get<Feed[]>(`/api/feeds/myfeeds/${id}`);
  }
  userFeeds(id): Observable<Feed[]> {
      return this.http.get<Feed[]>(`/api/feeds/${id}`);
  }
}
