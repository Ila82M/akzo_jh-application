/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { ComuneUpdateComponent } from 'app/entities/comune/comune-update.component';
import { ComuneService } from 'app/entities/comune/comune.service';
import { Comune } from 'app/shared/model/comune.model';

describe('Component Tests', () => {
    describe('Comune Management Update Component', () => {
        let comp: ComuneUpdateComponent;
        let fixture: ComponentFixture<ComuneUpdateComponent>;
        let service: ComuneService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [ComuneUpdateComponent]
            })
                .overrideTemplate(ComuneUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ComuneUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComuneService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Comune(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.comune = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Comune();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.comune = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
