import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatLattina } from 'app/shared/model/cat-lattina.model';
import { CatLattinaService } from './cat-lattina.service';

@Component({
    selector: 'jhi-cat-lattina-delete-dialog',
    templateUrl: './cat-lattina-delete-dialog.component.html'
})
export class CatLattinaDeleteDialogComponent {
    catLattina: ICatLattina;

    constructor(private catLattinaService: CatLattinaService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.catLattinaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'catLattinaListModification',
                content: 'Deleted an catLattina'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cat-lattina-delete-popup',
    template: ''
})
export class CatLattinaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catLattina }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CatLattinaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.catLattina = catLattina;
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
