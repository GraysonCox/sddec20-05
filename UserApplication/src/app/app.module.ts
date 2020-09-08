import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from 'src/app/app-routing.module';
import { AppComponent } from 'src/app/app.component';
import { NetworkManagerModule } from 'src/app/network-manager/network-manager.module';

@NgModule({
	declarations: [
		AppComponent
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		HttpClientModule,
		NetworkManagerModule
	],
	providers: [],
	bootstrap: [
		AppComponent
	]
})
export class AppModule {
}
