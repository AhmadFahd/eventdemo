import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {User} from '../user/user.model';
import {UserService} from '../user/user.service';
import {AuthenticationService} from '../auth/authentication.service';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {UploadFileService} from '../upload-file.service';
import {HttpEventType, HttpResponse} from '@angular/common/http';

function passwordMatcher(control: AbstractControl) {
    return control.get('password').value === control.get('confirm').value
        ? null : {'nomatch': true};
}
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
    myReactiveForm: FormGroup;
    currentUser: User;
    editable;
    selectedFiles: FileList;
    currentFileUpload: File;
    fileDownloadUri;
    uploaded;
    progress: { percentage: number } = {percentage: 0};

    constructor(private formBuilder: FormBuilder,
                private router: Router,
                private userService: UserService,
                private uploadService: UploadFileService,
                private auth: AuthenticationService) {
    }

    ngOnInit() {
        this.userService.getUser(this.auth.getUserId()).subscribe((value0 => {
            this.currentUser = value0;
            this.myReactiveForm.patchValue(value0 as any);
            this.fileDownloadUri = this.currentUser.icon;
        }));
        this.myReactiveForm = this.formBuilder.group({
            email: ['', Validators.compose([Validators.required, Validators.email])],
            username: ['', Validators.compose([Validators.required, Validators.pattern(/^[a-zA-Z]/)])],
            password: ['', Validators.compose([Validators.required, Validators.minLength(6), Validators.pattern(/^[a-zA-Z]/)])],
            confirm: ['', Validators.compose([Validators.required, Validators.minLength(6)])],
            gender: ``,
            firstname: ``,
            midname: ``,
            lastname: ``,
            phone: ['', [Validators.required, Validators.pattern(/(05)\d{8}/)]],
            dob: '',
            icon: ''

        }, {
            validator: passwordMatcher // pass in the validator function
        });
    }

    edit() {
        this.editable = true;
    }

    onSubmit() {
        this.myReactiveForm.controls.icon.setValue(this.fileDownloadUri);
        console.log(this.myReactiveForm.value);
        this.editable = false;
        return this.userService.updateUser(this.auth.getUserId(), this.myReactiveForm).subscribe(value =>
            this.ngOnInit());
    }

    selectFile(event) {
        this.selectedFiles = event.target.files;
    }

    upload(file) {
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

    cancel() {
        this.editable = false;
    }
}
