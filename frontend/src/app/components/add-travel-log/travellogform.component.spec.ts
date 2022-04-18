import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TravellogformComponent } from './travellogform.component';

describe('TravellogformComponent', () => {
  let component: TravellogformComponent;
  let fixture: ComponentFixture<TravellogformComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TravellogformComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TravellogformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
