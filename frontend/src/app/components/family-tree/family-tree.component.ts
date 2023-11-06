import { Component, OnInit } from '@angular/core';
import * as go from 'gojs';

@Component({
  selector: 'app-family-tree',
  templateUrl: './family-tree.component.html',
  styleUrls: ['./family-tree.component.css']
})
export class FamilyTreeComponent implements OnInit {
  diagram = new go.Diagram();

  constructor() { }

  ngOnInit() {
  	this.diagram = new go.Diagram('family-tree-diagram');
  }

}
