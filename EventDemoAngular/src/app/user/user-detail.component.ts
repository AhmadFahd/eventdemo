import {Component, OnDestroy, OnInit} from '@angular/core';

import {ActivatedRoute} from '@angular/router';
import {Observable, Subscription} from 'rxjs';
import {map, catchError, delay} from 'rxjs/operators';
import {User} from './user.model';
import {UserService} from './user.service';

@Component({
    selector: 'app-user-datail',
    templateUrl: 'user-datail.component.html'
})
export class UserDetailComponent implements OnInit, OnDestroy {
    id: number;
    currentUser$: Observable<User>;
    private sub: Subscription;

    constructor(private route: ActivatedRoute, private userService: UserService) {
    }


  ngOnInit() {
        this.sub = this.route.params.subscribe((param: any) => {
      this.id = param.id;
      this.currentUser$ = this.userService.getUser(this.id);
    });

  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}

