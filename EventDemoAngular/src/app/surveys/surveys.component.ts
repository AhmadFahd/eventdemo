import {Component, OnInit} from '@angular/core';
import {Events} from "../event/event.model";
import {EventsService} from "../event/events.service";

@Component({
    selector: 'app-surveys',
    templateUrl: './surveys.component.html',
    styleUrls: ['./surveys.component.css']
})
export class SurveysComponent implements OnInit {
    events: Events[];
    constructor(private eventsService: EventsService) {
    }

    ngOnInit() {
        this.getSurveys();
    }

    getSurveys() {
        this.eventsService.getSurveys().subscribe(value => this.events = value);
    }
}
