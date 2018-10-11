import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IComune } from 'app/shared/model/comune.model';
import { ComuneService } from './comune.service';
import { IProvincia } from 'app/shared/model/provincia.model';
import { ProvinciaService } from 'app/entities/provincia';

@Component({
    selector: 'jhi-comune-update',
    templateUrl: './comune-update.component.html'
})
export class ComuneUpdateComponent implements OnInit {
    comune: IComune;
    isSaving: boolean;

    provincias: IProvincia[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private comuneService: ComuneService,
        private provinciaService: ProvinciaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ comune }) => {
            this.comune = comune;
        });
        this.provinciaService.query().subscribe(
            (res: HttpResponse<IProvincia[]>) => {
                this.provincias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.comune.id !== undefined) {
            this.subscribeToSaveResponse(this.comuneService.update(this.comune));
        } else {
            this.subscribeToSaveResponse(this.comuneService.create(this.comune));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IComune>>) {
        result.subscribe((res: HttpResponse<IComune>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackProvinciaById(index: number, item: IProvincia) {
        return item.id;
    }
}
