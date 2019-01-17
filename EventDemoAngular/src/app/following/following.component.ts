import { Component, OnInit } from '@angular/core';
import {UserService} from '../user/user.service';
import {AuthenticationService} from '../auth/authentication.service';
import {User} from '../user/user.model';

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent implements OnInit {
    users: User[];
    displayedColumns = ['username', 'unFollow'];
    constructor(private userService: UserService,
    private auth: AuthenticationService) {
    }
    ngOnInit() {
        this.listOfFollowing();
    }
    listOfFollowing() { this.userService.listOfFollowing(this.auth.getUserId()).subscribe(value =>
    this.users = value );
    }

    unfollow(id) {
        this.userService.unFollow(this.auth.getUserId(), id).subscribe();
        return this.ngOnInit();
    }

}
