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

		this.node.name = prompt.toString();
		this.nodeService.updateNode(this.node)
			.subscribe(() => window.alert('Node name updated'));
	}

	changeNetworkName(): void {

		const prompt = window.prompt('Enter a new network name:', this.node.networkName);

		this.node.networkName = prompt.toString();
		this.nodeService.updateNode(this.node);

		this.nodeService.updateNode(this.node)
			.subscribe(() => window.alert('Network name updated'));
	}

	

}
