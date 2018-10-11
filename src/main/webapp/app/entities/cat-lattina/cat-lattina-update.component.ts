import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ICatLattina } from 'app/shared/model/cat-lattina.model';
import { CatLattinaService } from './cat-lattina.service';
import { ICatColorebase } from 'app/shared/model/cat-colorebase.model';
import { CatColorebaseService } from 'app/entities/cat-colorebase';

@Component({
    selector: 'jhi-cat-lattina-update',
    templateUrl: './cat-lattina-update.component.html'
})
export class CatLattinaUpdateComponent implements OnInit {
    catLattina: ICatLattina;
    isSaving: boolean;

    catcolorebases: ICatColorebase[];
    dataUpdate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private catLattinaService: CatLattinaService,
        private catColorebaseService: CatColorebaseService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ catLattina }) => {
            this.catLattina = catLattina;
            this.dataUpdate = this.catLattina.dataUpdate != null ? this.catLattina.dataUpdate.format(DATE_TIME_FORMAT) : null;
        });
        this.catColorebaseService.query().subscribe(
            (res: HttpResponse<ICatColorebase[]>) => {
                this.catcolorebases = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.catLattina.dataUpdate = this.dataUpdate != null ? moment(this.dataUpdate, DATE_TIME_FORMAT) : null;
        if (this.catLattina.id !== undefined) {
            this.subscribeToSaveResponse(this.catLattinaService.update(this.catLattina));
        } else {
            this.subscribeToSaveResponse(this.catLattinaService.create(this.catLattina));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICatLattina>>) {
        result.subscribe((res: HttpResponse<ICatLattina>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCatColorebaseById(index: number, item: ICatColorebase) {
        return item.id;
    }
}
