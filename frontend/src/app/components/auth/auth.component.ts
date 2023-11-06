import { Component } from '@angular/core';
@Component({
	selector: 'app-auth',
	templateUrl: './auth.component.html',
	styleUrls: ['./auth.component.css']
})
export class AuthComponent {
	formData: any = {};


	onRegisterSubmit() {
		const form: HTMLFormElement = document.getElementById('registerForm') as HTMLFormElement;
		const formData = new FormData(form)
		// Send the form data to a page using the FormData API and JavaScript
		const url = '/register'; // Replace with your target page URL

		fetch(url, {
			method: 'post',
			body: new URLSearchParams(formData as any)
		}).then(response => {
			if (!response.ok) {
			  throw new Error(response.statusText)
			}
			response.text().then(text=>document.getElementById("status")!.innerHTML = text);
		  });

		return false;
	}

	onLoginSubmit(){
}
}
