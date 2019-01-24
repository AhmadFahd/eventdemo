import {Component, Input, OnInit, Output} from '@angular/core';
import {Events} from "../../event/event.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EventsService} from "../../event/events.service";
import {AuthenticationService} from "../../auth/authentication.service";
import {UploadFileService} from "../../upload-file.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-org-events-edit',
    templateUrl: './org-events-edit.component.html',
    styleUrls: ['./org-events-edit.component.css']
})
export class OrgEventsEditComponent implements OnInit {

    fileDownloadUri ;
    id;
    eventForm: FormGroup;
    selectedFiles: FileList;
    currentFileUpload: File;
    uploaded;
    currentEvent;
    progress: { percentage: number } = {percentage: 0};

    constructor(
        private formBuilder: FormBuilder,
        private eventService: EventsService,
        private auth: AuthenticationService,
        private uploadService: UploadFileService,
        private route: ActivatedRoute) {
    }


    ngOnInit() {
        this.route.params.subscribe(value => {
            this.id = value.id;
            this.eventService.getEvent(this.id).subscribe( value1 => {
                if(value1){
                this.eventForm.patchValue(value1 as any);
                this.fileDownloadUri = value1.image;
                this.currentEvent =true;
            }})
        });
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
        this.eventForm.controls.image.setValue(this.fileDownloadUri);
        // console.log(this.eventForm)
        this.eventService.editEvent(this.id , this.eventForm).subscribe();

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


