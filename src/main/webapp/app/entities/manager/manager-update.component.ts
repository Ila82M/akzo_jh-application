import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IManager } from 'app/shared/model/manager.model';
import { ManagerService } from './manager.service';

@Component({
    selector: 'jhi-manager-update',
    templateUrl: './manager-update.component.html'
})
export class ManagerUpdateComponent implements OnInit {
    manager: IManager;
    isSaving: boolean;

    constructor(private managerService: ManagerService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ manager }) => {
            this.manager = manager;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.manager.id !== undefined) {
            this.subscribeToSaveResponse(this.managerService.update(this.manager));
        } else {
            this.subscribeToSaveResponse(this.managerService.create(this.manager));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IManager>>) {
        result.subscribe((res: HttpResponse<IManager>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
