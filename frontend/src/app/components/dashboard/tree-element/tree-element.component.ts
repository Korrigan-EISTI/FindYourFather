import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-tree-element',
  templateUrl: './tree-element.component.html',
  styleUrls: ['./tree-element.component.css']
})
export class TreeElementComponent {
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
}
