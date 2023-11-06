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
    key: number;
    father?: number;
    mother?: number;
    name: string;
    gender: string;
    birthYear: string;
    deathYear?: string;
  }[] = [];
  private linkDataArray: { from: number; to: number }[] = [];

  constructor() {
    this.data.push({ key: 0, name: "Papi 1", gender: "M", birthYear: "1950" });
    this.data.push({ key: 1, name: "Mamie 1", gender: "F", birthYear: "1950" });
    this.data.push({ key: 5, father: 0, mother: 1, name: "Papa", gender: "M", birthYear: "1975" });

    this.data.push({ key: 3, name: "Papi 2", gender: "M", birthYear: "1950" });
    this.data.push({ key: 4, name: "Mamie 2", gender: "F", birthYear: "1950" });
    this.data.push({ key: 6, father: 3, mother: 4, name: "Maman", gender: "F", birthYear: "1975" });

    this.data.push({ key: 7, father: 5, mother: 6, name: "Bébé", gender: "M", birthYear: "2002" });

    // Génération des liens de parenté
    for (const individual of this.data) {
      if (individual.father !== undefined) {
        this.linkDataArray.push({ from: individual.father, to: individual.key });
      }
      if (individual.mother !== undefined) {
        this.linkDataArray.push({ from: individual.mother, to: individual.key });
      }
    }
  }

  ngOnInit() {
    this.diagram = new go.Diagram('family-tree-diagram', { initialContentAlignment: go.Spot.Center });

    // Modèle de données
    this.diagram.model = new go.GraphLinksModel(this.data, this.linkDataArray);

    // Modèle de nœud
    this.diagram.nodeTemplate =
      go.GraphObject.make(go.Node, 'Auto',
        go.GraphObject.make(go.Shape, 'Rectangle',
          {
            fill: 'rgb(221, 221, 0)',
            width: 150,
            height: 50,
          }
        ),
        go.GraphObject.make(go.TextBlock, { margin: 5, editable: true },
          new go.Binding('text', 'name')
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
