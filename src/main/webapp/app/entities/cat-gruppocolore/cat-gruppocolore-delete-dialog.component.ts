import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatGruppocolore } from 'app/shared/model/cat-gruppocolore.model';
import { CatGruppocoloreService } from './cat-gruppocolore.service';

@Component({
    selector: 'jhi-cat-gruppocolore-delete-dialog',
    templateUrl: './cat-gruppocolore-delete-dialog.component.html'
})
export class CatGruppocoloreDeleteDialogComponent {
    catGruppocolore: ICatGruppocolore;

    constructor(
        private catGruppocoloreService: CatGruppocoloreService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.catGruppocoloreService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'catGruppocoloreListModification',
                content: 'Deleted an catGruppocolore'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cat-gruppocolore-delete-popup',
    template: ''
})
export class CatGruppocoloreDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catGruppocolore }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CatGruppocoloreDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.catGruppocolore = catGruppocolore;
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
