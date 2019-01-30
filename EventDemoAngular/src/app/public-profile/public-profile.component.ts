import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {User} from '../user/user.model';
import {UserService} from '../user/user.service';
import {AuthenticationService} from '../auth/authentication.service';
import {ActivatedRoute, NavigationEnd, NavigationStart, Router} from '@angular/router';
import {Feed} from '../feeds/feed.model';
import {FeedService} from '../feed.service';

@Component({
    selector: 'app-public-profile',
    templateUrl: './public-profile.component.html',
    styleUrls: ['./public-profile.component.css']
})
export class PublicProfileComponent implements OnInit {
    currentUser: User;
    id;
    err;
    name;
    followed;
    feeds: Feed[];
    myId = this.auth.getUserId();

    constructor(private userService: UserService,
                private route: ActivatedRoute,
                private auth: AuthenticationService,
                private router: Router,
                private feedService: FeedService) { }

    onBooleanGenerated(value) {

        this.userService.getUserByUsername(value).subscribe((value0 => {
            this.currentUser = value0;
            this.err=null;
            this.name = this.currentUser.username;
            this.id = this.currentUser.id;
            this.userService.isFollowed(this.myId, this.id).subscribe(
                value => {
                    this.followed = value;
                });
            this.feedService.userFeeds(this.id).subscribe(value =>
                this.feeds = value
            );
        }),error1 => {this.err=true ; this.currentUser = null});
    }

    ngOnInit() {
        if (this.router.url.startsWith('/search')) {
            this.route.params.subscribe((param: any) =>
                this.name = param.name);
            this.userService.getUserByUsername(this.name).subscribe((value0 => {
                this.currentUser = value0;
                this.err=null;
                this.id = this.currentUser.id;
                this.userService.isFollowed(this.myId, this.id).subscribe(
                    value => {
                        this.followed = value;
                    });
                this.feedService.userFeeds(this.id).subscribe(value =>
                    this.feeds = value
                );
            }),error1 => {this.err=true ; this.currentUser = null});

        }
        if (this.router.url.startsWith('/profile')) {
            this.route.params.subscribe((param: any) =>
                this.id = param.id);
            this.userService.getUser(this.id).subscribe((value0 => {
                this.currentUser = value0;
                this.err=null;
                this.userService.isFollowed(this.myId, this.id).subscribe(
                    value => {
                        this.followed = value;
                    });
                this.feedService.userFeeds(this.id).subscribe(value =>
                    this.feeds = value
                );
            }),error1 => {this.err=true ; this.currentUser = null});
        }
    }

    follow() {
        this.userService.Follow(this.myId, this.id).subscribe();
        this.ngOnInit();
    }

        unfollow() {
        this.userService.unFollow(this.myId, this.id).subscribe();
        this.ngOnInit();
    }

    nav(id) {
        this.id = id;
        this.userService.getUser(this.id).subscribe((value0 => {
            this.currentUser = value0;
            this.userService.isFollowed(this.myId, this.id).subscribe(
                value => {
                    this.followed = value;
                });
            this.feedService.userFeeds(this.id).subscribe(value =>
                this.feeds = value
            );
            this.router.navigateByUrl(`/profile/${id}`);
        }));
    }


}
