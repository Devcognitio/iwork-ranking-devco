import { Injectable } from '@angular/core';

@Injectable()
export class ApiService {

    public protocol: string;
    public baseUrl: string;
    public apiBaseUrl: string;
    public apiUrl: string;    

    constructor() {
        
        this.protocol = 'http://';
        this.apiBaseUrl = '35.229.115.188:8080';
        this.baseUrl = this.protocol + this.apiBaseUrl + '/';
        this.apiUrl = this.baseUrl + 'iWork Company/';
        console.log("URL Server:" + this.apiUrl );
    }

}