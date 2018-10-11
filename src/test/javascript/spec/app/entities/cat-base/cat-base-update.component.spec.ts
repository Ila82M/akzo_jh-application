/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatBaseUpdateComponent } from 'app/entities/cat-base/cat-base-update.component';
import { CatBaseService } from 'app/entities/cat-base/cat-base.service';
import { CatBase } from 'app/shared/model/cat-base.model';

describe('Component Tests', () => {
    describe('CatBase Management Update Component', () => {
        let comp: CatBaseUpdateComponent;
        let fixture: ComponentFixture<CatBaseUpdateComponent>;
        let service: CatBaseService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatBaseUpdateComponent]
            })
                .overrideTemplate(CatBaseUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CatBaseUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatBaseService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CatBase(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catBase = entity;
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
                    const entity = new CatBase();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catBase = entity;
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
