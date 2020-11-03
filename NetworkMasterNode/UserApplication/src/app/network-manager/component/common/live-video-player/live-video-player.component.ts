import { AfterViewInit, Component, Input, ViewChild } from '@angular/core';

declare var Janus: any;

/**
 * A video component that shows the live video stream for a given stream URL.
 */
@Component({
	selector: 'app-live-video-player',
	templateUrl: './live-video-player.component.html',
	styleUrls: ['./live-video-player.component.css']
})
export class LiveVideoPlayerComponent implements AfterViewInit {

	private static JANUS_PLUGIN_NAME = 'janus.plugin.streaming';
	private static REQUEST_WATCH = 'watch';
	private static REQUEST_START = 'start';

	@ViewChild('video') private video: any;

	@Input() streamUrl: string;

	constructor() {
	}

	ngAfterViewInit(): void {
		this.startVideoStream(this.streamUrl);
	}

	/**
	 * Starts displaying the live video feed from the Janus server with the given URL.
	 *
	 * @param streamUrl The URL of the Janus server.
	 * @private
	 */
	private startVideoStream(streamUrl: string): void {
		let streaming = null;
		const videoElement = this.video.nativeElement;
		Janus.init({
			debug: true,
			dependencies: Janus.useDefaultDependencies(),
			callback() {
				console.log('Setting up Janus.');
				const janus = new Janus({
					server: streamUrl,
					success() {
						console.log('Connected to server.');
						janus.attach({
							plugin: LiveVideoPlayerComponent.JANUS_PLUGIN_NAME,
							success(pluginHandle) {
								streaming = pluginHandle;
								console.log('Plugin attached.');
								const body = { request: LiveVideoPlayerComponent.REQUEST_WATCH };
								streaming.send({ message: body });
							},
							error(cause) {
								console.log('Couldn\'t attach to the plugin.', cause);
							},
							consentDialog(on) {
								console.log('Maybe a consent dialog should be shown here.', on);
							},
							onmessage(msg, jsep) {
								console.log('Message received.');
								if (jsep !== undefined && jsep !== null && streaming != null) {
									streaming.createAnswer({
										jsep,
										media: { audioSend: false, videoSend: false },
										success(ourjsep) {
											const body = { request: LiveVideoPlayerComponent.REQUEST_START };
											streaming.send({ message: body, jsep: ourjsep });
											console.log('Sent request to start streaming.');
										},
										error(error) {
											console.log(error);
										}
									});
								}
							},
							onlocalstream(stream) {
								console.log('Received local video stream.');
								Janus.attachMediaStream(videoElement, stream);
							},
							onremotestream(stream) {
								console.log('Received remote video stream.');
								Janus.attachMediaStream(videoElement, stream);
								const videoTracks = stream.getVideoTracks();
								if (!videoTracks || videoTracks.length === 0) {
									console.log('No remote video.');
								} else {
									console.log('Video stream started successfully.');
								}
							},
							oncleanup() {
								console.log('Clean up.');
							},
							detached() {
								console.log('Janus connection detached.');
							}
						});
					},
					error(cause) {
						console.log(cause);
					},
					destroyed() {
						console.log('Janus connection destroyed.');
					}
				});
			}
		});
	}

}
