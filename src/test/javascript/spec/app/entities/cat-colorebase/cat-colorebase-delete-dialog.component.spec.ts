/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatColorebaseDeleteDialogComponent } from 'app/entities/cat-colorebase/cat-colorebase-delete-dialog.component';
import { CatColorebaseService } from 'app/entities/cat-colorebase/cat-colorebase.service';

describe('Component Tests', () => {
    describe('CatColorebase Management Delete Component', () => {
        let comp: CatColorebaseDeleteDialogComponent;
        let fixture: ComponentFixture<CatColorebaseDeleteDialogComponent>;
        let service: CatColorebaseService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatColorebaseDeleteDialogComponent]
            })
                .overrideTemplate(CatColorebaseDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatColorebaseDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatColorebaseService);
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
