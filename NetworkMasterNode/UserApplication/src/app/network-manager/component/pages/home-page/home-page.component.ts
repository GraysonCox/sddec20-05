import { Component, OnInit } from '@angular/core';

import { NodeModel } from 'src/app/network-manager/model/node.model';

import { NodeService } from 'src/app/network-manager/service/node/node.service';

@Component({
	selector: 'app-home-page',
	templateUrl: './home-page.component.html',
	styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

	nodes: NodeModel[];
	isLoading = false;

	constructor(private nodeService: NodeService) {
	}

	ngOnInit() {
		this.fetchNodes();
	}

	fetchNodes(): void {
		this.isLoading = true;
		this.nodeService.getAllNodes().subscribe(resultingNodes => {
			this.nodes = resultingNodes;
			this.isLoading = false;
		});
	}

}
