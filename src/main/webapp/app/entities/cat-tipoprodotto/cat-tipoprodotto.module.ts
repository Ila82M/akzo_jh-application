import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AkzoJhApplicationSharedModule } from 'app/shared';
import {
    CatTipoprodottoComponent,
    CatTipoprodottoDetailComponent,
    CatTipoprodottoUpdateComponent,
    CatTipoprodottoDeletePopupComponent,
    CatTipoprodottoDeleteDialogComponent,
    catTipoprodottoRoute,
    catTipoprodottoPopupRoute
} from './';

const ENTITY_STATES = [...catTipoprodottoRoute, ...catTipoprodottoPopupRoute];

@NgModule({
    imports: [AkzoJhApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CatTipoprodottoComponent,
        CatTipoprodottoDetailComponent,
        CatTipoprodottoUpdateComponent,
        CatTipoprodottoDeleteDialogComponent,
        CatTipoprodottoDeletePopupComponent
    ],
    entryComponents: [
        CatTipoprodottoComponent,
        CatTipoprodottoUpdateComponent,
        CatTipoprodottoDeleteDialogComponent,
        CatTipoprodottoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AkzoJhApplicationCatTipoprodottoModule {}
