import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SygmapTestModule } from '../../../test.module';
import { PlanpassationDetailComponent } from 'app/entities/planpassation/planpassation-detail.component';
import { Planpassation } from 'app/shared/model/planpassation.model';

describe('Component Tests', () => {
  describe('Planpassation Management Detail Component', () => {
    let comp: PlanpassationDetailComponent;
    let fixture: ComponentFixture<PlanpassationDetailComponent>;
    const route = ({ data: of({ planpassation: new Planpassation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SygmapTestModule],
        declarations: [PlanpassationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PlanpassationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PlanpassationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load planpassation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.planpassation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
