import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AkzoJhApplicationCatBaseModule } from './cat-base/cat-base.module';
import { AkzoJhApplicationCatColorebaseModule } from './cat-colorebase/cat-colorebase.module';
import { AkzoJhApplicationCatColoreModule } from './cat-colore/cat-colore.module';
import { AkzoJhApplicationCatGruppocoloreModule } from './cat-gruppocolore/cat-gruppocolore.module';
import { AkzoJhApplicationCatLattinaModule } from './cat-lattina/cat-lattina.module';
import { AkzoJhApplicationCatTipocoloreModule } from './cat-tipocolore/cat-tipocolore.module';
import { AkzoJhApplicationCatFamigliaModule } from './cat-famiglia/cat-famiglia.module';
import { AkzoJhApplicationCatTipoprodottoModule } from './cat-tipoprodotto/cat-tipoprodotto.module';
import { AkzoJhApplicationCatProdottoModule } from './cat-prodotto/cat-prodotto.module';
import { AkzoJhApplicationComuneModule } from './comune/comune.module';
import { AkzoJhApplicationProvinciaModule } from './provincia/provincia.module';
import { AkzoJhApplicationRegioneModule } from './regione/regione.module';
import { AkzoJhApplicationManagerModule } from './manager/manager.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        AkzoJhApplicationCatBaseModule,
        AkzoJhApplicationCatColorebaseModule,
        AkzoJhApplicationCatColoreModule,
        AkzoJhApplicationCatGruppocoloreModule,
        AkzoJhApplicationCatLattinaModule,
        AkzoJhApplicationCatTipocoloreModule,
        AkzoJhApplicationCatFamigliaModule,
        AkzoJhApplicationCatTipoprodottoModule,
        AkzoJhApplicationCatProdottoModule,
        AkzoJhApplicationComuneModule,
        AkzoJhApplicationProvinciaModule,
        AkzoJhApplicationRegioneModule,
        AkzoJhApplicationManagerModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AkzoJhApplicationEntityModule {}
