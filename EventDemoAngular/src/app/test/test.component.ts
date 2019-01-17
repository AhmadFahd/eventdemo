import {Component, OnInit} from '@angular/core';
import {UserService} from '../user/user.service';
import {Events} from '../event/event.model';
import {EventsService} from '../event/events.service';
import {User} from '../user/user.model';
import {AuthenticationService} from '../auth/authentication.service';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {UploadFileService} from '../upload-file.service';
import {HttpEventType, HttpResponse} from '@angular/common/http';

function passwordMatcher(control: AbstractControl) {
    return control.get('password').value === control.get('confirm').value
        ? null : {'nomatch': true};
}

@Component({
    selector: 'app-test',
    templateUrl: './test.component.html',
    styleUrls: ['./test.component.scss']
})

export class TestComponent implements OnInit {

    fileDownloadUri = 'defaultEventImage';
    eventForm: FormGroup;
    myReactiveForm: FormGroup;
    selectedFiles: FileList;
    currentFileUpload: File;
    uploaded;
    progress: { percentage: number } = {percentage: 0};

    constructor(
        private formBuilder: FormBuilder,
        private eventService: EventsService,
        private auth: AuthenticationService,
        private uploadService: UploadFileService) {
    }

    ngOnInit() {
        this.eventForm = this.formBuilder.group({
            name: ['', Validators.required],
            capacity: ['', Validators.required],
            overview: '',
            date: ['', Validators.required],
            time: '',
            image: '',
            minage: '',
            gender: '',
            category: '',
            city: '',
            location: ''
        });
    }
    onSubmit() {
        this.eventService.addEvent(this.auth.getUserId() , JSON.stringify(this.eventForm.value)).subscribe();
    }

    onSubmit() {
        // console.log(this.myReactiveForm)
        this.myReactiveForm.controls.image.setValue(this.fileDownloadUri);
        this.eventService.addEvent(this.auth.getUserId() , JSON.stringify(this.eventForm.value)).subscribe();

    }
    selectFile(event) {
        this.selectedFiles = event.target.files;
    }

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

