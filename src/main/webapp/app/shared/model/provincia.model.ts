export interface IProvincia {
    id?: number;
    codProvincia?: number;
    nome?: string;
    sigla?: string;
    codRegioneId?: number;
}

export class Provincia implements IProvincia {
    constructor(
        public id?: number,
        public codProvincia?: number,
        public nome?: string,
        public sigla?: string,
        public codRegioneId?: number
    ) {}
}
