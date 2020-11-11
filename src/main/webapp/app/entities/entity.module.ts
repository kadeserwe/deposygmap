import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'planpassation',
        loadChildren: () => import('./planpassation/planpassation.module').then(m => m.SygmapPlanpassationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class SygmapEntityModule {}
