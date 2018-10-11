import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatFamiglia } from 'app/shared/model/cat-famiglia.model';

@Component({
    selector: 'jhi-cat-famiglia-detail',
    templateUrl: './cat-famiglia-detail.component.html'
})
export class CatFamigliaDetailComponent implements OnInit {
    catFamiglia: ICatFamiglia;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catFamiglia }) => {
            this.catFamiglia = catFamiglia;
        });
    }

    previousState() {
        window.history.back();
    }
}
