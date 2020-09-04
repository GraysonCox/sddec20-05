import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from 'src/app/app-routing.module';
import { AppComponent } from 'src/app/app.component';
import { NodeDetailComponent } from 'src/app/component/node-detail/node-detail.component';
import { HomePageComponent } from 'src/app/component/home-page/home-page.component';
import { VideoPlayerComponent } from 'src/app/component/video-player/video-player.component';

@NgModule({
	declarations: [
		AppComponent,
		HomePageComponent,
		NodeDetailComponent,
		VideoPlayerComponent
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		HttpClientModule
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule {
}
