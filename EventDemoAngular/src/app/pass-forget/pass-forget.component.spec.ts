import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PassForgetComponent } from './pass-forget.component';

describe('PassForgetComponent', () => {
  let component: PassForgetComponent;
  let fixture: ComponentFixture<PassForgetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PassForgetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PassForgetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
