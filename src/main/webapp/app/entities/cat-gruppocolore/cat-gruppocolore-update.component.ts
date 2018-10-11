import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICatGruppocolore } from 'app/shared/model/cat-gruppocolore.model';
import { CatGruppocoloreService } from './cat-gruppocolore.service';

@Component({
    selector: 'jhi-cat-gruppocolore-update',
    templateUrl: './cat-gruppocolore-update.component.html'
})
export class CatGruppocoloreUpdateComponent implements OnInit {
    catGruppocolore: ICatGruppocolore;
    isSaving: boolean;

    constructor(private catGruppocoloreService: CatGruppocoloreService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ catGruppocolore }) => {
            this.catGruppocolore = catGruppocolore;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.catGruppocolore.id !== undefined) {
            this.subscribeToSaveResponse(this.catGruppocoloreService.update(this.catGruppocolore));
        } else {
            this.subscribeToSaveResponse(this.catGruppocoloreService.create(this.catGruppocolore));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICatGruppocolore>>) {
        result.subscribe((res: HttpResponse<ICatGruppocolore>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
