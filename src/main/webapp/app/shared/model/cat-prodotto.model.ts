import { Moment } from 'moment';
import { ICatFamiglia } from 'app/shared/model//cat-famiglia.model';
import { ICatTipoprodotto } from 'app/shared/model//cat-tipoprodotto.model';

export interface ICatProdotto {
    id?: number;
    nome?: string;
    descrizione?: string;
    sottotipo?: string;
    note?: string;
    schedaTecnica?: string;
    schedaSicurezza?: string;
    img?: string;
    dataUpdate?: Moment;
    pubblicato?: string;
    misura?: string;
    famiglia?: ICatFamiglia;
    tipoprodotto?: ICatTipoprodotto;
}

export class CatProdotto implements ICatProdotto {
    constructor(
        public id?: number,
        public nome?: string,
        public descrizione?: string,
        public sottotipo?: string,
        public note?: string,
        public schedaTecnica?: string,
        public schedaSicurezza?: string,
        public img?: string,
        public dataUpdate?: Moment,
        public pubblicato?: string,
        public misura?: string,
        public famiglia?: ICatFamiglia,
        public tipoprodotto?: ICatTipoprodotto
    ) {}
}
