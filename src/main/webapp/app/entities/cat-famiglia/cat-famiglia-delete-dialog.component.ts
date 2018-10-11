import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatFamiglia } from 'app/shared/model/cat-famiglia.model';
import { CatFamigliaService } from './cat-famiglia.service';

@Component({
    selector: 'jhi-cat-famiglia-delete-dialog',
    templateUrl: './cat-famiglia-delete-dialog.component.html'
})
export class CatFamigliaDeleteDialogComponent {
    catFamiglia: ICatFamiglia;

    constructor(
        private catFamigliaService: CatFamigliaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.catFamigliaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'catFamigliaListModification',
                content: 'Deleted an catFamiglia'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cat-famiglia-delete-popup',
    template: ''
})
export class CatFamigliaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catFamiglia }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CatFamigliaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.catFamiglia = catFamiglia;
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
