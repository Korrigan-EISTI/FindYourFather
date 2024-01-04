import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-invitation-element',
    templateUrl: './invitation-element.component.html',
    styleUrls: ['./invitation-element.component.css']
})

export class InvitationElementComponent {

	@Input()
	invitation: any | undefined = undefined;

    acceptInvitation() {
        const formData = new FormData();
        formData.append("id", this.invitation!.invitation.id.toString());
        const url = '/relation/accept';

        fetch(url, {
            method: 'post',
            body: new URLSearchParams(formData as any)
        }).then(response => {
            if (!response.ok) {
                throw new Error(response.statusText);
            }

            response.text().then(text => {
                console.log(text);
            })
        });
    }

    refuseInvitation() {
        const formData = new FormData();
        formData.append("id", this.invitation!.invitation.id.toString());
        const url = '/relation/refuse';

        fetch(url, {
            method: 'post',
            body: new URLSearchParams(formData as any)
        }).then(response => {
            if (!response.ok) {
                throw new Error(response.statusText);
            }

            response.text().then(text => {
                console.log(text);
            })
        });
    }
}
