import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatColorebase } from 'app/shared/model/cat-colorebase.model';

@Component({
    selector: 'jhi-cat-colorebase-detail',
    templateUrl: './cat-colorebase-detail.component.html'
})
export class CatColorebaseDetailComponent implements OnInit {
    catColorebase: ICatColorebase;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catColorebase }) => {
            this.catColorebase = catColorebase;
        });
    }

    previousState() {
        window.history.back();
    }
}
