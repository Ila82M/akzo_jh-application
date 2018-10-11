import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IRegione } from 'app/shared/model/regione.model';
import { RegioneService } from './regione.service';

@Component({
    selector: 'jhi-regione-update',
    templateUrl: './regione-update.component.html'
})
export class RegioneUpdateComponent implements OnInit {
    regione: IRegione;
    isSaving: boolean;

    constructor(private regioneService: RegioneService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ regione }) => {
            this.regione = regione;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.regione.id !== undefined) {
            this.subscribeToSaveResponse(this.regioneService.update(this.regione));
        } else {
            this.subscribeToSaveResponse(this.regioneService.create(this.regione));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRegione>>) {
        result.subscribe((res: HttpResponse<IRegione>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
