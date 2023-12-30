import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvitationElementComponent } from './invitation-element.component';

describe('InvitationElementComponent', () => {
  let component: InvitationElementComponent;
  let fixture: ComponentFixture<InvitationElementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InvitationElementComponent]
    });
    fixture = TestBed.createComponent(InvitationElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
