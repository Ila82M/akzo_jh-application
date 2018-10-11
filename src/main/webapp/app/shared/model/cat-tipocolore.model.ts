export interface ICatTipocolore {
    id?: number;
    idProdotto?: number;
    descrizione?: string;
}

export class CatTipocolore implements ICatTipocolore {
    constructor(public id?: number, public idProdotto?: number, public descrizione?: string) {}
}
