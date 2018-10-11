/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatTipocoloreDetailComponent } from 'app/entities/cat-tipocolore/cat-tipocolore-detail.component';
import { CatTipocolore } from 'app/shared/model/cat-tipocolore.model';

describe('Component Tests', () => {
    describe('CatTipocolore Management Detail Component', () => {
        let comp: CatTipocoloreDetailComponent;
        let fixture: ComponentFixture<CatTipocoloreDetailComponent>;
        const route = ({ data: of({ catTipocolore: new CatTipocolore(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatTipocoloreDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CatTipocoloreDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatTipocoloreDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.catTipocolore).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
