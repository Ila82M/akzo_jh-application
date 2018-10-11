import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRegione } from 'app/shared/model/regione.model';

@Component({
    selector: 'jhi-regione-detail',
    templateUrl: './regione-detail.component.html'
})
export class RegioneDetailComponent implements OnInit {
    regione: IRegione;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ regione }) => {
            this.regione = regione;
        });
    }

    previousState() {
        window.history.back();
    }
}
