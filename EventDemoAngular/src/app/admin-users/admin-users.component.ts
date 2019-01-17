import {Component, OnInit} from '@angular/core';
import {User} from '../user/user.model';
import {Subscription} from 'rxjs';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../user/user.service';
import {RoleEnum} from '../shared/roles';
import {forEach} from '@angular/router/src/utils/collection';

@Component({
    selector: 'app-admin-users',
    templateUrl: './admin-users.component.html',
    styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {

    users: User[];
    nonAdmin: User[] = [];

    constructor(private route: ActivatedRoute,
                private userService: UserService) {
    }

    ngOnInit() {
        this.userService.getUsers().subscribe(userData => {
            this.users = userData;
            this.giveRole();
        });
    }

    giveRole() {
        this.users.forEach(value => {
            if ((value.roles.length === 1 || value.roles.length === 2)   && !value.roles.pop().roleName.includes(RoleEnum.ADMIN)) {
                this.nonAdmin.push(value);
            }
            // if (value.roles.length === 2 && value.roles.pop().roleName.includes(RoleEnum.ORGANIZER)) {
            //     console.log(value);
            //     this.nonAdmin.push(value);
            // }
        });
    }
}
