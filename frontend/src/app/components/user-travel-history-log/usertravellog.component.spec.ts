import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsertravellogComponent } from './usertravellog.component';

describe('UsertravellogComponent', () => {
  let component: UsertravellogComponent;
  let fixture: ComponentFixture<UsertravellogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsertravellogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UsertravellogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
