import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AkzoJhApplicationSharedModule } from 'app/shared';
import {
    CatGruppocoloreComponent,
    CatGruppocoloreDetailComponent,
    CatGruppocoloreUpdateComponent,
    CatGruppocoloreDeletePopupComponent,
    CatGruppocoloreDeleteDialogComponent,
    catGruppocoloreRoute,
    catGruppocolorePopupRoute
} from './';

const ENTITY_STATES = [...catGruppocoloreRoute, ...catGruppocolorePopupRoute];

@NgModule({
    imports: [AkzoJhApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CatGruppocoloreComponent,
        CatGruppocoloreDetailComponent,
        CatGruppocoloreUpdateComponent,
        CatGruppocoloreDeleteDialogComponent,
        CatGruppocoloreDeletePopupComponent
    ],
    entryComponents: [
        CatGruppocoloreComponent,
        CatGruppocoloreUpdateComponent,
        CatGruppocoloreDeleteDialogComponent,
        CatGruppocoloreDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AkzoJhApplicationCatGruppocoloreModule {}
