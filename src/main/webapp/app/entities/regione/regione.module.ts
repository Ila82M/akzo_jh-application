import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AkzoJhApplicationSharedModule } from 'app/shared';
import {
    RegioneComponent,
    RegioneDetailComponent,
    RegioneUpdateComponent,
    RegioneDeletePopupComponent,
    RegioneDeleteDialogComponent,
    regioneRoute,
    regionePopupRoute
} from './';

const ENTITY_STATES = [...regioneRoute, ...regionePopupRoute];

@NgModule({
    imports: [AkzoJhApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RegioneComponent,
        RegioneDetailComponent,
        RegioneUpdateComponent,
        RegioneDeleteDialogComponent,
        RegioneDeletePopupComponent
    ],
    entryComponents: [RegioneComponent, RegioneUpdateComponent, RegioneDeleteDialogComponent, RegioneDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AkzoJhApplicationRegioneModule {}
