/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatBaseDetailComponent } from 'app/entities/cat-base/cat-base-detail.component';
import { CatBase } from 'app/shared/model/cat-base.model';

describe('Component Tests', () => {
    describe('CatBase Management Detail Component', () => {
        let comp: CatBaseDetailComponent;
        let fixture: ComponentFixture<CatBaseDetailComponent>;
        const route = ({ data: of({ catBase: new CatBase(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatBaseDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CatBaseDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatBaseDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.catBase).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
