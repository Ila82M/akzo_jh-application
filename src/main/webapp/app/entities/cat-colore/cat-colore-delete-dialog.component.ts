import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatColore } from 'app/shared/model/cat-colore.model';
import { CatColoreService } from './cat-colore.service';

@Component({
    selector: 'jhi-cat-colore-delete-dialog',
    templateUrl: './cat-colore-delete-dialog.component.html'
})
export class CatColoreDeleteDialogComponent {
    catColore: ICatColore;

    constructor(private catColoreService: CatColoreService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.catColoreService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'catColoreListModification',
                content: 'Deleted an catColore'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cat-colore-delete-popup',
    template: ''
})
export class CatColoreDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catColore }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CatColoreDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.catColore = catColore;
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
