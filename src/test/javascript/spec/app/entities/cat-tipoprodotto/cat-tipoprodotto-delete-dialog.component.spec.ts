/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatTipoprodottoDeleteDialogComponent } from 'app/entities/cat-tipoprodotto/cat-tipoprodotto-delete-dialog.component';
import { CatTipoprodottoService } from 'app/entities/cat-tipoprodotto/cat-tipoprodotto.service';

describe('Component Tests', () => {
    describe('CatTipoprodotto Management Delete Component', () => {
        let comp: CatTipoprodottoDeleteDialogComponent;
        let fixture: ComponentFixture<CatTipoprodottoDeleteDialogComponent>;
        let service: CatTipoprodottoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatTipoprodottoDeleteDialogComponent]
            })
                .overrideTemplate(CatTipoprodottoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatTipoprodottoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatTipoprodottoService);
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
