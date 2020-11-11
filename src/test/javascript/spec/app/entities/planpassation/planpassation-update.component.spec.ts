import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SygmapTestModule } from '../../../test.module';
import { PlanpassationUpdateComponent } from 'app/entities/planpassation/planpassation-update.component';
import { PlanpassationService } from 'app/entities/planpassation/planpassation.service';
import { Planpassation } from 'app/shared/model/planpassation.model';

describe('Component Tests', () => {
  describe('Planpassation Management Update Component', () => {
    let comp: PlanpassationUpdateComponent;
    let fixture: ComponentFixture<PlanpassationUpdateComponent>;
    let service: PlanpassationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SygmapTestModule],
        declarations: [PlanpassationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PlanpassationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PlanpassationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PlanpassationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Planpassation(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Planpassation();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
