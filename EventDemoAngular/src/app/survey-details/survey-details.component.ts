import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Events} from "../event/event.model";
import {Observable} from "rxjs";
import {Comments} from "../event/comment.model";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../auth/authentication.service";
import {EventsService} from "../event/events.service";

@Component({
  selector: 'app-survey-details',
  templateUrl: './survey-details.component.html',
  styleUrls: ['./survey-details.component.css']
})
export class SurveyDetailsComponent implements OnInit {
    id: number;
    err;
    err1;
    commentForm: FormGroup;
    currentEvent: Events;

    comments: Observable<Comments>;
    orgRate = 0;
    voteCounter;
    isVoted;
    noContent;
    percentage;

    constructor(private formBuilder: FormBuilder,
                private route: ActivatedRoute,
                private router: Router,
                private auth: AuthenticationService,
                private eventsService: EventsService) {
    }

    ngOnInit() {
        this.route.params.subscribe((param: any) => {
            this.id = param.id;
            this.eventsService.getSurvey(this.id).subscribe(value => {
                this.currentEvent = value;
                this.eventsService.getOrgRate(this.currentEvent.organizer.id).subscribe(value =>
                    this.orgRate = value);
                this.eventsService.votesCounter(this.id).subscribe(value => {
                    this.voteCounter = value;}
                    );
                this.eventsService.checkVoted(this.auth.getUserId(),this.id).subscribe(
                    value1 => this.isVoted = value1
                );
            },error1 => this.noContent = true);
        });

        this.commentForm = this.formBuilder.group({
            comment: ['', Validators.required]
        });
        // todo: add a button to view comments
        this.getComments();
    }

    onSubmit() {

        this.eventsService.addComment(this.commentForm, this.id, this.auth.getUserId()).subscribe(
            value => {this.err1 = null; this.ngOnInit(); },error1 =>  this.err1=error1);

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

    voteSurvey() {
        this.eventsService.addVote(this.auth.getUserId(),this.id).subscribe(
        value =>  this.ngOnInit()
        );

        // this.eventsService.bookTicket(this.id, this.auth.getUserId()).subscribe(value => {
        //     this.router.navigateByUrl("/tickets");
        // }, error1 => {
        //     this.err = error1;
        //     console.log(this.err);
        // });

    }

}
