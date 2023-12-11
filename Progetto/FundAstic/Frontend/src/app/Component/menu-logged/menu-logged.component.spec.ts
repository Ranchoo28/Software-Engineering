import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuLoggedComponent } from './menu-logged.component';

describe('MenuLoggedComponent', () => {
  let component: MenuLoggedComponent;
  let fixture: ComponentFixture<MenuLoggedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MenuLoggedComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MenuLoggedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
