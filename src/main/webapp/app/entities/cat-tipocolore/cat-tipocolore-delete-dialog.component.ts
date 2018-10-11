import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatTipocolore } from 'app/shared/model/cat-tipocolore.model';
import { CatTipocoloreService } from './cat-tipocolore.service';

@Component({
    selector: 'jhi-cat-tipocolore-delete-dialog',
    templateUrl: './cat-tipocolore-delete-dialog.component.html'
})
export class CatTipocoloreDeleteDialogComponent {
    catTipocolore: ICatTipocolore;

    constructor(
        private catTipocoloreService: CatTipocoloreService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.catTipocoloreService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'catTipocoloreListModification',
                content: 'Deleted an catTipocolore'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cat-tipocolore-delete-popup',
    template: ''
})
export class CatTipocoloreDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catTipocolore }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CatTipocoloreDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.catTipocolore = catTipocolore;
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
