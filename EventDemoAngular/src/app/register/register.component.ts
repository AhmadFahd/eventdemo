import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {User} from '../user/user.model';
import {HttpClient, HttpEventType, HttpResponse} from '@angular/common/http';
import {UserService} from '../user/user.service';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Route, Router} from '@angular/router';
import {UploadFileService} from '../upload-file.service';

function passwordMatcher(control: AbstractControl) {
    return control.get('password').value === control.get('confirm').value
        ? null : {'nomatch': true};
}

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
    myReactiveForm: FormGroup;
    user$: Observable<User>;
    selectedFiles: FileList;
    emailErr;
    userErr;
    currentFileUpload: File;
    fileDownloadUri = 'defaultProfileImage';
    uploaded;
    progress: { percentage: number } = {percentage: 0};

    constructor(private formBuilder: FormBuilder,
                private router: Router,
                private userService: UserService,
                private uploadService: UploadFileService) {
    }

    ngOnInit() {
        this.myReactiveForm = this.formBuilder.group({
            email: ['', Validators.compose([Validators.required, Validators.email])],
            username: ['', Validators.compose([Validators.required, Validators.pattern(/^[a-zA-Z][a-zA-Z0-9_.-]{2,17}$/)])],
            password: ['', Validators.compose([Validators.required, Validators.minLength(6), Validators.pattern(/^[a-zA-Z]/)])],
            confirm: ['', Validators.compose([Validators.required, Validators.minLength(6)])],
            gender: [``, Validators.required],
            firstname: [``, Validators.required],
            midname: ``,
            lastname: ``,
            phone: [``, [Validators.required, Validators.pattern(/(05)\d{8}/)]],
            dob: [``, Validators.required],
            icon: ``

        }, {
            validator: passwordMatcher // pass in the validator function
        });
    }

    onSubmit() {
        // console.log(this.myReactiveForm)
        this.myReactiveForm.controls.icon.setValue(this.fileDownloadUri);
        this.userService.addUser(this.myReactiveForm).subscribe(
            value => this.router.navigateByUrl('/login'),
                err => {
                this.emailErr = null;
                this.userErr = null;
                 if (err.includes('@')){
                     this.emailErr = err;
                 }else {this.userErr = err;}
                });
    }
    selectFile(event) {
        this.selectedFiles = event.target.files;}

    upload() {
        this.currentFileUpload = this.selectedFiles.item(0);
        this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
            if (event.type === HttpEventType.UploadProgress) {
                this.progress.percentage = Math.round(100 * event.loaded / event.total);
            } else if (event instanceof HttpResponse) {
                this.uploaded = true;
                console.log('File is completely uploaded!');
                if (typeof event.body === 'string') {
                    this.fileDownloadUri = JSON.parse(event.body).fileDownloadUri;
                }
                console.log('uploaded : ', this.fileDownloadUri);
            }
        });

        this.selectedFiles = undefined;
    }
}
