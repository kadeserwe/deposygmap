import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPlanpassation, Planpassation } from 'app/shared/model/planpassation.model';
import { PlanpassationService } from './planpassation.service';
import { PlanpassationComponent } from './planpassation.component';
import { PlanpassationDetailComponent } from './planpassation-detail.component';
import { PlanpassationUpdateComponent } from './planpassation-update.component';

@Injectable({ providedIn: 'root' })
export class PlanpassationResolve implements Resolve<IPlanpassation> {
  constructor(private service: PlanpassationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPlanpassation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((planpassation: HttpResponse<Planpassation>) => {
          if (planpassation.body) {
            return of(planpassation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Planpassation());
  }
}

export const planpassationRoute: Routes = [
  {
    path: '',
    component: PlanpassationComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Planpassations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PlanpassationDetailComponent,
    resolve: {
      planpassation: PlanpassationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Planpassations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PlanpassationUpdateComponent,
    resolve: {
      planpassation: PlanpassationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Planpassations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PlanpassationUpdateComponent,
    resolve: {
      planpassation: PlanpassationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Planpassations',
    },
    canActivate: [UserRouteAccessService],
  },
];
