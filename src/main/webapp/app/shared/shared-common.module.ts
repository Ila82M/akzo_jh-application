import { NgModule } from '@angular/core';

import { AkzoJhApplicationSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [AkzoJhApplicationSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [AkzoJhApplicationSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class AkzoJhApplicationSharedCommonModule {}
