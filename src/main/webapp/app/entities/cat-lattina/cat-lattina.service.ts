import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICatLattina } from 'app/shared/model/cat-lattina.model';

type EntityResponseType = HttpResponse<ICatLattina>;
type EntityArrayResponseType = HttpResponse<ICatLattina[]>;

@Injectable({ providedIn: 'root' })
export class CatLattinaService {
    private resourceUrl = SERVER_API_URL + 'api/cat-lattinas';

    constructor(private http: HttpClient) {}

    create(catLattina: ICatLattina): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(catLattina);
        return this.http
            .post<ICatLattina>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(catLattina: ICatLattina): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(catLattina);
        return this.http
            .put<ICatLattina>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICatLattina>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICatLattina[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(catLattina: ICatLattina): ICatLattina {
        const copy: ICatLattina = Object.assign({}, catLattina, {
            dataUpdate: catLattina.dataUpdate != null && catLattina.dataUpdate.isValid() ? catLattina.dataUpdate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dataUpdate = res.body.dataUpdate != null ? moment(res.body.dataUpdate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((catLattina: ICatLattina) => {
            catLattina.dataUpdate = catLattina.dataUpdate != null ? moment(catLattina.dataUpdate) : null;
        });
        return res;
    }
}
