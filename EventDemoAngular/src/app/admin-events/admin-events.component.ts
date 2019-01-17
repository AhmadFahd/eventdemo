import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Events} from '../event/event.model';
import {EventsService} from '../event/events.service';

@Component({
  selector: 'app-admin-events',
  templateUrl: './admin-events.component.html',
  styleUrls: ['./admin-events.component.css']
})
export class AdminEventsComponent implements OnInit {

    events$: Observable<Events[]>;
    unApprovedEvents$: Observable<Events[]>;

    constructor(private eventsService: EventsService) {    }

    ngOnInit() {
        this.getEvents();
        this.getUnapprovedEvents();
    }
    getEvents() {
        this.events$ = this.eventsService.getEvnets();
    }

    getUnapprovedEvents() {
        this.unApprovedEvents$ = this.eventsService.getAllNonApprovedEvents();
    }

    approve(id) {
        this.eventsService.approve(id).subscribe();
    }
}
