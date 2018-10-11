import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CatTipoprodotto } from 'app/shared/model/cat-tipoprodotto.model';
import { CatTipoprodottoService } from './cat-tipoprodotto.service';
import { CatTipoprodottoComponent } from './cat-tipoprodotto.component';
import { CatTipoprodottoDetailComponent } from './cat-tipoprodotto-detail.component';
import { CatTipoprodottoUpdateComponent } from './cat-tipoprodotto-update.component';
import { CatTipoprodottoDeletePopupComponent } from './cat-tipoprodotto-delete-dialog.component';
import { ICatTipoprodotto } from 'app/shared/model/cat-tipoprodotto.model';

@Injectable({ providedIn: 'root' })
export class CatTipoprodottoResolve implements Resolve<ICatTipoprodotto> {
    constructor(private service: CatTipoprodottoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((catTipoprodotto: HttpResponse<CatTipoprodotto>) => catTipoprodotto.body));
        }
        return of(new CatTipoprodotto());
    }
}

export const catTipoprodottoRoute: Routes = [
    {
        path: 'cat-tipoprodotto',
        component: CatTipoprodottoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CatTipoprodottos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-tipoprodotto/:id/view',
        component: CatTipoprodottoDetailComponent,
        resolve: {
            catTipoprodotto: CatTipoprodottoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatTipoprodottos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-tipoprodotto/new',
        component: CatTipoprodottoUpdateComponent,
        resolve: {
            catTipoprodotto: CatTipoprodottoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatTipoprodottos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-tipoprodotto/:id/edit',
        component: CatTipoprodottoUpdateComponent,
        resolve: {
            catTipoprodotto: CatTipoprodottoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatTipoprodottos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const catTipoprodottoPopupRoute: Routes = [
    {
        path: 'cat-tipoprodotto/:id/delete',
        component: CatTipoprodottoDeletePopupComponent,
        resolve: {
            catTipoprodotto: CatTipoprodottoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatTipoprodottos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
