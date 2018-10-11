/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatColorebaseUpdateComponent } from 'app/entities/cat-colorebase/cat-colorebase-update.component';
import { CatColorebaseService } from 'app/entities/cat-colorebase/cat-colorebase.service';
import { CatColorebase } from 'app/shared/model/cat-colorebase.model';

describe('Component Tests', () => {
    describe('CatColorebase Management Update Component', () => {
        let comp: CatColorebaseUpdateComponent;
        let fixture: ComponentFixture<CatColorebaseUpdateComponent>;
        let service: CatColorebaseService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatColorebaseUpdateComponent]
            })
                .overrideTemplate(CatColorebaseUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CatColorebaseUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatColorebaseService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CatColorebase(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catColorebase = entity;
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
                    const entity = new CatColorebase();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catColorebase = entity;
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
