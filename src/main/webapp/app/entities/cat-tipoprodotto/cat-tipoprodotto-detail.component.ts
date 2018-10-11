import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatTipoprodotto } from 'app/shared/model/cat-tipoprodotto.model';

@Component({
    selector: 'jhi-cat-tipoprodotto-detail',
    templateUrl: './cat-tipoprodotto-detail.component.html'
})
export class CatTipoprodottoDetailComponent implements OnInit {
    catTipoprodotto: ICatTipoprodotto;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catTipoprodotto }) => {
            this.catTipoprodotto = catTipoprodotto;
        });
    }

    previousState() {
        window.history.back();
    }
}
