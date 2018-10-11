/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatTipoprodottoDetailComponent } from 'app/entities/cat-tipoprodotto/cat-tipoprodotto-detail.component';
import { CatTipoprodotto } from 'app/shared/model/cat-tipoprodotto.model';

describe('Component Tests', () => {
    describe('CatTipoprodotto Management Detail Component', () => {
        let comp: CatTipoprodottoDetailComponent;
        let fixture: ComponentFixture<CatTipoprodottoDetailComponent>;
        const route = ({ data: of({ catTipoprodotto: new CatTipoprodotto(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatTipoprodottoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CatTipoprodottoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatTipoprodottoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.catTipoprodotto).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
