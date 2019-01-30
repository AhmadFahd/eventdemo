import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../user/user.service';
import {AuthenticationService} from '../auth/authentication.service';

function passwordMatcher(control: AbstractControl) {
    return control.get('password').value === control.get('confirm').value
        ? null : {'nomatch': true};
}
@Component({
  selector: 'app-pass-reset',
  templateUrl: './pass-reset.component.html',
  styleUrls: ['./pass-reset.component.css']
})
export class PassResetComponent implements OnInit {

    loginForm: FormGroup;
    error = '';
    id;
    active;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private userService: UserService
    ) {
    }

    ngOnInit() {
        this.route.params.subscribe((value: any ) => {
            this.id = value.id;
        });
        this.userService.checkActive(this.id).subscribe(value =>
            this.active = value);
        this.loginForm = this.formBuilder.group({
            password: ['', Validators.required],
            confirm: ['', Validators.required]
        }, {
            validator: passwordMatcher // pass in the validator function
        });
    }

    onSubmit() {
        this.userService.resetPassword(this.id, this.loginForm).subscribe(value => this.router.navigateByUrl("/login"));
    }
}
