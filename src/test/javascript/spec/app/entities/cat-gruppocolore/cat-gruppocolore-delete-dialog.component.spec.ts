/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatGruppocoloreDeleteDialogComponent } from 'app/entities/cat-gruppocolore/cat-gruppocolore-delete-dialog.component';
import { CatGruppocoloreService } from 'app/entities/cat-gruppocolore/cat-gruppocolore.service';

describe('Component Tests', () => {
    describe('CatGruppocolore Management Delete Component', () => {
        let comp: CatGruppocoloreDeleteDialogComponent;
        let fixture: ComponentFixture<CatGruppocoloreDeleteDialogComponent>;
        let service: CatGruppocoloreService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatGruppocoloreDeleteDialogComponent]
            })
                .overrideTemplate(CatGruppocoloreDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatGruppocoloreDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatGruppocoloreService);
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
