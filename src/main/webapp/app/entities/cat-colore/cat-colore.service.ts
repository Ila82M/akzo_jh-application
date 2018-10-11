import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICatColore } from 'app/shared/model/cat-colore.model';

type EntityResponseType = HttpResponse<ICatColore>;
type EntityArrayResponseType = HttpResponse<ICatColore[]>;

@Injectable({ providedIn: 'root' })
export class CatColoreService {
    private resourceUrl = SERVER_API_URL + 'api/cat-colores';

    constructor(private http: HttpClient) {}

    create(catColore: ICatColore): Observable<EntityResponseType> {
        return this.http.post<ICatColore>(this.resourceUrl, catColore, { observe: 'response' });
    }

    update(catColore: ICatColore): Observable<EntityResponseType> {
        return this.http.put<ICatColore>(this.resourceUrl, catColore, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICatColore>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICatColore[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
