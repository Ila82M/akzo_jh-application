import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatTipoprodotto } from 'app/shared/model/cat-tipoprodotto.model';
import { CatTipoprodottoService } from './cat-tipoprodotto.service';

@Component({
    selector: 'jhi-cat-tipoprodotto-delete-dialog',
    templateUrl: './cat-tipoprodotto-delete-dialog.component.html'
})
export class CatTipoprodottoDeleteDialogComponent {
    catTipoprodotto: ICatTipoprodotto;

    constructor(
        private catTipoprodottoService: CatTipoprodottoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.catTipoprodottoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'catTipoprodottoListModification',
                content: 'Deleted an catTipoprodotto'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cat-tipoprodotto-delete-popup',
    template: ''
})
export class CatTipoprodottoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catTipoprodotto }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CatTipoprodottoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.catTipoprodotto = catTipoprodotto;
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
