import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRegione } from 'app/shared/model/regione.model';

type EntityResponseType = HttpResponse<IRegione>;
type EntityArrayResponseType = HttpResponse<IRegione[]>;

@Injectable({ providedIn: 'root' })
export class RegioneService {
    private resourceUrl = SERVER_API_URL + 'api/regiones';

    constructor(private http: HttpClient) {}

    create(regione: IRegione): Observable<EntityResponseType> {
        return this.http.post<IRegione>(this.resourceUrl, regione, { observe: 'response' });
    }

    update(regione: IRegione): Observable<EntityResponseType> {
        return this.http.put<IRegione>(this.resourceUrl, regione, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRegione>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRegione[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
