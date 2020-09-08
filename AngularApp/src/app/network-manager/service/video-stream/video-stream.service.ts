import { Injectable } from '@angular/core';

import { from, Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class VideoStreamService {

	constructor() {
	}

	/**
	 *	Starts capturing video from the web cam and returns its MediaStream
	 *	object. This will eventually be changed to get video from a remote
	 *	source.
	 *
	 *	@param streamId	The ID of the stream to fetch (when remote fetching is
	 *					implemented).
	 *	@returns The MediaStream object associated with the fetched stream.
	 */
	getVideoStream(streamId: string): Observable<MediaStream> {
		return from(navigator.mediaDevices.getUserMedia({
			audio: false,
			video: {
				width: {
					min: 1280
				},
				height: {
					min: 720
				}
			}
		}));
	}

}
