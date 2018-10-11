/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatFamigliaUpdateComponent } from 'app/entities/cat-famiglia/cat-famiglia-update.component';
import { CatFamigliaService } from 'app/entities/cat-famiglia/cat-famiglia.service';
import { CatFamiglia } from 'app/shared/model/cat-famiglia.model';

describe('Component Tests', () => {
    describe('CatFamiglia Management Update Component', () => {
        let comp: CatFamigliaUpdateComponent;
        let fixture: ComponentFixture<CatFamigliaUpdateComponent>;
        let service: CatFamigliaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatFamigliaUpdateComponent]
            })
                .overrideTemplate(CatFamigliaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CatFamigliaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatFamigliaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CatFamiglia(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catFamiglia = entity;
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
                    const entity = new CatFamiglia();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catFamiglia = entity;
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
