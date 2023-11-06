import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
	public showTree(){
		// Send the form data to a page using the FormData API and JavaScript
		const url = '/showTree'; // Replace with your target page URL

		fetch(url, {
			method: 'post',
		}).then(response => {
			if (!response.ok) {
			  throw new Error(response.statusText)
			}
			response.text().then(text=>document.getElementById("status")!.innerHTML = text);
		  });

		return false;
	}
}
