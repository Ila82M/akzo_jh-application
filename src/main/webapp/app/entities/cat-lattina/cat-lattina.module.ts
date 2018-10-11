import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AkzoJhApplicationSharedModule } from 'app/shared';
import {
    CatLattinaComponent,
    CatLattinaDetailComponent,
    CatLattinaUpdateComponent,
    CatLattinaDeletePopupComponent,
    CatLattinaDeleteDialogComponent,
    catLattinaRoute,
    catLattinaPopupRoute
} from './';

const ENTITY_STATES = [...catLattinaRoute, ...catLattinaPopupRoute];

@NgModule({
    imports: [AkzoJhApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CatLattinaComponent,
        CatLattinaDetailComponent,
        CatLattinaUpdateComponent,
        CatLattinaDeleteDialogComponent,
        CatLattinaDeletePopupComponent
    ],
    entryComponents: [CatLattinaComponent, CatLattinaUpdateComponent, CatLattinaDeleteDialogComponent, CatLattinaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AkzoJhApplicationCatLattinaModule {}
