import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AkzoJhApplicationSharedModule } from 'app/shared';
import {
    CatBaseComponent,
    CatBaseDetailComponent,
    CatBaseUpdateComponent,
    CatBaseDeletePopupComponent,
    CatBaseDeleteDialogComponent,
    catBaseRoute,
    catBasePopupRoute
} from './';

const ENTITY_STATES = [...catBaseRoute, ...catBasePopupRoute];

@NgModule({
    imports: [AkzoJhApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CatBaseComponent,
        CatBaseDetailComponent,
        CatBaseUpdateComponent,
        CatBaseDeleteDialogComponent,
        CatBaseDeletePopupComponent
    ],
    entryComponents: [CatBaseComponent, CatBaseUpdateComponent, CatBaseDeleteDialogComponent, CatBaseDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AkzoJhApplicationCatBaseModule {}
