import { Component, OnInit } from '@angular/core';
import {FeedService} from '../feed.service';
import {AuthenticationService} from '../auth/authentication.service';
import {Feed} from './feed.model';

@Component({
  selector: 'app-feeds',
  templateUrl: './feeds.component.html',
  styleUrls: ['./feeds.component.css']
})
export class FeedsComponent implements OnInit {
feeds: Feed[];
  constructor(private feedService: FeedService,
              private auth: AuthenticationService) { }

  ngOnInit() {
      this.feedService.myFeeds(this.auth.getUserId()).subscribe(value =>
          this.feeds = value.reverse()
      );
  }

}
