import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IComune } from 'app/shared/model/comune.model';

type EntityResponseType = HttpResponse<IComune>;
type EntityArrayResponseType = HttpResponse<IComune[]>;

@Injectable({ providedIn: 'root' })
export class ComuneService {
    private resourceUrl = SERVER_API_URL + 'api/comunes';

    constructor(private http: HttpClient) {}

    create(comune: IComune): Observable<EntityResponseType> {
        return this.http.post<IComune>(this.resourceUrl, comune, { observe: 'response' });
    }

    update(comune: IComune): Observable<EntityResponseType> {
        return this.http.put<IComune>(this.resourceUrl, comune, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IComune>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IComune[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
