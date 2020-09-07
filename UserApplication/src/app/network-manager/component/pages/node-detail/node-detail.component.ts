import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VideoPlayerComponent } from 'src/app/network-manager/component/common/video-player/video-player.component';
import { NodeModel } from 'src/app/network-manager/model/node.model';
import { NodeService } from 'src/app/network-manager/service/node/node.service';

import { VideoStreamService } from 'src/app/network-manager/service/video-stream/video-stream.service';

@Component({
	selector: 'app-node-detail',
	templateUrl: './node-detail.component.html',
	styleUrls: ['./node-detail.component.css']
})
export class NodeDetailComponent implements OnInit {

	node: NodeModel;
	other: any;
	@ViewChild('videoPlayer') private videoPlayer: VideoPlayerComponent;

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
		this.nodeService.getNodeById(id)
			.subscribe((node: NodeModel) => this.node = node);
	}

	fetchVideoStream(): void {
		let videoStreamId = this.node.ipAddress;
		this.videoStreamService.getVideoStream(videoStreamId)
			.subscribe((mediaStream) =>
				this.videoPlayer.setSource(mediaStream));
	}

}
