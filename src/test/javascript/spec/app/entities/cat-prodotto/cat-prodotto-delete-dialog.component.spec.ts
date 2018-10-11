/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatProdottoDeleteDialogComponent } from 'app/entities/cat-prodotto/cat-prodotto-delete-dialog.component';
import { CatProdottoService } from 'app/entities/cat-prodotto/cat-prodotto.service';

describe('Component Tests', () => {
    describe('CatProdotto Management Delete Component', () => {
        let comp: CatProdottoDeleteDialogComponent;
        let fixture: ComponentFixture<CatProdottoDeleteDialogComponent>;
        let service: CatProdottoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatProdottoDeleteDialogComponent]
            })
                .overrideTemplate(CatProdottoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatProdottoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatProdottoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
