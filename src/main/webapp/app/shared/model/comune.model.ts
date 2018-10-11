import { IProvincia } from 'app/shared/model//provincia.model';

export interface IComune {
    id?: number;
    cod?: number;
    nome?: string;
    codProvincia?: IProvincia;
}

export class Comune implements IComune {
    constructor(public id?: number, public cod?: number, public nome?: string, public codProvincia?: IProvincia) {}
}
