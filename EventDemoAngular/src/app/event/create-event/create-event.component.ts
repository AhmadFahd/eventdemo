import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {EventsService} from '../events.service';
import {AuthenticationService} from '../../auth/authentication.service';
import {UploadFileService} from '../../upload-file.service';
import {HttpEventType, HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.scss']
})
export class CreateEventComponent implements OnInit {

    fileDownloadUri = 'defaultEventImage';
    eventForm: FormGroup;
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
        // console.log(this.myReactiveForm)
        this.eventForm.controls.image.setValue(this.fileDownloadUri);
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

