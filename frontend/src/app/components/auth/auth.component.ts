import { Component } from '@angular/core';

@Component({
	selector: 'app-auth',
	templateUrl: './auth.component.html',
	styleUrls: ['./auth.component.css'],
})
export class AuthComponent {
	
	formData: any = {isForeigner: false,ssn: '',};
	saveFormData : FormData = new FormData();
	
	onIsForeignerChange() {
        // Si la case à cocher isForeigner est cochée, définir la valeur du SSN à 99
        if (this.formData.isForeigner) {
            this.formData.ssn = '99';
        } else {
            this.formData.ssn = ''; // Réinitialiser la valeur du SSN si la case est décochée
        }
    }
	
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
		return false;
	}
	
	logout() {
	    fetch('/logout', {
	        method: 'post',
	    }).then(response => {
	        if (!response.ok) {
	            throw new Error(response.statusText);
	        }
	        return response.text();
	    }).then(text => {
	        document.getElementById("loginStatus")!.innerHTML = text;
	    }).catch(error => {
	        console.error('Error during logout:', error);
	        document.getElementById("loginStatus")!.innerHTML = "An error occurred during logout.";
	    });
	    return false;
	}

}
