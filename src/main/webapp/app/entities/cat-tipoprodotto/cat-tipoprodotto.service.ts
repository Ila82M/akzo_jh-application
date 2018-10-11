import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICatTipoprodotto } from 'app/shared/model/cat-tipoprodotto.model';

type EntityResponseType = HttpResponse<ICatTipoprodotto>;
type EntityArrayResponseType = HttpResponse<ICatTipoprodotto[]>;

@Injectable({ providedIn: 'root' })
export class CatTipoprodottoService {
    private resourceUrl = SERVER_API_URL + 'api/cat-tipoprodottos';

    constructor(private http: HttpClient) {}

    create(catTipoprodotto: ICatTipoprodotto): Observable<EntityResponseType> {
        return this.http.post<ICatTipoprodotto>(this.resourceUrl, catTipoprodotto, { observe: 'response' });
    }

    update(catTipoprodotto: ICatTipoprodotto): Observable<EntityResponseType> {
        return this.http.put<ICatTipoprodotto>(this.resourceUrl, catTipoprodotto, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICatTipoprodotto>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICatTipoprodotto[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
