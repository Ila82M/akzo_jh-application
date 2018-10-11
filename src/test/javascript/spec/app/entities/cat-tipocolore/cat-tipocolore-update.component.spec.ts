/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatTipocoloreUpdateComponent } from 'app/entities/cat-tipocolore/cat-tipocolore-update.component';
import { CatTipocoloreService } from 'app/entities/cat-tipocolore/cat-tipocolore.service';
import { CatTipocolore } from 'app/shared/model/cat-tipocolore.model';

describe('Component Tests', () => {
    describe('CatTipocolore Management Update Component', () => {
        let comp: CatTipocoloreUpdateComponent;
        let fixture: ComponentFixture<CatTipocoloreUpdateComponent>;
        let service: CatTipocoloreService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatTipocoloreUpdateComponent]
            })
                .overrideTemplate(CatTipocoloreUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CatTipocoloreUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatTipocoloreService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CatTipocolore(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catTipocolore = entity;
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
                    const entity = new CatTipocolore();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catTipocolore = entity;
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
