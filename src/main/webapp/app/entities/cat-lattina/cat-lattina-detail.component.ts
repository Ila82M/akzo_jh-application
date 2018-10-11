import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatLattina } from 'app/shared/model/cat-lattina.model';

@Component({
    selector: 'jhi-cat-lattina-detail',
    templateUrl: './cat-lattina-detail.component.html'
})
export class CatLattinaDetailComponent implements OnInit {
    catLattina: ICatLattina;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catLattina }) => {
            this.catLattina = catLattina;
        });
    }

    previousState() {
        window.history.back();
    }
}
