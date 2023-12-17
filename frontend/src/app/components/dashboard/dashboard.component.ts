import { Component, ComponentRef, Type, ViewChild, ViewContainerRef } from '@angular/core';
import { TreeElementComponent } from './tree-element/tree-element.component';

@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
	@ViewChild("viewContainerRef", { read: ViewContainerRef }) vcr!: ViewContainerRef;

	ref!: ComponentRef<TreeElementComponent>;

	private data: {
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

	components = [];

	ngOnInit() {
		setTimeout(() => {
			this.getPersonnes(true);
		});
	}
	
	public getPersonnes(test: boolean) {
		let promise: Promise<any[]>;
		
		if (test) {
			promise = Promise.resolve(this.getStaticData());
		} else {
			promise = this.getDataFromBackend();
		}
	
		promise.then((data: any[]) => {
			data.forEach((personne: any) => {
				let ref = this.vcr.createComponent(TreeElementComponent);
				ref.instance.personne = personne;
			});
		});
	}

	private getStaticData() {
		return [
			{
				"id": 0,
				"numeroSecu": 444444440,
				"nom": "MODE",
				"prenom": "TEST",
				"naissance": "00/00/0000",
				"dateDeces": null,
				"nationalite": "French",
				"genre": "HOMME",
				"pere": null,
				"mere": null,
				"key": 6
			},
			{
				"id": 5,
				"numeroSecu": 444444444,
				"nom": "Doe",
				"prenom": "Alice",
				"naissance": "01/01/1945",
				"dateDeces": null,
				"nationalite": "French",
				"genre": "FEMME",
				"pere": null,
				"mere": null,
				"key": 5
			},
			{
				"id": 3,
				"numeroSecu": 222222222,
				"nom": "Doe",
				"prenom": "Mary",
				"naissance": "01/01/1970",
				"dateDeces": null,
				"nationalite": "French",
				"genre": "FEMME",
				"pere": null,
				"mere": null,
				"key": 3
			},
			{
				"id": 1,
				"numeroSecu": 123456789,
				"nom": "Doe",
				"prenom": "John",
				"naissance": "01/01/1990",
				"dateDeces": null,
				"nationalite": "French",
				"genre": "HOMME",
				"pere": 2,
				"mere": 3,
				"key": 1
			},
			{
				"id": 4,
				"numeroSecu": 333333333,
				"nom": "Doe",
				"prenom": "George",
				"naissance": "01/01/1940",
				"dateDeces": null,
				"nationalite": "French",
				"genre": "HOMME",
				"pere": null,
				"mere": null,
				"key": 4
			},
			{
				"id": 2,
				"numeroSecu": 111111111,
				"nom": "Doe",
				"prenom": "Peter",
				"naissance": "01/01/1965",
				"dateDeces": null,
				"nationalite": "French",
				"genre": "HOMME",
				"pere": 4,
				"mere": 5,
				"key": 2
			}
		];
	}
	
	private getDataFromBackend(): Promise<any[]> {
		const url = '/showTree';
		return fetch(url, { method: 'post' })
	    	.then(response => response.json());
	}
}
