import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatBase } from 'app/shared/model/cat-base.model';
import { CatBaseService } from './cat-base.service';

@Component({
    selector: 'jhi-cat-base-delete-dialog',
    templateUrl: './cat-base-delete-dialog.component.html'
})
export class CatBaseDeleteDialogComponent {
    catBase: ICatBase;

    constructor(private catBaseService: CatBaseService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.catBaseService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'catBaseListModification',
                content: 'Deleted an catBase'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cat-base-delete-popup',
    template: ''
})
export class CatBaseDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catBase }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CatBaseDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.catBase = catBase;
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
