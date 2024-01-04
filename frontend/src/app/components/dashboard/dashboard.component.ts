import { Component, ViewChild, ViewContainerRef } from '@angular/core';
import { TreeElementComponent } from './tree-element/tree-element.component';
import { InvitationElementComponent } from './invitation-element/invitation-element.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

	@ViewChild("treeViewContainerRef", { read: ViewContainerRef }) treeVcr!: ViewContainerRef;
	@ViewChild("invitationViewContainerRef", { read: ViewContainerRef }) invitationVcr!: ViewContainerRef;

	devEnvironment = false;

	ngOnInit() {
		setTimeout(() => {
			this.getPersonnes(this.devEnvironment);
			this.getInvitations(this.devEnvironment);
		});
	}

	public getPersonnes(areWeInDevEnvironment: boolean) {
		let promise: Promise<any[]>;

		if (areWeInDevEnvironment) {
			promise = this.getStaticPersonneData();
		} else {
			promise = this.getPersonneDataFromBackend();
		}

		promise.then((data: any[]) => {
			data.forEach((personne: any) => {
				const refTreeElement = this.treeVcr.createComponent(TreeElementComponent);
				refTreeElement.instance.personne = personne;
			});
		})
			.catch(error => {
				console.error('Erreur lors de la récupération des personnes :', error);
			});
	}

	public getInvitations(areWeInDevEnvironment: boolean) {
		let promise: Promise<any[]>;

		if (areWeInDevEnvironment) {
			promise = this.getStaticInvitations();
		} else {
			promise = this.getInvitationsFromBackend();
		}

		promise.then((invitations: any[]) => {
			console.log("invitations");
			console.log(invitations);
			invitations.forEach((invitation: any) => {
				console.log(invitation);
				const refInvitation = this.invitationVcr.createComponent(InvitationElementComponent);
				refInvitation.instance.invitation = invitation;
				console.log(invitation);
			});
		})
			.catch(error => {
				console.error('Erreur lors de la récupération des invitations :', error);
			});
	}

	private getStaticInvitations(): Promise<any[]> {
		const staticInvitations = [];

		for (let i = 1; i <= 15; i++) {
			const relation = i % 3 === 0 ? 'CHILD' : (i % 2 === 0 ? 'FATHER' : 'MOTHER');
			const status = 'PENDING';

			const invitation = {
				id: i,
				idUser: i + 100,
				root: i + 200,
				target: i + 300,
				relation: relation,
				status: status
			};

			staticInvitations.push(invitation);
		}

		return Promise.resolve(staticInvitations);
	}

	private async getInvitationsFromBackend(): Promise<any[]> {
		const url = '/getInvitations';
		try {
			const response = await fetch(url, { method: 'post' });
			return response.json();
		} catch (error) {
			throw new Error('Erreur lors de la récupération des invitations depuis le backend.');
		}
	}

	private getStaticPersonneData(): Promise<any[]> {
		const staticData = [];

		for (let i = 1; i <= 15; i++) {
			const randomNumber = Math.floor(Math.random() * 900000000) + 100000000;

			staticData.push({
				"id": i,
				"numeroSecu": randomNumber,
				"nom": `Nom${i}`,
				"prenom": `Prenom${i}`,
				"naissance": "00/00/0000",
				"dateDeces": null,
				"nationalite": "French",
				"genre": i % 2 === 0 ? "FEMME" : "HOMME",
				"pere": i-1,
				"mere": i+1,
				"key": i
			});
		}

		return Promise.resolve(staticData);
	}

	private async getPersonneDataFromBackend(): Promise<any[]> {
		const url = '/showTree';
		try {
			const response = await fetch(url, { method: 'post' });
			return response.json();
		} catch (error) {
			throw new Error('Erreur lors de la récupération des personnes depuis le backend.');
		}
	}
}