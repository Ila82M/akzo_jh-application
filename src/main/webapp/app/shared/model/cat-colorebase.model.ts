export interface ICatColorebase {
    id?: number;
    resa?: number;
    baseId?: number;
    gruppocoloreId?: number;
    tipocoloreId?: number;
}

export class CatColorebase implements ICatColorebase {
    constructor(
        public id?: number,
        public resa?: number,
        public baseId?: number,
        public gruppocoloreId?: number,
        public tipocoloreId?: number
    ) {}
}
