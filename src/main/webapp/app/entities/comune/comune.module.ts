import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AkzoJhApplicationSharedModule } from 'app/shared';
import {
    ComuneComponent,
    ComuneDetailComponent,
    ComuneUpdateComponent,
    ComuneDeletePopupComponent,
    ComuneDeleteDialogComponent,
    comuneRoute,
    comunePopupRoute
} from './';

const ENTITY_STATES = [...comuneRoute, ...comunePopupRoute];

@NgModule({
    imports: [AkzoJhApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ComuneComponent, ComuneDetailComponent, ComuneUpdateComponent, ComuneDeleteDialogComponent, ComuneDeletePopupComponent],
    entryComponents: [ComuneComponent, ComuneUpdateComponent, ComuneDeleteDialogComponent, ComuneDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AkzoJhApplicationComuneModule {}
