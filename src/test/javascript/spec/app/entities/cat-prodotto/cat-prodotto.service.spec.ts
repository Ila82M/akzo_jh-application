/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CatProdottoService } from 'app/entities/cat-prodotto/cat-prodotto.service';
import { ICatProdotto, CatProdotto } from 'app/shared/model/cat-prodotto.model';

describe('Service Tests', () => {
    describe('CatProdotto Service', () => {
        let injector: TestBed;
        let service: CatProdottoService;
        let httpMock: HttpTestingController;
        let elemDefault: ICatProdotto;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(CatProdottoService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new CatProdotto(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dataUpdate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a CatProdotto', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dataUpdate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataUpdate: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new CatProdotto(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a CatProdotto', async () => {
                const returnedFromService = Object.assign(
                    {
                        nome: 'BBBBBB',
                        descrizione: 'BBBBBB',
                        sottotipo: 'BBBBBB',
                        note: 'BBBBBB',
                        schedaTecnica: 'BBBBBB',
                        schedaSicurezza: 'BBBBBB',
                        img: 'BBBBBB',
                        dataUpdate: currentDate.format(DATE_TIME_FORMAT),
                        pubblicato: 'BBBBBB',
                        misura: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dataUpdate: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of CatProdotto', async () => {
                const returnedFromService = Object.assign(
                    {
                        nome: 'BBBBBB',
                        descrizione: 'BBBBBB',
                        sottotipo: 'BBBBBB',
                        note: 'BBBBBB',
                        schedaTecnica: 'BBBBBB',
                        schedaSicurezza: 'BBBBBB',
                        img: 'BBBBBB',
                        dataUpdate: currentDate.format(DATE_TIME_FORMAT),
                        pubblicato: 'BBBBBB',
                        misura: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataUpdate: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a CatProdotto', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
