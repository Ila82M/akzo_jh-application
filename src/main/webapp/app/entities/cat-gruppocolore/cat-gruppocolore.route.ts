import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CatGruppocolore } from 'app/shared/model/cat-gruppocolore.model';
import { CatGruppocoloreService } from './cat-gruppocolore.service';
import { CatGruppocoloreComponent } from './cat-gruppocolore.component';
import { CatGruppocoloreDetailComponent } from './cat-gruppocolore-detail.component';
import { CatGruppocoloreUpdateComponent } from './cat-gruppocolore-update.component';
import { CatGruppocoloreDeletePopupComponent } from './cat-gruppocolore-delete-dialog.component';
import { ICatGruppocolore } from 'app/shared/model/cat-gruppocolore.model';

@Injectable({ providedIn: 'root' })
export class CatGruppocoloreResolve implements Resolve<ICatGruppocolore> {
    constructor(private service: CatGruppocoloreService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((catGruppocolore: HttpResponse<CatGruppocolore>) => catGruppocolore.body));
        }
        return of(new CatGruppocolore());
    }
}

export const catGruppocoloreRoute: Routes = [
    {
        path: 'cat-gruppocolore',
        component: CatGruppocoloreComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CatGruppocolores'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-gruppocolore/:id/view',
        component: CatGruppocoloreDetailComponent,
        resolve: {
            catGruppocolore: CatGruppocoloreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatGruppocolores'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-gruppocolore/new',
        component: CatGruppocoloreUpdateComponent,
        resolve: {
            catGruppocolore: CatGruppocoloreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatGruppocolores'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-gruppocolore/:id/edit',
        component: CatGruppocoloreUpdateComponent,
        resolve: {
            catGruppocolore: CatGruppocoloreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatGruppocolores'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const catGruppocolorePopupRoute: Routes = [
    {
        path: 'cat-gruppocolore/:id/delete',
        component: CatGruppocoloreDeletePopupComponent,
        resolve: {
            catGruppocolore: CatGruppocoloreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatGruppocolores'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
