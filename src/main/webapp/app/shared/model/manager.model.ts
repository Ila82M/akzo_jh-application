export interface IManager {
    id?: number;
    iduser?: number;
    areamanager?: string;
    agente?: string;
    npuntivendita?: number;
}

export class Manager implements IManager {
    constructor(
        public id?: number,
        public iduser?: number,
        public areamanager?: string,
        public agente?: string,
        public npuntivendita?: number
    ) {}
}
