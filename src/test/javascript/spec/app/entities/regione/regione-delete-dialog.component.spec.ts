/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { RegioneDeleteDialogComponent } from 'app/entities/regione/regione-delete-dialog.component';
import { RegioneService } from 'app/entities/regione/regione.service';

describe('Component Tests', () => {
    describe('Regione Management Delete Component', () => {
        let comp: RegioneDeleteDialogComponent;
        let fixture: ComponentFixture<RegioneDeleteDialogComponent>;
        let service: RegioneService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [RegioneDeleteDialogComponent]
            })
                .overrideTemplate(RegioneDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RegioneDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RegioneService);
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
