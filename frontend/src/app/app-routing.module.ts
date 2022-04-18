import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from "./app.component";
import {UsertravellogComponent} from "./components/user-travel-history-log/usertravellog.component";
import {TravellogformComponent} from "./components/add-travel-log/travellogform.component";
import {UpdatetravellogComponent} from "./components/update-travel-log/updatetravellog.component";

const routes: Routes = [
  { path: "", component: UsertravellogComponent, pathMatch: "full" },
  { path: "updatetravelhistory", component: UpdatetravellogComponent },
  { path: "savetravellog", component: TravellogformComponent }
];

@NgModule({
  declarations: [],
  exports: [RouterModule],
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ]
})
export class AppRoutingModule { }
