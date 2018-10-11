export interface ICatGruppocolore {
    id?: number;
    descrizione?: string;
}

export class CatGruppocolore implements ICatGruppocolore {
    constructor(public id?: number, public descrizione?: string) {}
}
