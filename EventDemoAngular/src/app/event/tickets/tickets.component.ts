import { Component, OnInit } from '@angular/core';
import {EventsService} from '../events.service';
import {AuthenticationService} from '../../auth/authentication.service';
import {Observable} from 'rxjs';
import {Tickets} from './tickets.model';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {
  tickets$: Observable<Tickets[]>;
  nonRated$: Observable<Tickets[]>;
  constructor(private eventsServices: EventsService, private auth: AuthenticationService) { }

  ngOnInit() {
    this.getMyTickets();
  }
  getMyTickets() {
    this.tickets$ = this.eventsServices.getMyTickets(this.auth.getUserId());
    this.nonRated$ = this.eventsServices.getMyNonRatedTickets(this.auth.getUserId());

  }

    cancel(id) {
      console.log('cancel ticket', id);
    this.eventsServices.cancelTicket(id).subscribe();
        location.reload();
  }
    rate(value, eid) {
    this.eventsServices.rate(value, this.auth.getUserId(), eid).subscribe();
    }
}
