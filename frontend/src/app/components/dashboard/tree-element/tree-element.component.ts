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
	}|undefined=undefined;
	
	addingConnexionForm_ShowButton(){
		if(this.showingAddingConnexionForm==true && this.showingRemovingConnexionForm==false) {
			this.showingAddingConnexionForm=false;
		} else {
			this.showingAddingConnexionForm=true;
			this.showingRemovingConnexionForm=false;
		}
	}
	
	removingConnexionForm_ShowButton(){
		if(this.showingAddingConnexionForm==false && this.showingRemovingConnexionForm==true) {
			this.showingRemovingConnexionForm=false;
		} else {
			this.showingAddingConnexionForm=false;
			this.showingRemovingConnexionForm=true;
		}
	}
}
