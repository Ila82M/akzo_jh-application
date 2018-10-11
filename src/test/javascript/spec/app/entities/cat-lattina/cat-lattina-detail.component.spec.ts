/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AkzoJhApplicationTestModule } from '../../../test.module';
import { CatLattinaDetailComponent } from 'app/entities/cat-lattina/cat-lattina-detail.component';
import { CatLattina } from 'app/shared/model/cat-lattina.model';

describe('Component Tests', () => {
    describe('CatLattina Management Detail Component', () => {
        let comp: CatLattinaDetailComponent;
        let fixture: ComponentFixture<CatLattinaDetailComponent>;
        const route = ({ data: of({ catLattina: new CatLattina(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AkzoJhApplicationTestModule],
                declarations: [CatLattinaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CatLattinaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CatLattinaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.catLattina).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
