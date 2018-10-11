export interface ICatFamiglia {
    id?: number;
    marchio?: string;
    attivo?: string;
}

export class CatFamiglia implements ICatFamiglia {
    constructor(public id?: number, public marchio?: string, public attivo?: string) {}
}
