/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatColorebaseDetailComponent } from 'app/entities/cat-colorebase/cat-colorebase-detail.component';
import { CatColorebase } from 'app/shared/model/cat-colorebase.model';

describe('Component Tests', () => {
    describe('CatColorebase Management Detail Component', () => {
        let comp: CatColorebaseDetailComponent;
        let fixture: ComponentFixture<CatColorebaseDetailComponent>;
        const route = ({ data: of({ catColorebase: new CatColorebase(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatColorebaseDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CatColorebaseDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatColorebaseDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.catColorebase).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
