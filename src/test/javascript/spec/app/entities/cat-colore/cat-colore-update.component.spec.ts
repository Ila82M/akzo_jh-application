/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatColoreUpdateComponent } from 'app/entities/cat-colore/cat-colore-update.component';
import { CatColoreService } from 'app/entities/cat-colore/cat-colore.service';
import { CatColore } from 'app/shared/model/cat-colore.model';

describe('Component Tests', () => {
    describe('CatColore Management Update Component', () => {
        let comp: CatColoreUpdateComponent;
        let fixture: ComponentFixture<CatColoreUpdateComponent>;
        let service: CatColoreService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatColoreUpdateComponent]
            })
                .overrideTemplate(CatColoreUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CatColoreUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatColoreService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CatColore(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catColore = entity;
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
                    const entity = new CatColore();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catColore = entity;
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
