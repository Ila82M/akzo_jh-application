import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICatBase } from 'app/shared/model/cat-base.model';
import { CatBaseService } from './cat-base.service';

@Component({
    selector: 'jhi-cat-base-update',
    templateUrl: './cat-base-update.component.html'
})
export class CatBaseUpdateComponent implements OnInit {
    catBase: ICatBase;
    isSaving: boolean;

    constructor(private catBaseService: CatBaseService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ catBase }) => {
            this.catBase = catBase;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.catBase.id !== undefined) {
            this.subscribeToSaveResponse(this.catBaseService.update(this.catBase));
        } else {
            this.subscribeToSaveResponse(this.catBaseService.create(this.catBase));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICatBase>>) {
        result.subscribe((res: HttpResponse<ICatBase>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
