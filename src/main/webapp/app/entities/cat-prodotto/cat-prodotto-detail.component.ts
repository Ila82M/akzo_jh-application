import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatProdotto } from 'app/shared/model/cat-prodotto.model';

@Component({
    selector: 'jhi-cat-prodotto-detail',
    templateUrl: './cat-prodotto-detail.component.html'
})
export class CatProdottoDetailComponent implements OnInit {
    catProdotto: ICatProdotto;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catProdotto }) => {
            this.catProdotto = catProdotto;
        });
    }

    previousState() {
        window.history.back();
    }
}
