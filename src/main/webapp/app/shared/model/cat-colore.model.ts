export interface ICatColore {
    id?: number;
    descrizione?: string;
    gruppocoloreId?: number;
}

export class CatColore implements ICatColore {
    constructor(public id?: number, public descrizione?: string, public gruppocoloreId?: number) {}
}
