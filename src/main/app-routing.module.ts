import { NgModule } from "@angular/core";
import { LoginComponent } from "./auth/login/login.component";
import { RegisterComponent } from "./auth/register/register.component";
import { HomeComponent } from "./user/home/home.component";
import { RouterModule, RouterOutlet, Routes } from "@angular/router";

const routes: Routes = [
    {path: 'login', component:LoginComponent},
    {path: 'register', component:RegisterComponent},
    {path: 'home',component:HomeComponent},
    {path: '**', redirectTo: '/login'},
    {path: '', pathMatch:'full',redirectTo: '/login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
