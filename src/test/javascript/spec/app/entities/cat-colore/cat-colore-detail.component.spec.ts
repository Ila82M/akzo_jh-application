/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatColoreDetailComponent } from 'app/entities/cat-colore/cat-colore-detail.component';
import { CatColore } from 'app/shared/model/cat-colore.model';

describe('Component Tests', () => {
    describe('CatColore Management Detail Component', () => {
        let comp: CatColoreDetailComponent;
        let fixture: ComponentFixture<CatColoreDetailComponent>;
        const route = ({ data: of({ catColore: new CatColore(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatColoreDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CatColoreDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatColoreDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.catColore).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
