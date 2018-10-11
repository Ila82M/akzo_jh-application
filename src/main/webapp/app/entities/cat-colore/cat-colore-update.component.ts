import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICatColore } from 'app/shared/model/cat-colore.model';
import { CatColoreService } from './cat-colore.service';
import { ICatGruppocolore } from 'app/shared/model/cat-gruppocolore.model';
import { CatGruppocoloreService } from 'app/entities/cat-gruppocolore';

@Component({
    selector: 'jhi-cat-colore-update',
    templateUrl: './cat-colore-update.component.html'
})
export class CatColoreUpdateComponent implements OnInit {
    catColore: ICatColore;
    isSaving: boolean;

    catgruppocolores: ICatGruppocolore[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private catColoreService: CatColoreService,
        private catGruppocoloreService: CatGruppocoloreService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ catColore }) => {
            this.catColore = catColore;
        });
        this.catGruppocoloreService.query().subscribe(
            (res: HttpResponse<ICatGruppocolore[]>) => {
                this.catgruppocolores = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.catColore.id !== undefined) {
            this.subscribeToSaveResponse(this.catColoreService.update(this.catColore));
        } else {
            this.subscribeToSaveResponse(this.catColoreService.create(this.catColore));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICatColore>>) {
        result.subscribe((res: HttpResponse<ICatColore>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCatGruppocoloreById(index: number, item: ICatGruppocolore) {
        return item.id;
    }
}
