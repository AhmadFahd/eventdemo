import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable, Subscription} from 'rxjs';
import {User} from './user.model';
import {ActivatedRoute} from '@angular/router';
import {UserService} from './user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html'
})
export class UserComponent implements OnInit  {
  users: User[];
  public currentUser: User;


  constructor(private route: ActivatedRoute, private userService: UserService) {
  }

  ngOnInit() {
      this.userService.getUsers().subscribe(
          userData => {
              this.users = userData;
          },
          err => console.log(err),
          () => console.log('Getting users complete...')
      );
  }
}

