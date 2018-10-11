/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { ComuneDetailComponent } from 'app/entities/comune/comune-detail.component';
import { Comune } from 'app/shared/model/comune.model';

describe('Component Tests', () => {
    describe('Comune Management Detail Component', () => {
        let comp: ComuneDetailComponent;
        let fixture: ComponentFixture<ComuneDetailComponent>;
        const route = ({ data: of({ comune: new Comune(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [ComuneDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ComuneDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ComuneDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.comune).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
