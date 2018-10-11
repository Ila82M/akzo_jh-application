import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AkzoJhApplicationSharedModule } from 'app/shared';
import {
    CatColoreComponent,
    CatColoreDetailComponent,
    CatColoreUpdateComponent,
    CatColoreDeletePopupComponent,
    CatColoreDeleteDialogComponent,
    catColoreRoute,
    catColorePopupRoute
} from './';

const ENTITY_STATES = [...catColoreRoute, ...catColorePopupRoute];

@NgModule({
    imports: [AkzoJhApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CatColoreComponent,
        CatColoreDetailComponent,
        CatColoreUpdateComponent,
        CatColoreDeleteDialogComponent,
        CatColoreDeletePopupComponent
    ],
    entryComponents: [CatColoreComponent, CatColoreUpdateComponent, CatColoreDeleteDialogComponent, CatColoreDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AkzoJhApplicationCatColoreModule {}
