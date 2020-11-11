import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPlanpassation, Planpassation } from 'app/shared/model/planpassation.model';
import { PlanpassationService } from './planpassation.service';

@Component({
  selector: 'jhi-planpassation-update',
  templateUrl: './planpassation-update.component.html',
})
export class PlanpassationUpdateComponent implements OnInit {
  isSaving = false;
  dateDebutDp: any;
  dateFinDp: any;

  editForm = this.fb.group({
    id: [],
    dateDebut: [],
    dateFin: [],
    commentaire: [],
  });

  constructor(protected planpassationService: PlanpassationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ planpassation }) => {
      this.updateForm(planpassation);
    });
  }

  updateForm(planpassation: IPlanpassation): void {
    this.editForm.patchValue({
      id: planpassation.id,
      dateDebut: planpassation.dateDebut,
      dateFin: planpassation.dateFin,
      commentaire: planpassation.commentaire,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const planpassation = this.createFromForm();
    if (planpassation.id !== undefined) {
      this.subscribeToSaveResponse(this.planpassationService.update(planpassation));
    } else {
      this.subscribeToSaveResponse(this.planpassationService.create(planpassation));
    }
  }

  private createFromForm(): IPlanpassation {
    return {
      ...new Planpassation(),
      id: this.editForm.get(['id'])!.value,
      dateDebut: this.editForm.get(['dateDebut'])!.value,
      dateFin: this.editForm.get(['dateFin'])!.value,
      commentaire: this.editForm.get(['commentaire'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanpassation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
