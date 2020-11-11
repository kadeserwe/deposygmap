import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanpassation } from 'app/shared/model/planpassation.model';
import { PlanpassationService } from './planpassation.service';

@Component({
  templateUrl: './planpassation-delete-dialog.component.html',
})
export class PlanpassationDeleteDialogComponent {
  planpassation?: IPlanpassation;

  constructor(
    protected planpassationService: PlanpassationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.planpassationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('planpassationListModification');
      this.activeModal.close();
    });
  }
}
