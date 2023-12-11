import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublishFormComponent } from './publish-form.component';

describe('PublishFormComponent', () => {
  let component: PublishFormComponent;
  let fixture: ComponentFixture<PublishFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublishFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PublishFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
