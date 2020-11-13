import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NodeModel } from 'src/app/network-manager/model/node.model';
import { NodeService } from 'src/app/network-manager/service/node/node.service';

@Component({
	selector: 'app-node-detail',
	templateUrl: './node-detail.component.html',
	styleUrls: ['./node-detail.component.css']
})
export class NodeDetailComponent implements OnInit {

	node: NodeModel;
	other: any;

	constructor(
		private route: ActivatedRoute,
		private nodeService: NodeService
	) {
	}

	ngOnInit(): void {
		this.fetchNode();
	}

	fetchNode(): void {
		const ipAddress = this.route.snapshot.paramMap.get('ipAddress');
		this.nodeService.getNodeByIpAddress(ipAddress)
			.subscribe((node: NodeModel) => this.node = node);
	}

	changeName(): void {
		const prompt = window.prompt('Enter a new node name:', this.node.name);

		if (prompt != null) {
			this.node.name = prompt;
			this.nodeService.updateNode(this.node.ipAddress, this.node)
				.subscribe(
					() => window.alert('Node name updated.'),
					() => window.alert('Could not update node name.')
				);
		}
	}

	changeNetworkName(): void {
		const prompt = window.prompt('Enter a new network name:', this.node.networkName);

		if (prompt != null) {
			this.node.networkName = prompt;
			this.nodeService.updateNode(this.node.ipAddress, this.node)
				.subscribe(
					() => window.alert('Network name updated.'),
					() => window.alert('Could not update network name.')
				);
		}
	}

}
