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
	    const form: HTMLFormElement = document.getElementById('remove-relation-form') as HTMLFormElement;
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
