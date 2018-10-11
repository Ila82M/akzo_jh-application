import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICatGruppocolore } from 'app/shared/model/cat-gruppocolore.model';

type EntityResponseType = HttpResponse<ICatGruppocolore>;
type EntityArrayResponseType = HttpResponse<ICatGruppocolore[]>;

@Injectable({ providedIn: 'root' })
export class CatGruppocoloreService {
    private resourceUrl = SERVER_API_URL + 'api/cat-gruppocolores';

    constructor(private http: HttpClient) {}

    create(catGruppocolore: ICatGruppocolore): Observable<EntityResponseType> {
        return this.http.post<ICatGruppocolore>(this.resourceUrl, catGruppocolore, { observe: 'response' });
    }

    update(catGruppocolore: ICatGruppocolore): Observable<EntityResponseType> {
        return this.http.put<ICatGruppocolore>(this.resourceUrl, catGruppocolore, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICatGruppocolore>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICatGruppocolore[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
