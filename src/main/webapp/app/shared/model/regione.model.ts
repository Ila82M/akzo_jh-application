export interface IRegione {
    id?: number;
    codRegione?: number;
    nome?: string;
}

export class Regione implements IRegione {
    constructor(public id?: number, public codRegione?: number, public nome?: string) {}
}
