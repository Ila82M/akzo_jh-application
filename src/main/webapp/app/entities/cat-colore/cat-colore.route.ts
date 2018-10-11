import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CatColore } from 'app/shared/model/cat-colore.model';
import { CatColoreService } from './cat-colore.service';
import { CatColoreComponent } from './cat-colore.component';
import { CatColoreDetailComponent } from './cat-colore-detail.component';
import { CatColoreUpdateComponent } from './cat-colore-update.component';
import { CatColoreDeletePopupComponent } from './cat-colore-delete-dialog.component';
import { ICatColore } from 'app/shared/model/cat-colore.model';

@Injectable({ providedIn: 'root' })
export class CatColoreResolve implements Resolve<ICatColore> {
    constructor(private service: CatColoreService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((catColore: HttpResponse<CatColore>) => catColore.body));
        }
        return of(new CatColore());
    }
}

export const catColoreRoute: Routes = [
    {
        path: 'cat-colore',
        component: CatColoreComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CatColores'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-colore/:id/view',
        component: CatColoreDetailComponent,
        resolve: {
            catColore: CatColoreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatColores'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-colore/new',
        component: CatColoreUpdateComponent,
        resolve: {
            catColore: CatColoreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatColores'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-colore/:id/edit',
        component: CatColoreUpdateComponent,
        resolve: {
            catColore: CatColoreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatColores'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const catColorePopupRoute: Routes = [
    {
        path: 'cat-colore/:id/delete',
        component: CatColoreDeletePopupComponent,
        resolve: {
            catColore: CatColoreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatColores'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
