import { Component, OnInit } from '@angular/core';
import {RequestsModel} from './requests.model';
import {UserService} from '../user/user.service';
import {AuthenticationService} from '../auth/authentication.service';
import {RequestService} from './request.service';

@Component({
  selector: 'app-role-request',
  templateUrl: './role-request.component.html',
  styleUrls: ['./role-request.component.css']
})
export class RoleRequestComponent implements OnInit {
requests: RequestsModel[];
    displayedColumns = ['username', 'Request', 'Accept'];

    constructor(private roleRequest: RequestService) { }

  ngOnInit() {
      this.roleRequest.getRoleRequests().subscribe(
          value => this.requests = value);
  }
    accept(id) {
        this.roleRequest.accept(id).subscribe();
    }
}
