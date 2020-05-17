import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './ecommerce/login/login.component';
import { EcommerceComponent } from './ecommerce/ecommerce.component';
import { OrderhistroyComponent } from './ecommerce/orderhistroy/orderhistroy.component';



const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'ecomm', component: EcommerceComponent },
  {path : '', component : LoginComponent},
  {path : 'orderhistory', component : OrderhistroyComponent}
];

export const routing = RouterModule.forRoot(routes);

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
