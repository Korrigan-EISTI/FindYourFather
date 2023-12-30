import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-invitation-element',
  templateUrl: './invitation-element.component.html',
  styleUrls: ['./invitation-element.component.css']
})
export class InvitationElementComponent {
	
	@Input()
	invitation: {
	    id: number;
	    idUser: number;
	    root: number;
	    target: number;
	    relation: string;
	    status: string;
	}|undefined=undefined;
	
	acceptInvitation(){
		//TODO
	}
	
	refuseInvitation(){
		//TODO
	}
}
