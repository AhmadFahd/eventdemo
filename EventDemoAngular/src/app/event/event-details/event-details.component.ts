import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {EventsService} from '../events.service';
import {Observable, Subscription} from 'rxjs';
import {User} from '../../user/user.model';
import {Events} from '../event.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../auth/authentication.service';
import {Comments} from '../comment.model';

@Component({
    selector: 'app-event-details',
    templateUrl: './event-details.component.html',
    styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {
    id: number;
    commentForm: FormGroup;
    currentEvent: Events;
    private sub: Subscription;
    comments: Observable<Comments>;
    time: number;
    orgRate = 0;
    eventCounter;
    percentage;
    models;

    constructor(private formBuilder: FormBuilder,
                private route: ActivatedRoute,
                private router: Router,
                private auth: AuthenticationService,
                private eventsService: EventsService) {
    }

    ngOnInit() {
        this.route.params.subscribe((param: any) => {
            this.id = param.id;
            this.eventsService.getEvent(this.id).subscribe(value => {
                this.currentEvent = value;
                this.eventsService.getOrgRate(this.currentEvent.organizer.id).subscribe(value =>
                    this.orgRate = value);
                this.eventsService.eventCounter(this.id).subscribe(value => {
                    this.eventCounter = value;
                    this.percentage= this.eventCounter/this.currentEvent.capacity*100;
                });
            });
        });

        this.commentForm = this.formBuilder.group({
            comment: ['', Validators.required]
        });
        // todo: add a button to view comments
        this.getComments();
    }

    onSubmit() {
        if (!this.time || this.time <= Date.now() - 60000) {
            // console.log('Accepted');
            this.time = Date.now();
            this.eventsService.addComment(this.commentForm, this.id, this.auth.getUserId()).subscribe(
                value => this.ngOnInit());
        } else {
            // console.log('wait');
        }
    }

    getComments() {
        this.comments = this.eventsService.getComments(this.id);
    }

    bookTicket() {
        this.eventsService.bookTicket(this.id, this.auth.getUserId()).subscribe();
        this.router.navigateByUrl("/tickets");
    }

}
