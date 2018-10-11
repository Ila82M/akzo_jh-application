import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CatFamiglia } from 'app/shared/model/cat-famiglia.model';
import { CatFamigliaService } from './cat-famiglia.service';
import { CatFamigliaComponent } from './cat-famiglia.component';
import { CatFamigliaDetailComponent } from './cat-famiglia-detail.component';
import { CatFamigliaUpdateComponent } from './cat-famiglia-update.component';
import { CatFamigliaDeletePopupComponent } from './cat-famiglia-delete-dialog.component';
import { ICatFamiglia } from 'app/shared/model/cat-famiglia.model';

@Injectable({ providedIn: 'root' })
export class CatFamigliaResolve implements Resolve<ICatFamiglia> {
    constructor(private service: CatFamigliaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((catFamiglia: HttpResponse<CatFamiglia>) => catFamiglia.body));
        }
        return of(new CatFamiglia());
    }
}

export const catFamigliaRoute: Routes = [
    {
        path: 'cat-famiglia',
        component: CatFamigliaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CatFamiglias'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-famiglia/:id/view',
        component: CatFamigliaDetailComponent,
        resolve: {
            catFamiglia: CatFamigliaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatFamiglias'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-famiglia/new',
        component: CatFamigliaUpdateComponent,
        resolve: {
            catFamiglia: CatFamigliaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatFamiglias'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-famiglia/:id/edit',
        component: CatFamigliaUpdateComponent,
        resolve: {
            catFamiglia: CatFamigliaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatFamiglias'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const catFamigliaPopupRoute: Routes = [
    {
        path: 'cat-famiglia/:id/delete',
        component: CatFamigliaDeletePopupComponent,
        resolve: {
            catFamiglia: CatFamigliaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatFamiglias'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
