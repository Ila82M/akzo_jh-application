import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatColorebase } from 'app/shared/model/cat-colorebase.model';
import { CatColorebaseService } from './cat-colorebase.service';

@Component({
    selector: 'jhi-cat-colorebase-delete-dialog',
    templateUrl: './cat-colorebase-delete-dialog.component.html'
})
export class CatColorebaseDeleteDialogComponent {
    catColorebase: ICatColorebase;

    constructor(
        private catColorebaseService: CatColorebaseService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.catColorebaseService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'catColorebaseListModification',
                content: 'Deleted an catColorebase'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cat-colorebase-delete-popup',
    template: ''
})
export class CatColorebaseDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catColorebase }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CatColorebaseDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.catColorebase = catColorebase;
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
