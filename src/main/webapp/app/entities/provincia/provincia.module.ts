import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AkzoJhApplicationSharedModule } from 'app/shared';
import {
    ProvinciaComponent,
    ProvinciaDetailComponent,
    ProvinciaUpdateComponent,
    ProvinciaDeletePopupComponent,
    ProvinciaDeleteDialogComponent,
    provinciaRoute,
    provinciaPopupRoute
} from './';

const ENTITY_STATES = [...provinciaRoute, ...provinciaPopupRoute];

@NgModule({
    imports: [AkzoJhApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProvinciaComponent,
        ProvinciaDetailComponent,
        ProvinciaUpdateComponent,
        ProvinciaDeleteDialogComponent,
        ProvinciaDeletePopupComponent
    ],
    entryComponents: [ProvinciaComponent, ProvinciaUpdateComponent, ProvinciaDeleteDialogComponent, ProvinciaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AkzoJhApplicationProvinciaModule {}
