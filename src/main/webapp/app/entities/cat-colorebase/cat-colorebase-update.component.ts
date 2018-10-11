import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICatColorebase } from 'app/shared/model/cat-colorebase.model';
import { CatColorebaseService } from './cat-colorebase.service';
import { ICatBase } from 'app/shared/model/cat-base.model';
import { CatBaseService } from 'app/entities/cat-base';
import { ICatGruppocolore } from 'app/shared/model/cat-gruppocolore.model';
import { CatGruppocoloreService } from 'app/entities/cat-gruppocolore';
import { ICatTipocolore } from 'app/shared/model/cat-tipocolore.model';
import { CatTipocoloreService } from 'app/entities/cat-tipocolore';

@Component({
    selector: 'jhi-cat-colorebase-update',
    templateUrl: './cat-colorebase-update.component.html'
})
export class CatColorebaseUpdateComponent implements OnInit {
    catColorebase: ICatColorebase;
    isSaving: boolean;

    catbases: ICatBase[];

    catgruppocolores: ICatGruppocolore[];

    cattipocolores: ICatTipocolore[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private catColorebaseService: CatColorebaseService,
        private catBaseService: CatBaseService,
        private catGruppocoloreService: CatGruppocoloreService,
        private catTipocoloreService: CatTipocoloreService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ catColorebase }) => {
            this.catColorebase = catColorebase;
        });
        this.catBaseService.query().subscribe(
            (res: HttpResponse<ICatBase[]>) => {
                this.catbases = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.catGruppocoloreService.query().subscribe(
            (res: HttpResponse<ICatGruppocolore[]>) => {
                this.catgruppocolores = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.catTipocoloreService.query().subscribe(
            (res: HttpResponse<ICatTipocolore[]>) => {
                this.cattipocolores = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.catColorebase.id !== undefined) {
            this.subscribeToSaveResponse(this.catColorebaseService.update(this.catColorebase));
        } else {
            this.subscribeToSaveResponse(this.catColorebaseService.create(this.catColorebase));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICatColorebase>>) {
        result.subscribe((res: HttpResponse<ICatColorebase>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCatBaseById(index: number, item: ICatBase) {
        return item.id;
    }

    trackCatGruppocoloreById(index: number, item: ICatGruppocolore) {
        return item.id;
    }

    trackCatTipocoloreById(index: number, item: ICatTipocolore) {
        return item.id;
    }
}
