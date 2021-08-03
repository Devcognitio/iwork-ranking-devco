import { Component, ViewChild } from '@angular/core';

import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';

import { FileUploader } from "ng2-file-upload"
import { FuseConfirmDialogComponent } from '@fuse/components/confirm-dialog/confirm-dialog.component';
import { MatDialog, MatDialogRef } from '@angular/material';
import { ApiService } from 'app/services/api.service';
import {saveAs as importedSaveAs} from "file-saver";

@Component({
    selector: 'linkedin-rank',
    templateUrl: './linkedin-rank.component.html',
    styleUrls: ['./linkedin-rank.component.scss']
})
export class LinkedInRank {

    @ViewChild('fileInput') fileInput: any;
    uploader: FileUploader;
    profiles: any = [];
    showGrid: boolean;
    confirmDialogRef: MatDialogRef<FuseConfirmDialogComponent>;
    textData: string;


    /**
     * Constructor
     *
     * @param {FuseTranslationLoaderService} _fuseTranslationLoaderService
     */
    constructor(
        private _apiService: ApiService,
        private _matDialog: MatDialog

    ) {

        this.uploader = new FileUploader({
            url: _apiService.apiUrl + "/linkedin-rank/uploadFile"
        });

        this.uploader.onAfterAddingFile = (fileItem) => {
            this.uploader.clearQueue();
            this.uploader.queue[0] = fileItem;
        }

        this.uploader.onCompleteItem = (fileItem, response, status, headers) => {
            this.uploader.clearQueue();
            var actualResponse = JSON.parse(fileItem._xhr.response);
            this.profiles = actualResponse;           
            this.textData = "";
            
            for (const iterator of actualResponse) {
                this.textData += iterator.personId + "\n"; 
            }
            this.downloadFile(this.textData);
        }

        this.uploader.onWhenAddingFileFailed = () => {

            this.confirmDialogRef = this._matDialog.open(FuseConfirmDialogComponent, {
                disableClose: false
            });

            this.confirmDialogRef.componentInstance.confirmMessage =  "Document cannot be uploaded. Please verify the document is a text plain file and maximum file size 2GB.";
        }

        this.uploader.onErrorItem = (errorData) => {
            let msg = JSON.parse(errorData._xhr.response);
            if (msg.status != -1) {

            }
        }
    }

    public uploadAll() {        
        this.uploader.uploadAll();
        this.uploader.onCompleteAll = () => { this.fileInput.nativeElement.value = ''; }        
        this.showGrid = true;
    }

    downloadFile(data: any) {
        const blob = new Blob([data], { type: 'text/plain' });
        importedSaveAs(blob, "people.out");
      }

}
