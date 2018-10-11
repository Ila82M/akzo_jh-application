/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatTipocoloreDeleteDialogComponent } from 'app/entities/cat-tipocolore/cat-tipocolore-delete-dialog.component';
import { CatTipocoloreService } from 'app/entities/cat-tipocolore/cat-tipocolore.service';

describe('Component Tests', () => {
    describe('CatTipocolore Management Delete Component', () => {
        let comp: CatTipocoloreDeleteDialogComponent;
        let fixture: ComponentFixture<CatTipocoloreDeleteDialogComponent>;
        let service: CatTipocoloreService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatTipocoloreDeleteDialogComponent]
            })
                .overrideTemplate(CatTipocoloreDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatTipocoloreDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatTipocoloreService);
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
