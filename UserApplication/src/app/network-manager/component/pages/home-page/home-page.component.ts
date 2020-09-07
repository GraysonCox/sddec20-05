import { Component, OnInit } from '@angular/core';

import { Observable } from 'rxjs';
import { NodeModel } from 'src/app/network-manager/model/node.model';

import { NodeService } from 'src/app/network-manager/service/node/node.service';

@Component({
	selector: 'app-home-page',
	templateUrl: './home-page.component.html',
	styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

	nodes: Observable<NodeModel[]>;
	$i: number;

	constructor(private nodeService: NodeService) {
	}

	ngOnInit() {
		this.fetchNodes();
	}

	fetchNodes(): void {
		this.nodes = this.nodeService.getAllNodes();
	}

}
