/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatProdottoUpdateComponent } from 'app/entities/cat-prodotto/cat-prodotto-update.component';
import { CatProdottoService } from 'app/entities/cat-prodotto/cat-prodotto.service';
import { CatProdotto } from 'app/shared/model/cat-prodotto.model';

describe('Component Tests', () => {
    describe('CatProdotto Management Update Component', () => {
        let comp: CatProdottoUpdateComponent;
        let fixture: ComponentFixture<CatProdottoUpdateComponent>;
        let service: CatProdottoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatProdottoUpdateComponent]
            })
                .overrideTemplate(CatProdottoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CatProdottoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatProdottoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CatProdotto(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catProdotto = entity;
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
                    const entity = new CatProdotto();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catProdotto = entity;
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
