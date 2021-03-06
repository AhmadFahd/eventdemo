import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {EventsService} from '../events.service';
import {Observable, Subscription} from 'rxjs';
import {User} from '../../user/user.model';
import {Events} from '../event.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../auth/authentication.service';
import {Comments} from '../comment.model';
import {log} from "util";

@Component({
    selector: 'app-event-details',
    templateUrl: './event-details.component.html',
    styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {
    id: number;
    err;
    err1;
    commentForm: FormGroup;
    currentEvent: Events;
    noContent;

    comments: Observable<Comments>;
    orgRate = 0;
    eventCounter;
    percentage;
    deleted;
    survey;

    constructor(private formBuilder: FormBuilder,
                private route: ActivatedRoute,
                private router: Router,
                private auth: AuthenticationService,
                private eventsService: EventsService) {
    }

    ngOnInit() {
        this.route.params.subscribe((param: any) => {
            this.id = param.id;

            this.eventsService.isSurvey(this.id).subscribe(value1 => {
                this.survey = value1;
                if (this.survey) {
                    this.router.navigateByUrl(`/survey/${this.id}`);
                } else {
                    this.eventsService.getEvent(this.id).subscribe(value => {
                        this.currentEvent = value;
                        this.eventsService.getOrgRate(this.currentEvent.organizer.id).subscribe(value =>
                            this.orgRate = value);
                        this.eventsService.eventCounter(this.id).subscribe(value => {
                            this.eventCounter = value;
                            this.percentage = this.eventCounter / this.currentEvent.capacity * 100;
                        });
                    },error1 => this.noContent = true);
                }
            });
        });

        this.commentForm = this.formBuilder.group({
            comment: ['', Validators.required]
        });
        // todo: add a button to view comments
        this.getComments();
    }

    onSubmit() {
        this.eventsService.addComment(this.commentForm, this.id, this.auth.getUserId()).subscribe(
            value => {
                this.err1 = null;
                this.ngOnInit();
            }, error1 => this.err1 = error1);

        // if (!this.time || this.time <= Date.now() - 60000) {
        //     console.log('Accepted');
        // this.time = Date.now();
        // this.eventsService.addComment(this.commentForm, this.id, this.auth.getUserId()).subscribe(
        //     value => this.ngOnInit());
        // } else {
        //     console.log('wait');
        // }
    }

    getComments() {
        this.comments = this.eventsService.getComments(this.id);
    }

    bookTicket() {
        this.eventsService.bookTicket(this.id, this.auth.getUserId()).subscribe(value => {
            this.router.navigateByUrl("/tickets");
        }, error1 => {
            this.err = error1;
            console.log(this.err);
        });

    }

}
