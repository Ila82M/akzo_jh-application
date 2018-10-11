/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatGruppocoloreDetailComponent } from 'app/entities/cat-gruppocolore/cat-gruppocolore-detail.component';
import { CatGruppocolore } from 'app/shared/model/cat-gruppocolore.model';

describe('Component Tests', () => {
    describe('CatGruppocolore Management Detail Component', () => {
        let comp: CatGruppocoloreDetailComponent;
        let fixture: ComponentFixture<CatGruppocoloreDetailComponent>;
        const route = ({ data: of({ catGruppocolore: new CatGruppocolore(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatGruppocoloreDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CatGruppocoloreDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatGruppocoloreDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.catGruppocolore).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
