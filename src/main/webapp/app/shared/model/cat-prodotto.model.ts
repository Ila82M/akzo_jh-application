import { Moment } from 'moment';

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
    famigliaId?: number;
    tipoprodottoId?: number;
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
        public famigliaId?: number,
        public tipoprodottoId?: number
    ) {}
}
