import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatBase } from 'app/shared/model/cat-base.model';

@Component({
    selector: 'jhi-cat-base-detail',
    templateUrl: './cat-base-detail.component.html'
})
export class CatBaseDetailComponent implements OnInit {
    catBase: ICatBase;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catBase }) => {
            this.catBase = catBase;
        });
    }

    previousState() {
        window.history.back();
    }
}
