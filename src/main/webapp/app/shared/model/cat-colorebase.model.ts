import { ICatBase } from 'app/shared/model//cat-base.model';
import { ICatGruppocolore } from 'app/shared/model//cat-gruppocolore.model';
import { ICatTipocolore } from 'app/shared/model//cat-tipocolore.model';

export interface ICatColorebase {
    id?: number;
    resa?: number;
    base?: ICatBase;
    gruppocolore?: ICatGruppocolore;
    tipocolore?: ICatTipocolore;
}

export class CatColorebase implements ICatColorebase {
    constructor(
        public id?: number,
        public resa?: number,
        public base?: ICatBase,
        public gruppocolore?: ICatGruppocolore,
        public tipocolore?: ICatTipocolore
    ) {}
}
