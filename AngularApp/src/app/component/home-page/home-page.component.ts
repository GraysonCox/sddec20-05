import { Component, OnInit } from '@angular/core';

import { Observable } from 'rxjs';

import { NodeService } from 'src/app/service/node/node.service';
import { Node } from 'src/app/model/node';

@Component({
	selector: 'app-home-page',
	templateUrl: './home-page.component.html',
	styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

	nodes: Observable<Node[]>;
	$i: number;

	constructor(private nodeService: NodeService) {
	}

	ngOnInit() {
		this.fetchNodes();
	}

	fetchNodes(): void {
		this.nodes = this.nodeService.getNodes();
	}

}
