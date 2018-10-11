import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CatLattina } from 'app/shared/model/cat-lattina.model';
import { CatLattinaService } from './cat-lattina.service';
import { CatLattinaComponent } from './cat-lattina.component';
import { CatLattinaDetailComponent } from './cat-lattina-detail.component';
import { CatLattinaUpdateComponent } from './cat-lattina-update.component';
import { CatLattinaDeletePopupComponent } from './cat-lattina-delete-dialog.component';
import { ICatLattina } from 'app/shared/model/cat-lattina.model';

@Injectable({ providedIn: 'root' })
export class CatLattinaResolve implements Resolve<ICatLattina> {
    constructor(private service: CatLattinaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((catLattina: HttpResponse<CatLattina>) => catLattina.body));
        }
        return of(new CatLattina());
    }
}

export const catLattinaRoute: Routes = [
    {
        path: 'cat-lattina',
        component: CatLattinaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CatLattinas'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-lattina/:id/view',
        component: CatLattinaDetailComponent,
        resolve: {
            catLattina: CatLattinaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatLattinas'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-lattina/new',
        component: CatLattinaUpdateComponent,
        resolve: {
            catLattina: CatLattinaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatLattinas'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cat-lattina/:id/edit',
        component: CatLattinaUpdateComponent,
        resolve: {
            catLattina: CatLattinaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatLattinas'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const catLattinaPopupRoute: Routes = [
    {
        path: 'cat-lattina/:id/delete',
        component: CatLattinaDeletePopupComponent,
        resolve: {
            catLattina: CatLattinaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatLattinas'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
