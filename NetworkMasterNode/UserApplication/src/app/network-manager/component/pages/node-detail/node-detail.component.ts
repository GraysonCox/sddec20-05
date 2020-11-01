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
		let id = +this.route.snapshot.paramMap.get('id');
		this.nodeService.getNodeById(id)
			.subscribe((node: NodeModel) => this.node = node);
	}

}
