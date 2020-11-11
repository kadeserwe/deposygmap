import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlanpassation } from 'app/shared/model/planpassation.model';

@Component({
  selector: 'jhi-planpassation-detail',
  templateUrl: './planpassation-detail.component.html',
})
export class PlanpassationDetailComponent implements OnInit {
  planpassation: IPlanpassation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ planpassation }) => (this.planpassation = planpassation));
  }

  previousState(): void {
    window.history.back();
  }
}
