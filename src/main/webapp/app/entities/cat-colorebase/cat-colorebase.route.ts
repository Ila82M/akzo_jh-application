import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CatColorebase } from 'app/shared/model/cat-colorebase.model';
import { CatColorebaseService } from './cat-colorebase.service';
import { CatColorebaseComponent } from './cat-colorebase.component';
import { CatColorebaseDetailComponent } from './cat-colorebase-detail.component';
import { CatColorebaseUpdateComponent } from './cat-colorebase-update.component';
import { CatColorebaseDeletePopupComponent } from './cat-colorebase-delete-dialog.component';
import { ICatColorebase } from 'app/shared/model/cat-colorebase.model';

@Injectable({ providedIn: 'root' })
export class CatColorebaseResolve implements Resolve<ICatColorebase> {
    constructor(private service: CatColorebaseService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((catColorebase: HttpResponse<CatColorebase>) => catColorebase.body));
        }
        return of(new CatColorebase());
    }
}

export const catColorebaseRoute: Routes = [
    {
        path: 'cat-colorebase',
        component: CatColorebaseComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CatColorebases'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-colorebase/:id/view',
        component: CatColorebaseDetailComponent,
        resolve: {
            catColorebase: CatColorebaseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatColorebases'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-colorebase/new',
        component: CatColorebaseUpdateComponent,
        resolve: {
            catColorebase: CatColorebaseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatColorebases'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-colorebase/:id/edit',
        component: CatColorebaseUpdateComponent,
        resolve: {
            catColorebase: CatColorebaseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatColorebases'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const catColorebasePopupRoute: Routes = [
    {
        path: 'cat-colorebase/:id/delete',
        component: CatColorebaseDeletePopupComponent,
        resolve: {
            catColorebase: CatColorebaseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatColorebases'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
