/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatLattinaDeleteDialogComponent } from 'app/entities/cat-lattina/cat-lattina-delete-dialog.component';
import { CatLattinaService } from 'app/entities/cat-lattina/cat-lattina.service';

describe('Component Tests', () => {
    describe('CatLattina Management Delete Component', () => {
        let comp: CatLattinaDeleteDialogComponent;
        let fixture: ComponentFixture<CatLattinaDeleteDialogComponent>;
        let service: CatLattinaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatLattinaDeleteDialogComponent]
            })
                .overrideTemplate(CatLattinaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatLattinaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatLattinaService);
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
