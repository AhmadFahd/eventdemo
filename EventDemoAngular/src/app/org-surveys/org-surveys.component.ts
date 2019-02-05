import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {Events} from "../event/event.model";
import {EventsService} from "../event/events.service";
import {AuthenticationService} from "../auth/authentication.service";

@Component({
  selector: 'app-org-surveys',
  templateUrl: './org-surveys.component.html',
  styleUrls: ['./org-surveys.component.css']
})
export class OrgSurveysComponent implements OnInit {


    events$: Events[];
    constructor(private eventsService: EventsService,
                private auth: AuthenticationService) {
    }

    ngOnInit() {
        this.getEvents();
    }

    getEvents() {
      this.eventsService.getMySurveys(this.auth.getUserId()).subscribe(data => this.events$ = data);
    }





    delete(id) {
        this.eventsService.cancelEvent(id).subscribe(value => this.ngOnInit());
    }
}

