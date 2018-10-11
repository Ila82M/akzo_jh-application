export interface ICatBase {
    id?: number;
    codbase?: string;
    descrizione?: string;
}

export class CatBase implements ICatBase {
    constructor(public id?: number, public codbase?: string, public descrizione?: string) {}
}
