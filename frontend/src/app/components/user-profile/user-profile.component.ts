import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

export class UserProfile {
	email: string;
	nationalite: string;
	genre: string;
	phoneNumber: string;

	constructor() {
		this.email = '';
		this.nationalite = '';
		this.genre = '';
		this.phoneNumber = '';
	}
}

export class PersonneProfile {
	numeroSecu: string;
	nom: string;
	prenom: string;
	naissance: string;
	nationalite: string;

	constructor() {
		this.numeroSecu = '';
		this.nom = '';
		this.prenom = '';
		this.naissance = '';
		this.nationalite = '';
	}
}


@Component({
	selector: 'app-user-profile',
	templateUrl: './user-profile.component.html',
	styleUrls: ['./user-profile.component.css']
})

export class UserProfileComponent implements OnInit {

	userProfile: UserProfile = new UserProfile();
	personne: PersonneProfile = new PersonneProfile();
	editMode: boolean = false;

	constructor(private router: Router) { }
	ngOnInit(): void {
		this.showUser();
	}

	toggleEditFields(): void {
		this.editMode = !this.editMode;
	}

	saveChanges(): void {
		const saveUser = "/saveUser";
		fetch(saveUser, {
      		method: 'post',
      		body : new URLSearchParams(this.userProfile as any)
    	}).then(response =>  {
      		if (!response.ok) {
				throw new Error(response.statusText);
			}
			
    	});
    	const savePers = "/savePers";
		fetch(savePers, {
      		method: 'post',
      		body : new URLSearchParams(this.personne as any)
    	}).then(response =>  {
      		if (!response.ok) {
				throw new Error(response.statusText);
			}
			
    	});
    	this.router.navigate(['/index']);
    	this.toggleEditFields();
	}
	
	public showUser(){
		const urlUser = '/showUser';
    	fetch(urlUser, {
      	method: 'post',
    	}).then(response =>  {
      	response.json().then(data => {
        	this.userProfile = data;
        	this.userProfile.phoneNumber = '0' + this.userProfile.phoneNumber;
      		});
    	});
    	
    	const urlPers = '/showPers';

    	fetch(urlPers, {
      	method: 'post',
    	}).then(response =>  {
      	response.json().then(data => {
        	this.personne = data;
        	console.log(data);
      		});
    	});
	}
	
	public saveNationality(e : any){
		this.personne.nationalite = e.target.value;
	}
	
	public saveEmail(e : any){
		const email = e.target.value;
		const regex : RegExp = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;

		if (!regex.test(email))
			return;
		
		this.userProfile.email = email;
	}
	
	public savePhone(e : any){
		const phone: String = e.target.value;
		const p : String = this.normalizePhoneNumber(phone);
		
		if (p.length != 10)
			return;
			
		this.personne.nationalite = e.target.value;
	}
	
	private normalizePhoneNumber(phone : String) {
		const p : Array<string> = phone.split(" "); 
		let str : string = "";
		for (let i = 0; i < p.length; i++){
			str += p[i];
		}		
		return str;
	}
}
