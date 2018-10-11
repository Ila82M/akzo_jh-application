import { ICatGruppocolore } from 'app/shared/model//cat-gruppocolore.model';

export interface ICatColore {
    id?: number;
    descrizione?: string;
    gruppocolore?: ICatGruppocolore;
}

export class CatColore implements ICatColore {
    constructor(public id?: number, public descrizione?: string, public gruppocolore?: ICatGruppocolore) {}
}
