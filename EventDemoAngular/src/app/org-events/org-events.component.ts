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
    editable = false;
    eventForm: FormGroup;

    constructor(private eventsService: EventsService,
                private auth: AuthenticationService,
                private formBuilder: FormBuilder) {
    }

    ngOnInit() {
        this.getEvents();
        this.getUnaprrovedEvents();
        this.eventForm = this.formBuilder.group({
            name: ['', Validators.required],
            capacity: ['', Validators.required],
            overview: '',
            date: ['', Validators.required],
            time: '',
            image: '',
            minage: '',
            gender: '',
            category: '',
            city: '',
            location: ''
        });
    }

    getEvents() {
        this.events$ = this.eventsService.getMyEvnets(this.auth.getUserId());
    }

    getUnaprrovedEvents() {
        this.unAprrovedEvents$ = this.eventsService.getUnaprrovedEvents(this.auth.getUserId());
    }

    edit(event) {
        this.editable = true;
        this.eventForm.patchValue(event);
    }

    onSubmit() {
        console.log(this.eventForm);
        this.editable = false;
    }

    delete(id) {
        console.log(id);
    }
}
