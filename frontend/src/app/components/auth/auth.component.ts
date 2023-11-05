import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Component({
	selector: 'app-auth',
	templateUrl: './auth.component.html',
	styleUrls: ['./auth.component.css']
})
export class AuthComponent {
	formData: any = {};
	
	constructor(private http: HttpClient) {}

	onRegisterSubmit() {
		const url = '/register'; // Replace with your target page URL
		this.http.post(url, this.formData);
	}
	onLoginSubmit(){

	}
}
