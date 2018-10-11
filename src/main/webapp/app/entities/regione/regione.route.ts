import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Regione } from 'app/shared/model/regione.model';
import { RegioneService } from './regione.service';
import { RegioneComponent } from './regione.component';
import { RegioneDetailComponent } from './regione-detail.component';
import { RegioneUpdateComponent } from './regione-update.component';
import { RegioneDeletePopupComponent } from './regione-delete-dialog.component';
import { IRegione } from 'app/shared/model/regione.model';

@Injectable({ providedIn: 'root' })
export class RegioneResolve implements Resolve<IRegione> {
    constructor(private service: RegioneService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((regione: HttpResponse<Regione>) => regione.body));
        }
        return of(new Regione());
    }
}

export const regioneRoute: Routes = [
    {
        path: 'regione',
        component: RegioneComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Regiones'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'regione/:id/view',
        component: RegioneDetailComponent,
        resolve: {
            regione: RegioneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Regiones'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'regione/new',
        component: RegioneUpdateComponent,
        resolve: {
            regione: RegioneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Regiones'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'regione/:id/edit',
        component: RegioneUpdateComponent,
        resolve: {
            regione: RegioneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Regiones'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const regionePopupRoute: Routes = [
    {
        path: 'regione/:id/delete',
        component: RegioneDeletePopupComponent,
        resolve: {
            regione: RegioneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Regiones'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
