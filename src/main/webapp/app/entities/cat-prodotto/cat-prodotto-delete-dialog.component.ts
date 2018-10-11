import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatProdotto } from 'app/shared/model/cat-prodotto.model';
import { CatProdottoService } from './cat-prodotto.service';

@Component({
    selector: 'jhi-cat-prodotto-delete-dialog',
    templateUrl: './cat-prodotto-delete-dialog.component.html'
})
export class CatProdottoDeleteDialogComponent {
    catProdotto: ICatProdotto;

    constructor(
        private catProdottoService: CatProdottoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.catProdottoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'catProdottoListModification',
                content: 'Deleted an catProdotto'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cat-prodotto-delete-popup',
    template: ''
})
export class CatProdottoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catProdotto }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CatProdottoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.catProdotto = catProdotto;
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
