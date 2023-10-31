import { Component } from '@angular/core';

@Component({
	selector: 'app-auth',
	templateUrl: './auth.component.html',
	styleUrls: ['./auth.component.css']
})
export class AuthComponent {
	loginEmail: string = '';
	loginPassword: string = '';

	registrationEmail: string = '';
	registrationPassword: string = '';
	registrationSsn: string = '';
	registrationLastname: string = '';
	registrationFirstname: string = '';
	registrationBirthdate: string = '';
	registrationNationality: string = '';
	registrationGender: string = '';

	onLoginSubmit() {
		console.log('Login Form Submitted:', this.loginEmail, this.loginPassword);
	}

	onRegisterSubmit() {
		console.log(
			'Registration Form Submitted:',
			this.registrationEmail,
			this.registrationPassword,
			this.registrationSsn,
			this.registrationLastname,
			this.registrationFirstname,
			this.registrationBirthdate,
			this.registrationNationality,
			this.registrationGender
		);
	}
}
