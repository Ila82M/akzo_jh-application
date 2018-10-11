/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatTipoprodottoUpdateComponent } from 'app/entities/cat-tipoprodotto/cat-tipoprodotto-update.component';
import { CatTipoprodottoService } from 'app/entities/cat-tipoprodotto/cat-tipoprodotto.service';
import { CatTipoprodotto } from 'app/shared/model/cat-tipoprodotto.model';

describe('Component Tests', () => {
    describe('CatTipoprodotto Management Update Component', () => {
        let comp: CatTipoprodottoUpdateComponent;
        let fixture: ComponentFixture<CatTipoprodottoUpdateComponent>;
        let service: CatTipoprodottoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatTipoprodottoUpdateComponent]
            })
                .overrideTemplate(CatTipoprodottoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CatTipoprodottoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatTipoprodottoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CatTipoprodotto(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catTipoprodotto = entity;
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
                    const entity = new CatTipoprodotto();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catTipoprodotto = entity;
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
