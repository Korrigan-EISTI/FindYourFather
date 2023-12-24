import { Component } from '@angular/core';

@Component({
	selector: 'app-auth',
	templateUrl: './auth.component.html',
	styleUrls: ['./auth.component.css'],
})
export class AuthComponent {
	formData: any = {};
	logged: boolean = false;
	saveFormData : FormData = new FormData();
	
	onRegisterSubmit() {
		const form: HTMLFormElement = document.getElementById('registerForm') as HTMLFormElement;
		const formData = new FormData(form);
		const url = '/register'; 
		
		fetch(url, {
			method: 'post',
			body: new URLSearchParams(formData as any)
		}).then(response => {
			if (!response.ok) {
				throw new Error(response.statusText);
			}
			
			response.text().then(text => {
				if (text === "exists"){
					(document.getElementById("AlreadyExist") as HTMLElement).style.visibility = "visible";
				}
				else{
					(document.getElementById("registerStatus")!.innerHTML = text);
				}
				
				this.saveFormData = formData;
				
			});
			
		});

		return false;
	}

	onLoginSubmit() {
		const form: HTMLFormElement = document.getElementById('loginForm') as HTMLFormElement;
		const formData = new FormData(form);
		const url = '/login';

		fetch(url, {
			method: 'post',
			body: new URLSearchParams(formData as any)
		}).then(response => {
			if (!response.ok) {
				throw new Error(response.statusText);
			}
			response.text().then(text => (document.getElementById("loginStatus")!.innerHTML = text));
		});
		this.logged = true;
		return false;
	}
	
	createUserAlreadyExited(){
		const url = '/registerAlreadyExisted';
		
		fetch(url, {
			method: 'post',
			body: new URLSearchParams(this.saveFormData as any)
		}).then(response => {
			if (!response.ok) {
				throw new Error(response.statusText);
			}
			(document.getElementById("AlreadyExist") as HTMLElement).style.visibility = "hidden";
			response.text().then(text => (document.getElementById("registerStatus")!.innerHTML = text));
		});
		this.logged = true;
		return false;
	}
	
	logout() {
		this.logged = false;
	}
}
