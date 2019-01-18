import {Component, Input, OnInit} from '@angular/core';
import {UserService} from '../user/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../auth/authentication.service';
import {FeedService} from '../feed.service';
import {EventsService} from '../event/events.service';
import {Tickets} from '../event/tickets/tickets.model';
import {forEach} from "@angular/router/src/utils/collection";

@Component({
  selector: 'app-event-tickets',
  templateUrl: './event-tickets.component.html',
  styleUrls: ['./event-tickets.component.css']
})
export class EventTicketsComponent implements OnInit {

  id;
  tickets: Tickets[];
  forbidden;
    constructor(private userService: UserService,
                private route: ActivatedRoute,
                // private router: Router,
                private eventServices: EventsService,
                private auth: AuthenticationService) {}

    ngOnInit() {
      this.route.params.subscribe(value =>
      this.id = value.id);
      this.eventServices.getEvent(this.id).subscribe(value => {
          if (this.auth.getUserId() != value.organizer.id){this.forbidden = true; console.log(this.forbidden)
          }
              });
      this.eventServices.getEventTickets(this.id).subscribe(value =>
          this.tickets = value
      );
  }

    Chick(id) {
      this.eventServices.chickIn(id).subscribe();
    }
}
