import {Component, OnInit} from '@angular/core';
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
    error;
    loading;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private userService: UserService) {
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.compose([Validators.required, Validators.pattern(/^[a-zA-Z][a-zA-Z0-9@_.-]{2,}$/)])]
        });
    }

    onSubmit() {
        this.loading = true;
        this.userService.resetRequest(this.loginForm.controls.username.value, window.location.origin + '/reset/')
            .subscribe(value => this.router.navigateByUrl("/login"),
                error1 => {
                    this.loading = false;
                    this.error = error1;
                });
    }
}
