import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinaceFormComponent } from './finance-form.component';

describe('FinanceFormComponent', () => {
  let component: FinaceFormComponent;
  let fixture: ComponentFixture<FinaceFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FinaceFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FinaceFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
