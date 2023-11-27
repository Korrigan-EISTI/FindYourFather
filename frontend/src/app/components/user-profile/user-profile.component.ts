import { Component, OnInit } from '@angular/core';

export class UserProfile {
	numeroSecu: string;
	nom: string;
	prenom: string;
	email: string;
	naissance: string;
	nationalite: string;
	genre: number;
	phoneNumber: string;

	constructor() {
		this.numeroSecu = '';
		this.nom = '';
		this.prenom = '';
		this.email = '';
		this.naissance = '';
		this.nationalite = '';
		this.genre = 0;
		this.phoneNumber = '';
	}
}

@Component({
	selector: 'app-user-profile',
	templateUrl: './user-profile.component.html',
	styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

	userProfile: UserProfile = new UserProfile();
	editMode: boolean = false;

	ngOnInit(): void {
		this.userProfile.numeroSecu = '012345678901';
		this.userProfile.nom = 'Doe';
		this.userProfile.prenom = 'John';
		this.userProfile.email = 'john.doe@example.com';
		this.userProfile.naissance = '01/01/1990';
		this.userProfile.nationalite = 'French';
		this.userProfile.genre = 1;
		this.userProfile.phoneNumber = '06 15 15 15 15';
	}

	toggleEditFields(): void {
		this.editMode = !this.editMode;
	}

	saveChanges(): void {
		//TODO
	}
}
