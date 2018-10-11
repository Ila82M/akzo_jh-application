import { IRegione } from 'app/shared/model//regione.model';

export interface IProvincia {
    id?: number;
    codProvincia?: number;
    nome?: string;
    sigla?: string;
    codRegione?: IRegione;
}

export class Provincia implements IProvincia {
    constructor(
        public id?: number,
        public codProvincia?: number,
        public nome?: string,
        public sigla?: string,
        public codRegione?: IRegione
    ) {}
}
