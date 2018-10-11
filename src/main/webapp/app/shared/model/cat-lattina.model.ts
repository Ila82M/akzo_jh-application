import { Moment } from 'moment';

export interface ICatLattina {
    id?: number;
    litraggio?: number;
    codice?: string;
    prezzo?: number;
    dataUpdate?: Moment;
    colorebaseId?: number;
}

export class CatLattina implements ICatLattina {
    constructor(
        public id?: number,
        public litraggio?: number,
        public codice?: string,
        public prezzo?: number,
        public dataUpdate?: Moment,
        public colorebaseId?: number
    ) {}
}
