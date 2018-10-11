import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CatProdotto } from 'app/shared/model/cat-prodotto.model';
import { CatProdottoService } from './cat-prodotto.service';
import { CatProdottoComponent } from './cat-prodotto.component';
import { CatProdottoDetailComponent } from './cat-prodotto-detail.component';
import { CatProdottoUpdateComponent } from './cat-prodotto-update.component';
import { CatProdottoDeletePopupComponent } from './cat-prodotto-delete-dialog.component';
import { ICatProdotto } from 'app/shared/model/cat-prodotto.model';

@Injectable({ providedIn: 'root' })
export class CatProdottoResolve implements Resolve<ICatProdotto> {
    constructor(private service: CatProdottoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((catProdotto: HttpResponse<CatProdotto>) => catProdotto.body));
        }
        return of(new CatProdotto());
    }
}

export const catProdottoRoute: Routes = [
    {
        path: 'cat-prodotto',
        component: CatProdottoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CatProdottos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-prodotto/:id/view',
        component: CatProdottoDetailComponent,
        resolve: {
            catProdotto: CatProdottoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatProdottos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-prodotto/new',
        component: CatProdottoUpdateComponent,
        resolve: {
            catProdotto: CatProdottoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatProdottos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-prodotto/:id/edit',
        component: CatProdottoUpdateComponent,
        resolve: {
            catProdotto: CatProdottoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatProdottos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const catProdottoPopupRoute: Routes = [
    {
        path: 'cat-prodotto/:id/delete',
        component: CatProdottoDeletePopupComponent,
        resolve: {
            catProdotto: CatProdottoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatProdottos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
