export interface IComune {
    id?: number;
    cod?: number;
    nome?: string;
    codProvinciaId?: number;
}

export class Comune implements IComune {
    constructor(public id?: number, public cod?: number, public nome?: string, public codProvinciaId?: number) {}
}
