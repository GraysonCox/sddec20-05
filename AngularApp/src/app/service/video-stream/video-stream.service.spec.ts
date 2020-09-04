import { TestBed } from '@angular/core/testing';
import { VideoStreamService } from 'src/app/service/video-stream/video-stream.service';

describe('VideoStreamService', () => {
	let service: VideoStreamService;

	beforeEach(() => {
		TestBed.configureTestingModule({});
		service = TestBed.inject(VideoStreamService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});
});
