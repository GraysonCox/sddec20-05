import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { Node } from 'src/app/model/node';


@Injectable({
	providedIn: 'root'
})
export class NodeService {

	private nodesUrl: string = 'api/node';

	constructor(private http: HttpClient) {
	}

	getNodes(): Observable<Node[]> {
		return this.http.get<Node[]>(this.nodesUrl)
			.pipe(
				tap(_ => this.log('fetched nodes')),
				catchError(this.handleError<Node[]>('getNodes', []))
			);
	}

	getNode(id: number): Observable<Node> {
		let url = `${this.nodesUrl}/${id}`;
		return this.http.get<Node>(url)
			.pipe(
				tap(_ => this.log(`fetched node id=${id}`)),
				catchError(this.handleError<Node>(`getNode id=${id}`))
			);
	}

	/**
	 * Handle Http operation that failed.
	 * Let the app continue.
	 * @param operation - name of the operation that failed
	 * @param result - optional value to return as the observable result
	 */
	private handleError<T>(operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			// TODO: send the error to remote logging infrastructure
			console.error(error); // log to console instead

			// TODO: better job of transforming error for user consumption
			this.log(`${operation} failed: ${error.message}`);

			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
	}

	/**
	 * Log a NodeService message.
	 */
	private log(message: string) {
		console.log(`NodeService: ${message}`);
	}

}
