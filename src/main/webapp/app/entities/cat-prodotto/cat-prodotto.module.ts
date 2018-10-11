import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AkzoJhApplicationSharedModule } from 'app/shared';
import {
    CatProdottoComponent,
    CatProdottoDetailComponent,
    CatProdottoUpdateComponent,
    CatProdottoDeletePopupComponent,
    CatProdottoDeleteDialogComponent,
    catProdottoRoute,
    catProdottoPopupRoute
} from './';

const ENTITY_STATES = [...catProdottoRoute, ...catProdottoPopupRoute];

@NgModule({
    imports: [AkzoJhApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CatProdottoComponent,
        CatProdottoDetailComponent,
        CatProdottoUpdateComponent,
        CatProdottoDeleteDialogComponent,
        CatProdottoDeletePopupComponent
    ],
    entryComponents: [CatProdottoComponent, CatProdottoUpdateComponent, CatProdottoDeleteDialogComponent, CatProdottoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AkzoJhApplicationCatProdottoModule {}
