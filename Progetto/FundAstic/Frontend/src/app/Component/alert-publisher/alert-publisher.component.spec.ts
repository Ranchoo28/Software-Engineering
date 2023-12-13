import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlertPublisherComponent } from './alert-publisher.component';

describe('AlertPublisherComponent', () => {
  let component: AlertPublisherComponent;
  let fixture: ComponentFixture<AlertPublisherComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlertPublisherComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AlertPublisherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
