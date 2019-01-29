import {Component, EventEmitter , OnInit, Output} from '@angular/core';
import {RoleEnum} from '../shared/roles';
import {AuthenticationService} from '../auth/authentication.service';
import {RequestService} from '../role-request/request.service';
import {ActivatedRoute, Router} from '@angular/router';
import {} from "selenium-webdriver";
import {isBoolean} from "util";

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
    adm;
    user;
    org;
    auths = this.authService.getAuthorities();

    @Output() booleanGenerated = new EventEmitter<string>();

    constructor(private authService: AuthenticationService,
                private reqService: RequestService,
                private route: ActivatedRoute,
                private router: Router) {}
    ngOnInit() {
        this.getAuthorities();
    }

    getAuthorities() {
        let user = false;
        let adm = false;
        let org = false;
        this.auths.forEach(function (value) {
            if (value.authority === RoleEnum.USER) {
                user = true;
            }
            if (value.authority === RoleEnum.ADMIN) {
                adm = true;
            }
            if (value.authority === RoleEnum.ORGANIZER) {
                org = true;
            }
        });
        this.user = user;
        this.adm = adm;
        this.org = org;
    }

    AdminRequest() {
        this.reqService.requestAdmin(this.authService.getUserId()).subscribe();
    }

    OrgRequest() {
        this.reqService.requestOrg(this.authService.getUserId()).subscribe();
    }

    search(user) {
        this.booleanGenerated.emit(user);
        this.router.navigateByUrl('/search/' + user);
    }
}
