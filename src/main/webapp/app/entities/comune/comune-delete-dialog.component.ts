import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComune } from 'app/shared/model/comune.model';
import { ComuneService } from './comune.service';

@Component({
    selector: 'jhi-comune-delete-dialog',
    templateUrl: './comune-delete-dialog.component.html'
})
export class ComuneDeleteDialogComponent {
    comune: IComune;

    constructor(private comuneService: ComuneService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.comuneService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'comuneListModification',
                content: 'Deleted an comune'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-comune-delete-popup',
    template: ''
})
export class ComuneDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ comune }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ComuneDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.comune = comune;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
