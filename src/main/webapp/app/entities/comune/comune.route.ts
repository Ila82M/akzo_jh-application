import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Comune } from 'app/shared/model/comune.model';
import { ComuneService } from './comune.service';
import { ComuneComponent } from './comune.component';
import { ComuneDetailComponent } from './comune-detail.component';
import { ComuneUpdateComponent } from './comune-update.component';
import { ComuneDeletePopupComponent } from './comune-delete-dialog.component';
import { IComune } from 'app/shared/model/comune.model';

@Injectable({ providedIn: 'root' })
export class ComuneResolve implements Resolve<IComune> {
    constructor(private service: ComuneService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((comune: HttpResponse<Comune>) => comune.body));
        }
        return of(new Comune());
    }
}

export const comuneRoute: Routes = [
    {
        path: 'comune',
        component: ComuneComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Comunes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'comune/:id/view',
        component: ComuneDetailComponent,
        resolve: {
            comune: ComuneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Comunes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'comune/new',
        component: ComuneUpdateComponent,
        resolve: {
            comune: ComuneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Comunes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'comune/:id/edit',
        component: ComuneUpdateComponent,
        resolve: {
            comune: ComuneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Comunes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const comunePopupRoute: Routes = [
    {
        path: 'comune/:id/delete',
        component: ComuneDeletePopupComponent,
        resolve: {
            comune: ComuneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Comunes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
