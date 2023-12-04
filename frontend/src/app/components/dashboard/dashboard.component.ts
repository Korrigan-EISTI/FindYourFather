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
    this.getPersonnes();
  }

  public getPersonnes(){
    const url = '/showTree';

    fetch(url, {
      method: 'post',
    }).then(response =>  {
      response.json().then(data => {
        console.log(data);
        this.data = data;
        let panel = document.getElementById("right-panel")!;
        data.forEach((personne: any) => {
          let ref = this.vcr.createComponent(TreeElementComponent);
          ref.instance.personne = personne;
        });
      });
    });

    return false;
  }
}
