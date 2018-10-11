import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CatBase } from 'app/shared/model/cat-base.model';
import { CatBaseService } from './cat-base.service';
import { CatBaseComponent } from './cat-base.component';
import { CatBaseDetailComponent } from './cat-base-detail.component';
import { CatBaseUpdateComponent } from './cat-base-update.component';
import { CatBaseDeletePopupComponent } from './cat-base-delete-dialog.component';
import { ICatBase } from 'app/shared/model/cat-base.model';

@Injectable({ providedIn: 'root' })
export class CatBaseResolve implements Resolve<ICatBase> {
    constructor(private service: CatBaseService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((catBase: HttpResponse<CatBase>) => catBase.body));
        }
        return of(new CatBase());
    }
}

export const catBaseRoute: Routes = [
    {
        path: 'cat-base',
        component: CatBaseComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CatBases'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-base/:id/view',
        component: CatBaseDetailComponent,
        resolve: {
            catBase: CatBaseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatBases'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-base/new',
        component: CatBaseUpdateComponent,
        resolve: {
            catBase: CatBaseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatBases'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-base/:id/edit',
        component: CatBaseUpdateComponent,
        resolve: {
            catBase: CatBaseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatBases'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const catBasePopupRoute: Routes = [
    {
        path: 'cat-base/:id/delete',
        component: CatBaseDeletePopupComponent,
        resolve: {
            catBase: CatBaseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatBases'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
