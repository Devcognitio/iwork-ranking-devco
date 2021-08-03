import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';

import { FuseSharedModule } from '@fuse/shared.module';

import { LinkedInRank } from './linkedin-rank.component';
import { FileUploadModule } from 'ng2-file-upload';
import { MatDialogModule } from '@angular/material';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from '@fuse/material.module';
import { DxDataGridModule, DxSelectBoxModule, DevExtremeModule } from 'devextreme-angular';

const routes = [
    {
        path: 'linkedin-rank',
        component: LinkedInRank
    }
];

@NgModule({
    declarations: [
        LinkedInRank
    ],
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FormsModule,

        DxDataGridModule,
        DxSelectBoxModule,
        TranslateModule,
        MatDialogModule,
        FuseSharedModule,
        MaterialModule,
        FileUploadModule

    ],
    exports: [
        LinkedInRank
    ]
})

export class LinkedInRankModule {
}
