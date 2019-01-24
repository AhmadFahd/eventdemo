import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Events} from './event.model';
import {EventsService} from './events.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {
    events: Events[];

    constructor(private eventsService: EventsService) {
    }

    ngOnInit() {
        this.getEvents();
    }

    getEvents() {
        this.eventsService.getEvnets().subscribe(value => this.events = value);
    }
}
