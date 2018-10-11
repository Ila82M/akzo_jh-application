import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ICatProdotto } from 'app/shared/model/cat-prodotto.model';
import { CatProdottoService } from './cat-prodotto.service';
import { ICatFamiglia } from 'app/shared/model/cat-famiglia.model';
import { CatFamigliaService } from 'app/entities/cat-famiglia';
import { ICatTipoprodotto } from 'app/shared/model/cat-tipoprodotto.model';
import { CatTipoprodottoService } from 'app/entities/cat-tipoprodotto';

@Component({
    selector: 'jhi-cat-prodotto-update',
    templateUrl: './cat-prodotto-update.component.html'
})
export class CatProdottoUpdateComponent implements OnInit {
    catProdotto: ICatProdotto;
    isSaving: boolean;

    catfamiglias: ICatFamiglia[];

    cattipoprodottos: ICatTipoprodotto[];
    dataUpdate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private catProdottoService: CatProdottoService,
        private catFamigliaService: CatFamigliaService,
        private catTipoprodottoService: CatTipoprodottoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ catProdotto }) => {
            this.catProdotto = catProdotto;
            this.dataUpdate = this.catProdotto.dataUpdate != null ? this.catProdotto.dataUpdate.format(DATE_TIME_FORMAT) : null;
        });
        this.catFamigliaService.query().subscribe(
            (res: HttpResponse<ICatFamiglia[]>) => {
                this.catfamiglias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.catTipoprodottoService.query().subscribe(
            (res: HttpResponse<ICatTipoprodotto[]>) => {
                this.cattipoprodottos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.catProdotto.dataUpdate = this.dataUpdate != null ? moment(this.dataUpdate, DATE_TIME_FORMAT) : null;
        if (this.catProdotto.id !== undefined) {
            this.subscribeToSaveResponse(this.catProdottoService.update(this.catProdotto));
        } else {
            this.subscribeToSaveResponse(this.catProdottoService.create(this.catProdotto));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICatProdotto>>) {
        result.subscribe((res: HttpResponse<ICatProdotto>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCatFamigliaById(index: number, item: ICatFamiglia) {
        return item.id;
    }

    trackCatTipoprodottoById(index: number, item: ICatTipoprodotto) {
        return item.id;
    }
}
