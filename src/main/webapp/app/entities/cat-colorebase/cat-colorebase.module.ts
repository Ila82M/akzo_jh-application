import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AkzoJhApplicationSharedModule } from 'app/shared';
import {
    CatColorebaseComponent,
    CatColorebaseDetailComponent,
    CatColorebaseUpdateComponent,
    CatColorebaseDeletePopupComponent,
    CatColorebaseDeleteDialogComponent,
    catColorebaseRoute,
    catColorebasePopupRoute
} from './';

const ENTITY_STATES = [...catColorebaseRoute, ...catColorebasePopupRoute];

@NgModule({
    imports: [AkzoJhApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CatColorebaseComponent,
        CatColorebaseDetailComponent,
        CatColorebaseUpdateComponent,
        CatColorebaseDeleteDialogComponent,
        CatColorebaseDeletePopupComponent
    ],
    entryComponents: [
        CatColorebaseComponent,
        CatColorebaseUpdateComponent,
        CatColorebaseDeleteDialogComponent,
        CatColorebaseDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AkzoJhApplicationCatColorebaseModule {}
