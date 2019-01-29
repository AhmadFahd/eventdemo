import {Component, OnDestroy, OnInit} from '@angular/core';

import {ActivatedRoute} from '@angular/router';
import {Observable, Subscription} from 'rxjs';
import {map, catchError, delay} from 'rxjs/operators';
import {User} from '../user.model';
import {UserService} from '../user.service';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {UploadFileService} from "../../upload-file.service";

function passwordMatcher(control: AbstractControl) {
    return control.get('password').value === control.get('confirm').value
        ? null : {'nomatch': true};
}

@Component({
    selector: 'app-user-datail',
    templateUrl: 'user-datail.component.html',
    styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {
    id: number;
    dd: number;
    constructor(private route: ActivatedRoute, private userService: UserService,
                private formBuilder: FormBuilder,
                private uploadService: UploadFileService) {
    }

    myReactiveForm: FormGroup;
    currentUser: User;
    selectedFiles: FileList;
    currentFileUpload: File;
    fileDownloadUri;
    uploaded;
    progress: { percentage: number } = {percentage: 0};

  ngOnInit() {
        this.route.params.subscribe((param: any) => {
            this.id = param.id;
    });
      this.userService.getUser(this.id).subscribe((value0 => {
          if(value0) {
              this.currentUser = value0;
              this.myReactiveForm.patchValue(value0 as any);
              this.fileDownloadUri = this.currentUser.icon;
          }}));
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
        this.myReactiveForm.controls.icon.setValue(this.fileDownloadUri);
        console.log(this.myReactiveForm.value);
        return this.userService.updateUser(this.id, this.myReactiveForm).subscribe(value =>
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
}
