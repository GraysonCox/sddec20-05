import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LiveVideoPlayerComponent } from './live-video-player.component';

describe('LiveVideoPlayerComponent', () => {
	let component: LiveVideoPlayerComponent;
	let fixture: ComponentFixture<LiveVideoPlayerComponent>;

	beforeEach(async(() => {
		TestBed.configureTestingModule({
			declarations: [LiveVideoPlayerComponent]
		}).compileComponents();
	}));

	beforeEach(() => {
		fixture = TestBed.createComponent(LiveVideoPlayerComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
