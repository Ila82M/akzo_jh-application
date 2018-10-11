import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatTipocolore } from 'app/shared/model/cat-tipocolore.model';

@Component({
    selector: 'jhi-cat-tipocolore-detail',
    templateUrl: './cat-tipocolore-detail.component.html'
})
export class CatTipocoloreDetailComponent implements OnInit {
    catTipocolore: ICatTipocolore;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catTipocolore }) => {
            this.catTipocolore = catTipocolore;
        });
    }

    previousState() {
        window.history.back();
    }
}
