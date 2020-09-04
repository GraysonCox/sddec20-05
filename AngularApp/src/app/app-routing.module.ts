import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NodeDetailComponent } from 'src/app/component/node-detail/node-detail.component';
import { HomePageComponent } from 'src/app/component/home-page/home-page.component';

const routes: Routes = [
	{path: '', redirectTo: '/home', pathMatch: 'full'},
	{path: 'home', component: HomePageComponent},
	{path: 'node/:id', component: NodeDetailComponent},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
