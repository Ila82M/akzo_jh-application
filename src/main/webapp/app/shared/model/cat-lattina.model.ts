import { Moment } from 'moment';
import { ICatColorebase } from 'app/shared/model//cat-colorebase.model';

export interface ICatLattina {
    id?: number;
    litraggio?: number;
    codice?: string;
    prezzo?: number;
    dataUpdate?: Moment;
    colorebase?: ICatColorebase;
}

export class CatLattina implements ICatLattina {
    constructor(
        public id?: number,
        public litraggio?: number,
        public codice?: string,
        public prezzo?: number,
        public dataUpdate?: Moment,
        public colorebase?: ICatColorebase
    ) {}
}
