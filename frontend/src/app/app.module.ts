import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FamilyTreeComponent } from './components/family-tree/family-tree.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AuthComponent } from './components/auth/auth.component';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { TreeElementComponent } from './components/dashboard/tree-element/tree-element.component';
import { InvitationElementComponent } from './components/dashboard/invitation-element/invitation-element.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    FamilyTreeComponent,
    UserProfileComponent,
    AuthComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    TreeElementComponent,
    InvitationElementComponent
  ],
  
  imports: [
    FormsModule,
    BrowserModule,
    RouterModule.forRoot([
	  { path: '', component: HomeComponent },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'family-tree', component: FamilyTreeComponent },
      { path: 'user-profile', component: UserProfileComponent },
      { path: 'auth', component: AuthComponent }
    ]),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
