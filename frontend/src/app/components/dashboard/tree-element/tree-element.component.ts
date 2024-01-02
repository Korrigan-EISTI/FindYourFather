import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-tree-element',
  templateUrl: './tree-element.component.html',
  styleUrls: ['./tree-element.component.css']
})
export class TreeElementComponent {
	
	public showingAddingConnexionForm = false;
	public showingRemovingConnexionForm = false;
	
	@Input()
	personne: {
	    id: number;
	    key: number;
	    numeroSecu: number;
	    nom: string;
	    prenom: string;
	    naissance: string;
	    dateNaissance: string;
	    dateDeces: string;
	    nationalite: string;
	    genre: number;
	    pere?: number;
	    mere?: number;
	} | undefined = undefined;
	
	directLinks: {
	    id: number;
	    key: number;
	    numeroSecu: number;
	    nom: string;
	    prenom: string;
	    naissance: string;
	    dateNaissance: string;
	    dateDeces: string;
	    nationalite: string;
	    genre: number;
	    pere?: number;
	    mere?: number;
	}[] = [];

	addingConnexionForm_ShowButton() {
		if (this.showingAddingConnexionForm == true && this.showingRemovingConnexionForm == false) {
			this.showingAddingConnexionForm = false;
		} else {
			this.showingAddingConnexionForm = true;
			this.showingRemovingConnexionForm = false;
		}
	}

	removingConnexionForm_ShowButton() {
		if (this.showingAddingConnexionForm == false && this.showingRemovingConnexionForm == true) {
			this.showingRemovingConnexionForm = false;
		} else {
			this.showingAddingConnexionForm = false;
			this.showingRemovingConnexionForm = true;
		}
	}

	async findDirectLinks() {
	    if (this.personne?.pere !== undefined) {
	        const pereDetails = await this.getPersonneDetails(this.personne.pere);
	        this.directLinks.push(pereDetails);
	    }
	    if (this.personne?.mere !== undefined) {
	        const mereDetails = await this.getPersonneDetails(this.personne.mere);
	        this.directLinks.push(mereDetails);
	    }
	}
	
	async getPersonneDetails(id: number): Promise<any> {
	    const url = `/getPersonneById/${id}`;
	    try {
	        const response = await fetch(url, { method: 'get' });
	        if (!response.ok) {
	            throw new Error(`Erreur lors de la récupération des détails de la personne (statut : ${response.status}).`);
	        }
	        return response.json();
	    } catch (error) {
	        console.error('Erreur lors de la récupération des détails de la personne :', error);
	        return [];
	    }
	}
	
	addRelation() {
	    const form: HTMLFormElement = document.getElementById('add-relation-form') as HTMLFormElement;
	    const formData = new FormData(form);
	    formData.append("id", this.personne!.id.toString());
	    const url = '/relation/add';
	
	    fetch(url, {
	        method: 'post',
	        body: new URLSearchParams(formData as any)
	    }).then(response => {
	        if (!response.ok) {
	            throw new Error(response.statusText);
	        }
	        response.text().then(text => { console.log(text); });
	    });
	    this.showingAddingConnexionForm = false;
	}
	
	removeRelation() {
	    const form: HTMLFormElement = document.getElementById('add-relation-form') as HTMLFormElement;
	    const formData = new FormData(form);
	    formData.append("id", this.personne!.id.toString());
	    const url = '/relation/remove';
	
	    fetch(url, {
	        method: 'post',
	        body: new URLSearchParams(formData as any)
	    }).then(response => {
	        if (!response.ok) {
	            throw new Error(response.statusText);
	        }
	        response.text().then(text => { console.log(text); });
	    });
	    this.showingRemovingConnexionForm = false;
	}
}
