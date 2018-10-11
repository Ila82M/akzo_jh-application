import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICatTipoprodotto } from 'app/shared/model/cat-tipoprodotto.model';
import { CatTipoprodottoService } from './cat-tipoprodotto.service';

@Component({
    selector: 'jhi-cat-tipoprodotto-update',
    templateUrl: './cat-tipoprodotto-update.component.html'
})
export class CatTipoprodottoUpdateComponent implements OnInit {
    catTipoprodotto: ICatTipoprodotto;
    isSaving: boolean;

    constructor(private catTipoprodottoService: CatTipoprodottoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ catTipoprodotto }) => {
            this.catTipoprodotto = catTipoprodotto;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.catTipoprodotto.id !== undefined) {
            this.subscribeToSaveResponse(this.catTipoprodottoService.update(this.catTipoprodotto));
        } else {
            this.subscribeToSaveResponse(this.catTipoprodottoService.create(this.catTipoprodotto));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICatTipoprodotto>>) {
        result.subscribe((res: HttpResponse<ICatTipoprodotto>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
