import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AkzoJhApplicationSharedModule } from 'app/shared';
import {
    CatTipocoloreComponent,
    CatTipocoloreDetailComponent,
    CatTipocoloreUpdateComponent,
    CatTipocoloreDeletePopupComponent,
    CatTipocoloreDeleteDialogComponent,
    catTipocoloreRoute,
    catTipocolorePopupRoute
} from './';

const ENTITY_STATES = [...catTipocoloreRoute, ...catTipocolorePopupRoute];

@NgModule({
    imports: [AkzoJhApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CatTipocoloreComponent,
        CatTipocoloreDetailComponent,
        CatTipocoloreUpdateComponent,
        CatTipocoloreDeleteDialogComponent,
        CatTipocoloreDeletePopupComponent
    ],
    entryComponents: [
        CatTipocoloreComponent,
        CatTipocoloreUpdateComponent,
        CatTipocoloreDeleteDialogComponent,
        CatTipocoloreDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AkzoJhApplicationCatTipocoloreModule {}
