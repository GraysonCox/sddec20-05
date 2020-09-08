import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { VideoPlayerComponent } from 'src/app/network-manager/component/common/video-player/video-player.component';
import { HomePageComponent } from 'src/app/network-manager/component/pages/home-page/home-page.component';
import { NodeDetailComponent } from 'src/app/network-manager/component/pages/node-detail/node-detail.component';

import { NetworkManagerRoutingModule } from 'src/app/network-manager/network-manager-routing.module';

@NgModule({
	declarations: [
		HomePageComponent,
		NodeDetailComponent,
		VideoPlayerComponent
	],
	imports: [
		NetworkManagerRoutingModule,
		CommonModule
	],
	providers: []
})
export class NetworkManagerModule {
}
