import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from 'src/app/network-manager/component/pages/home-page/home-page.component';
import { NodeDetailComponent } from 'src/app/network-manager/component/pages/node-detail/node-detail.component';

const routes: Routes = [
	{ path: '', redirectTo: '/home', pathMatch: 'full' },
	{ path: 'home', component: HomePageComponent },
	{ path: 'node/:ipAddress', component: NodeDetailComponent }
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class NetworkManagerRoutingModule {
}
