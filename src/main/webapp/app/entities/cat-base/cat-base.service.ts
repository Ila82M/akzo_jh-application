import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICatBase } from 'app/shared/model/cat-base.model';

type EntityResponseType = HttpResponse<ICatBase>;
type EntityArrayResponseType = HttpResponse<ICatBase[]>;

@Injectable({ providedIn: 'root' })
export class CatBaseService {
    private resourceUrl = SERVER_API_URL + 'api/cat-bases';

    constructor(private http: HttpClient) {}

    create(catBase: ICatBase): Observable<EntityResponseType> {
        return this.http.post<ICatBase>(this.resourceUrl, catBase, { observe: 'response' });
    }

    update(catBase: ICatBase): Observable<EntityResponseType> {
        return this.http.put<ICatBase>(this.resourceUrl, catBase, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICatBase>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICatBase[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
