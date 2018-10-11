import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICatTipocolore } from 'app/shared/model/cat-tipocolore.model';
import { CatTipocoloreService } from './cat-tipocolore.service';

@Component({
    selector: 'jhi-cat-tipocolore-update',
    templateUrl: './cat-tipocolore-update.component.html'
})
export class CatTipocoloreUpdateComponent implements OnInit {
    catTipocolore: ICatTipocolore;
    isSaving: boolean;

    constructor(private catTipocoloreService: CatTipocoloreService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ catTipocolore }) => {
            this.catTipocolore = catTipocolore;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.catTipocolore.id !== undefined) {
            this.subscribeToSaveResponse(this.catTipocoloreService.update(this.catTipocolore));
        } else {
            this.subscribeToSaveResponse(this.catTipocoloreService.create(this.catTipocolore));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICatTipocolore>>) {
        result.subscribe((res: HttpResponse<ICatTipocolore>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
