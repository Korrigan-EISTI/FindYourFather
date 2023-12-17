import { Component, ComponentRef, ViewChild, ViewContainerRef } from '@angular/core';
import { TreeElementComponent } from './tree-element/tree-element.component';

@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
	@ViewChild("viewContainerRef", { read: ViewContainerRef }) vcr!: ViewContainerRef;

	ref!: ComponentRef<TreeElementComponent>;

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
            "pere": null,
            "mere": null,
            "key": i
        });
    }

    return staticData;
}

	
	private async getDataFromBackend(): Promise<any[]> {
		const url = '/showTree';
		return fetch(url, { method: 'post' })
	    	.then(response => response.json());
	}
}
