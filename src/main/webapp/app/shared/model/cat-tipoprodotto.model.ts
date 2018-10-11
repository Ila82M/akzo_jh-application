export interface ICatTipoprodotto {
    id?: number;
    descrizione?: string;
}

export class CatTipoprodotto implements ICatTipoprodotto {
    constructor(public id?: number, public descrizione?: string) {}
}
