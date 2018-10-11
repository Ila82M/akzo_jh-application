/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatLattinaUpdateComponent } from 'app/entities/cat-lattina/cat-lattina-update.component';
import { CatLattinaService } from 'app/entities/cat-lattina/cat-lattina.service';
import { CatLattina } from 'app/shared/model/cat-lattina.model';

describe('Component Tests', () => {
    describe('CatLattina Management Update Component', () => {
        let comp: CatLattinaUpdateComponent;
        let fixture: ComponentFixture<CatLattinaUpdateComponent>;
        let service: CatLattinaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatLattinaUpdateComponent]
            })
                .overrideTemplate(CatLattinaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CatLattinaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CatLattinaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CatLattina(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catLattina = entity;
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
                    const entity = new CatLattina();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.catLattina = entity;
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
