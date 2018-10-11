import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICatFamiglia } from 'app/shared/model/cat-famiglia.model';
import { CatFamigliaService } from './cat-famiglia.service';

@Component({
    selector: 'jhi-cat-famiglia-update',
    templateUrl: './cat-famiglia-update.component.html'
})
export class CatFamigliaUpdateComponent implements OnInit {
    catFamiglia: ICatFamiglia;
    isSaving: boolean;

    constructor(private catFamigliaService: CatFamigliaService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ catFamiglia }) => {
            this.catFamiglia = catFamiglia;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.catFamiglia.id !== undefined) {
            this.subscribeToSaveResponse(this.catFamigliaService.update(this.catFamiglia));
        } else {
            this.subscribeToSaveResponse(this.catFamigliaService.create(this.catFamiglia));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICatFamiglia>>) {
        result.subscribe((res: HttpResponse<ICatFamiglia>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
