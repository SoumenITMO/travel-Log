import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import {RouterModule} from "@angular/router";
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UsertravellogComponent } from './components/user-travel-history-log/usertravellog.component';
import { AppRoutingModule } from './app-routing.module';
import { TravellogformComponent } from './components/add-travel-log/travellogform.component';
import { DatePipe } from '@angular/common';
import { UpdatetravellogComponent } from './components/update-travel-log/updatetravellog.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ToastrModule} from "ngx-toastr";

@NgModule({
  declarations: [
    AppComponent,
    UsertravellogComponent,
    TravellogformComponent,
    UpdatetravellogComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    ToastrModule.forRoot()
  ],
  providers: [RouterModule, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
