import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Events} from '../event/event.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {EventsService} from '../event/events.service';
import {AuthenticationService} from '../auth/authentication.service';

@Component({
  selector: 'app-org-events',
  templateUrl: './org-events.component.html',
  styleUrls: ['./org-events.component.css']
})
export class OrgEventsComponent implements OnInit {

    events$: Observable<Events[]>;
    unAprrovedEvents$: Observable<Events[]>;
    event:Events;

    constructor(private eventsService: EventsService,
                private auth: AuthenticationService) {
    }

    ngOnInit() {
        this.getEvents();
        this.getUnaprrovedEvents();
    }

    getEvents() {
        this.events$ = this.eventsService.getMyEvnets(this.auth.getUserId());
    }

    getUnaprrovedEvents() {
        this.unAprrovedEvents$ = this.eventsService.getUnaprrovedEvents(this.auth.getUserId());
    }




    delete(id) {
        this.eventsService.cancelEvent(id).subscribe(value => this.ngOnInit());
    }
}
