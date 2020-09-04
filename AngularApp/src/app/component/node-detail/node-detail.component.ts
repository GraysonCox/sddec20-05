import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { VideoStreamService } from 'src/app/service/video-stream/video-stream.service';
import { NodeService } from 'src/app/service/node/node.service';
import { VideoPlayerComponent } from 'src/app/component/video-player/video-player.component';
import { Node } from 'src/app/model/node';

@Component({
	selector: 'app-node-detail',
	templateUrl: './node-detail.component.html',
	styleUrls: ['./node-detail.component.css']
})
export class NodeDetailComponent implements OnInit {

	@ViewChild('videoPlayer') private videoPlayer: VideoPlayerComponent;

	node: Node;
	other: any;

	constructor(
		private route: ActivatedRoute,
		private nodeService: NodeService,
		private videoStreamService: VideoStreamService
	) {
	}

	ngOnInit(): void {
		this.fetchNode();
	}

	toggleVideoStream(): void {
		if (this.videoPlayer.getSource() == null) {
			this.fetchVideoStream();
		}
		if (this.videoPlayer.getIsPlaying()) {
			this.videoPlayer.pause();
		} else {
			this.videoPlayer.play();
		}
	}

	fetchNode(): void {
		let id = +this.route.snapshot.paramMap.get('id');
		this.nodeService.getNode(id)
			.subscribe((node: Node) => this.node = node);
	}

	fetchVideoStream(): void {
		let videoStreamId = this.node.ipAddress;
		this.videoStreamService.getVideoStream(videoStreamId)
			.subscribe((mediaStream) =>
				this.videoPlayer.setSource(mediaStream));
	}

}
