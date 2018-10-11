import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICatFamiglia } from 'app/shared/model/cat-famiglia.model';

type EntityResponseType = HttpResponse<ICatFamiglia>;
type EntityArrayResponseType = HttpResponse<ICatFamiglia[]>;

@Injectable({ providedIn: 'root' })
export class CatFamigliaService {
    private resourceUrl = SERVER_API_URL + 'api/cat-famiglias';

    constructor(private http: HttpClient) {}

    create(catFamiglia: ICatFamiglia): Observable<EntityResponseType> {
        return this.http.post<ICatFamiglia>(this.resourceUrl, catFamiglia, { observe: 'response' });
    }

    update(catFamiglia: ICatFamiglia): Observable<EntityResponseType> {
        return this.http.put<ICatFamiglia>(this.resourceUrl, catFamiglia, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICatFamiglia>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICatFamiglia[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
