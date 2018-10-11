import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatGruppocolore } from 'app/shared/model/cat-gruppocolore.model';

@Component({
    selector: 'jhi-cat-gruppocolore-detail',
    templateUrl: './cat-gruppocolore-detail.component.html'
})
export class CatGruppocoloreDetailComponent implements OnInit {
    catGruppocolore: ICatGruppocolore;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catGruppocolore }) => {
            this.catGruppocolore = catGruppocolore;
        });
    }

    previousState() {
        window.history.back();
    }
}
