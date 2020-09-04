import { Component, AfterViewInit, ViewChild } from '@angular/core';

@Component({
	selector: 'app-video-player',
	templateUrl: './video-player.component.html',
	styleUrls: ['./video-player.component.css']
})
export class VideoPlayerComponent implements AfterViewInit {

	@ViewChild('video') private video: any;

	private isPlaying: boolean = false;

	constructor() {
	}

	ngAfterViewInit(): void {
		let _video = this.video.nativeElement;
		_video.onplay = (event) => {
			this.isPlaying = true;
		};
		_video.onpause = (event) => {
			this.isPlaying = false;
		};
	}

	play(): void {
		this.video.nativeElement.play();
	}

	pause(): void {
		this.video.nativeElement.pause();
	}

	setSource(source: any): void {
		this.video.nativeElement.srcObject = source;
	}

	getSource(): any {
		return this.video.nativeElement.srcObject;
	}

	getIsPlaying(): boolean {
		return this.isPlaying;
	}

}
