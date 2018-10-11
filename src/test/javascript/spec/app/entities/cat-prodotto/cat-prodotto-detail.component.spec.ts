/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatProdottoDetailComponent } from 'app/entities/cat-prodotto/cat-prodotto-detail.component';
import { CatProdotto } from 'app/shared/model/cat-prodotto.model';

describe('Component Tests', () => {
    describe('CatProdotto Management Detail Component', () => {
        let comp: CatProdottoDetailComponent;
        let fixture: ComponentFixture<CatProdottoDetailComponent>;
        const route = ({ data: of({ catProdotto: new CatProdotto(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatProdottoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CatProdottoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatProdottoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.catProdotto).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
