import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Users} from "../models/userlist";

@Injectable({
  providedIn: 'root'
})
export class UsertravellogService {

  constructor(private http: HttpClient) { }

  getAllUsers() {
    return this.http.get<Users[]>(environment.url+"/api/users");
  }
}
