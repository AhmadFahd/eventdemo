import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../auth/authentication.service';
import {UserService} from '../user/user.service';

@Component({
  selector: 'app-pass-forget',
  templateUrl: './pass-forget.component.html',
  styleUrls: ['./pass-forget.component.css']
})
export class PassForgetComponent implements OnInit {

    loginForm: FormGroup;
    error = '';

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private userService: UserService,
        private authenticationService: AuthenticationService) {
    }

    ngOnInit() {
        console.log(window.location.origin);
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required]
        });
    }

    onSubmit() {
//todo: request by email , send origin to be handled by backend
       this.userService.resetRequest(this.loginForm.controls.username.value).subscribe();
    }
}
