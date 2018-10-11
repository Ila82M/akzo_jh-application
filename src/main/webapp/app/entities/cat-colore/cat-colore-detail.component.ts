import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatColore } from 'app/shared/model/cat-colore.model';

@Component({
    selector: 'jhi-cat-colore-detail',
    templateUrl: './cat-colore-detail.component.html'
})
export class CatColoreDetailComponent implements OnInit {
    catColore: ICatColore;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catColore }) => {
            this.catColore = catColore;
        });
    }

    previousState() {
        window.history.back();
    }
}
