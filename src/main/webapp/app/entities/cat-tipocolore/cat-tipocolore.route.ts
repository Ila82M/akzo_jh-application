import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CatTipocolore } from 'app/shared/model/cat-tipocolore.model';
import { CatTipocoloreService } from './cat-tipocolore.service';
import { CatTipocoloreComponent } from './cat-tipocolore.component';
import { CatTipocoloreDetailComponent } from './cat-tipocolore-detail.component';
import { CatTipocoloreUpdateComponent } from './cat-tipocolore-update.component';
import { CatTipocoloreDeletePopupComponent } from './cat-tipocolore-delete-dialog.component';
import { ICatTipocolore } from 'app/shared/model/cat-tipocolore.model';

@Injectable({ providedIn: 'root' })
export class CatTipocoloreResolve implements Resolve<ICatTipocolore> {
    constructor(private service: CatTipocoloreService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((catTipocolore: HttpResponse<CatTipocolore>) => catTipocolore.body));
        }
        return of(new CatTipocolore());
    }
}

export const catTipocoloreRoute: Routes = [
    {
        path: 'cat-tipocolore',
        component: CatTipocoloreComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CatTipocolores'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-tipocolore/:id/view',
        component: CatTipocoloreDetailComponent,
        resolve: {
            catTipocolore: CatTipocoloreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatTipocolores'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-tipocolore/new',
        component: CatTipocoloreUpdateComponent,
        resolve: {
            catTipocolore: CatTipocoloreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatTipocolores'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-tipocolore/:id/edit',
        component: CatTipocoloreUpdateComponent,
        resolve: {
            catTipocolore: CatTipocoloreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatTipocolores'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const catTipocolorePopupRoute: Routes = [
    {
        path: 'cat-tipocolore/:id/delete',
        component: CatTipocoloreDeletePopupComponent,
        resolve: {
            catTipocolore: CatTipocoloreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatTipocolores'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
