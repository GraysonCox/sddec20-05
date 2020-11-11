import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { NodeModel } from 'src/app/network-manager/model/node.model';

@Injectable({
	providedIn: 'root'
})
export class NodeService {

	constructor(private http: HttpClient) {
	}

	private nodesUrl = '/api/node';

	/**
	 * Log a NodeService message.
	 */
	private static log(message: string) {
		// TODO: Make a logger class.
		console.log(`NodeService: ${ message }`);
	}

	/**
	 * Returns a list containing all the active nodes in the network.
	 */
	getAllNodes(): Observable<NodeModel[]> {
		const url = `${ this.nodesUrl }/all`;
		return this.http.get<NodeModel[]>(url)
			.pipe(
				tap(_ => NodeService.log('fetched nodes')),
				catchError(this.handleError<NodeModel[]>('getNodes', []))
			);
	}

	/**
	 * Returns the node with the given IPv4 address, or null if the node does not exist.
	 * @param ipAddress An IPv4 address.
	 */
	getNodeByIpAddress(ipAddress: string): Observable<NodeModel> {
		const url = `${ this.nodesUrl }/${ ipAddress }`;
		return this.http.get<NodeModel>(url)
			.pipe(
				tap(_ => NodeService.log(`fetched node IP=${ ipAddress }`)),
				catchError(this.handleError<NodeModel>(`getNode IP=${ ipAddress }`))
			);
	}

	/**
	 * Matches the given node with an existing node by its IP address and updates the existing node with the properties
	 * of the given node.
	 * @param node The node to persist.
	 */
	updateNode(node: NodeModel): Observable<void> {
		const url = `${ this.nodesUrl }/update`;
		return this.http.post<void>(url, node)
			.pipe(
				tap(_ => NodeService.log(`updated node IP=${ node.ipAddress }`)),
				catchError(this.handleError<void>(`updateNode IP=${ node.ipAddress }`))
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
			NodeService.log(`${ operation } failed: ${ error.message }`);

			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
	}

}
