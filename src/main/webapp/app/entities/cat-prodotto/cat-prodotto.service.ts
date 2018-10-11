import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICatProdotto } from 'app/shared/model/cat-prodotto.model';

type EntityResponseType = HttpResponse<ICatProdotto>;
type EntityArrayResponseType = HttpResponse<ICatProdotto[]>;

@Injectable({ providedIn: 'root' })
export class CatProdottoService {
    private resourceUrl = SERVER_API_URL + 'api/cat-prodottos';

    constructor(private http: HttpClient) {}

    create(catProdotto: ICatProdotto): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(catProdotto);
        return this.http
            .post<ICatProdotto>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(catProdotto: ICatProdotto): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(catProdotto);
        return this.http
            .put<ICatProdotto>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICatProdotto>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICatProdotto[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(catProdotto: ICatProdotto): ICatProdotto {
        const copy: ICatProdotto = Object.assign({}, catProdotto, {
            dataUpdate: catProdotto.dataUpdate != null && catProdotto.dataUpdate.isValid() ? catProdotto.dataUpdate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dataUpdate = res.body.dataUpdate != null ? moment(res.body.dataUpdate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((catProdotto: ICatProdotto) => {
            catProdotto.dataUpdate = catProdotto.dataUpdate != null ? moment(catProdotto.dataUpdate) : null;
        });
        return res;
    }
}
