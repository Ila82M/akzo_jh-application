/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatGruppocoloreUpdateComponent } from 'app/entities/cat-gruppocolore/cat-gruppocolore-update.component';
import { CatGruppocoloreService } from 'app/entities/cat-gruppocolore/cat-gruppocolore.service';
import { CatGruppocolore } from 'app/shared/model/cat-gruppocolore.model';

describe('Component Tests', () => {
    describe('CatGruppocolore Management Update Component', () => {
        let comp: CatGruppocoloreUpdateComponent;
        let fixture: ComponentFixture<CatGruppocoloreUpdateComponent>;
        let service: CatGruppocoloreService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatGruppocoloreUpdateComponent]
            })
                .overrideTemplate(CatGruppocoloreUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CatGruppocoloreUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatGruppocoloreService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CatGruppocolore(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catGruppocolore = entity;
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
                    const entity = new CatGruppocolore();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catGruppocolore = entity;
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
