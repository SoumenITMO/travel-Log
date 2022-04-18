import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatetravellogComponent } from './updatetravellog.component';

describe('UpdatetravellogComponent', () => {
  let component: UpdatetravellogComponent;
  let fixture: ComponentFixture<UpdatetravellogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatetravellogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatetravellogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
