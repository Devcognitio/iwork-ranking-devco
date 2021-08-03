import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';
import { DxDataGridModule, DxSelectBoxModule } from 'devextreme-angular';
import { TranslateModule } from '@ngx-translate/core';
import 'hammerjs';

import { FuseModule } from '@fuse/fuse.module';
import { FuseSharedModule } from '@fuse/shared.module';
import { FuseProgressBarModule, FuseSidebarModule, FuseThemeOptionsModule, FuseConfirmDialogModule } from '@fuse/components';

import { fuseConfig } from 'app/fuse-config';

import { AppComponent } from 'app/app.component';
import { LayoutModule } from 'app/layout/layout.module';
import { LinkedInRankModule } from 'app/main/linkedin-rank/linkedin-rank.module';
import { MaterialModule } from '@fuse/material.module';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ApiService } from './services/api.service';

const appRoutes: Routes = [
    {
        path: '**',
        redirectTo: 'linkedin-rank'
    }
];

@NgModule({
    declarations: [
        AppComponent
    ],
    entryComponents: [

    ],

    imports: [
        BrowserModule,
        FormsModule,
        CommonModule,
        BrowserAnimationsModule,
        HttpClientModule,
        RouterModule.forRoot(appRoutes),

        TranslateModule.forRoot(),
        // Fuse modules
        FuseModule.forRoot(fuseConfig),
        FuseProgressBarModule,
        MaterialModule,
        FuseSharedModule,
        FuseSidebarModule,
        FuseThemeOptionsModule,
        FuseConfirmDialogModule,
        // App modules
        LayoutModule,
        FormsModule,
        CommonModule,         
        DxDataGridModule,
        DxSelectBoxModule,
        LinkedInRankModule
    ],
    providers: [
        ApiService
    ],
    bootstrap: [
        AppComponent
    ]
})
export class AppModule {
}
