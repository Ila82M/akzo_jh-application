/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatFamigliaDetailComponent } from 'app/entities/cat-famiglia/cat-famiglia-detail.component';
import { CatFamiglia } from 'app/shared/model/cat-famiglia.model';

describe('Component Tests', () => {
    describe('CatFamiglia Management Detail Component', () => {
        let comp: CatFamigliaDetailComponent;
        let fixture: ComponentFixture<CatFamigliaDetailComponent>;
        const route = ({ data: of({ catFamiglia: new CatFamiglia(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatFamigliaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CatFamigliaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatFamigliaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.catFamiglia).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
