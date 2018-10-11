/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatFamigliaDeleteDialogComponent } from 'app/entities/cat-famiglia/cat-famiglia-delete-dialog.component';
import { CatFamigliaService } from 'app/entities/cat-famiglia/cat-famiglia.service';

describe('Component Tests', () => {
    describe('CatFamiglia Management Delete Component', () => {
        let comp: CatFamigliaDeleteDialogComponent;
        let fixture: ComponentFixture<CatFamigliaDeleteDialogComponent>;
        let service: CatFamigliaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatFamigliaDeleteDialogComponent]
            })
                .overrideTemplate(CatFamigliaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatFamigliaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatFamigliaService);
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
