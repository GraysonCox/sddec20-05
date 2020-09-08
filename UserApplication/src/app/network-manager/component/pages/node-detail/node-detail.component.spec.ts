import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NodeDetailComponent } from 'src/app/network-manager/component/pages/node-detail/node-detail.component';

describe('NodeDetailComponent', () => {
	let component: NodeDetailComponent;
	let fixture: ComponentFixture<NodeDetailComponent>;

	beforeEach(async(() => {
		TestBed.configureTestingModule({
			declarations: [NodeDetailComponent]
		})
			.compileComponents();
	}));

	beforeEach(() => {
		fixture = TestBed.createComponent(NodeDetailComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
