import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICatTipocolore } from 'app/shared/model/cat-tipocolore.model';

type EntityResponseType = HttpResponse<ICatTipocolore>;
type EntityArrayResponseType = HttpResponse<ICatTipocolore[]>;

@Injectable({ providedIn: 'root' })
export class CatTipocoloreService {
    private resourceUrl = SERVER_API_URL + 'api/cat-tipocolores';

    constructor(private http: HttpClient) {}

    create(catTipocolore: ICatTipocolore): Observable<EntityResponseType> {
        return this.http.post<ICatTipocolore>(this.resourceUrl, catTipocolore, { observe: 'response' });
    }

    update(catTipocolore: ICatTipocolore): Observable<EntityResponseType> {
        return this.http.put<ICatTipocolore>(this.resourceUrl, catTipocolore, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICatTipocolore>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICatTipocolore[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
