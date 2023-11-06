import { Component, OnInit } from '@angular/core';
import * as go from 'gojs';

@Component({
  selector: 'app-family-tree',
  templateUrl: './family-tree.component.html',
  styleUrls: ['./family-tree.component.css']
})
export class FamilyTreeComponent implements OnInit {
  private diagram!: go.Diagram;
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
  private linkDataArray: { from: number; to: number }[] = [];

  constructor() {
    // Génération des liens de parenté
  }

  ngOnInit() {
	this.showTree();
  }
  
  public showTree(){
    console.log("hey");
		// Send the form data to a page using the FormData API and JavaScript
		const url = '/showTree'; // Replace with your target page URL

		fetch(url, {
			method: 'post',
		}).then(response =>  {
			response.json().then(data => {
				console.log(data);
				this.data = data;
				
				if (this.data.length == 0) return;
				
				this.createTree();
			});
		});

		return false;
	}
	
	public createTree(){
		this.diagram = new go.Diagram('family-tree-diagram', { initialContentAlignment: go.Spot.Center });
		for (const individual of this.data) {
			individual.key = individual.id;
     		if (individual.pere !== null) {
        		this.linkDataArray.push({ from: individual.pere!, to: individual.key });
      		}
      		if (individual.mere !== null) {
        		this.linkDataArray.push({ from: individual.mere!, to: individual.key });
      		}
    	}
    	
	console.log(this.linkDataArray);
	console.log(this.data);
    // Modèle de données
    this.diagram.model = new go.GraphLinksModel(this.data, this.linkDataArray);

    // Modèle de nœud
    this.diagram.nodeTemplate =
      go.GraphObject.make(go.Node, 'Auto',
        go.GraphObject.make(go.Shape, 'Rectangle',
          {
            fill: 'rgb(180, 180, 180)',
            width: 150,
            height: 50,
          }
        ),
        go.GraphObject.make(go.TextBlock, { margin: 5, editable: true },
          new go.Binding('text', 'nom'),
          new go.Binding('text', 'prenom')
        )
      );

    // Modèle de lien
    this.diagram.linkTemplate = go.GraphObject.make(go.Link,
      go.GraphObject.make(go.Shape),
      go.GraphObject.make(go.Shape, { toArrow: 'Standard' })
    );

    // Modèle de disposition
    this.diagram.layout = go.GraphObject.make(go.LayeredDigraphLayout, {
      direction: 90,
      columnSpacing: 10,
      layerSpacing: 40,
    }); 
	}
}
