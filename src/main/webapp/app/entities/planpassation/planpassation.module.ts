import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SygmapSharedModule } from 'app/shared/shared.module';
import { PlanpassationComponent } from './planpassation.component';
import { PlanpassationDetailComponent } from './planpassation-detail.component';
import { PlanpassationUpdateComponent } from './planpassation-update.component';
import { PlanpassationDeleteDialogComponent } from './planpassation-delete-dialog.component';
import { planpassationRoute } from './planpassation.route';

@NgModule({
  imports: [SygmapSharedModule, RouterModule.forChild(planpassationRoute)],
  declarations: [PlanpassationComponent, PlanpassationDetailComponent, PlanpassationUpdateComponent, PlanpassationDeleteDialogComponent],
  entryComponents: [PlanpassationDeleteDialogComponent],
})
export class SygmapPlanpassationModule {}
