import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AkzoJhApplicationSharedModule } from 'app/shared';
import {
    CatFamigliaComponent,
    CatFamigliaDetailComponent,
    CatFamigliaUpdateComponent,
    CatFamigliaDeletePopupComponent,
    CatFamigliaDeleteDialogComponent,
    catFamigliaRoute,
    catFamigliaPopupRoute
} from './';

const ENTITY_STATES = [...catFamigliaRoute, ...catFamigliaPopupRoute];

@NgModule({
    imports: [AkzoJhApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CatFamigliaComponent,
        CatFamigliaDetailComponent,
        CatFamigliaUpdateComponent,
        CatFamigliaDeleteDialogComponent,
        CatFamigliaDeletePopupComponent
    ],
    entryComponents: [CatFamigliaComponent, CatFamigliaUpdateComponent, CatFamigliaDeleteDialogComponent, CatFamigliaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AkzoJhApplicationCatFamigliaModule {}
