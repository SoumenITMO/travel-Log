import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { User } from "../models/user";
import { environment } from "../../environments/environment";
import { Travellog } from "../models/travellog";
import { UserLogSearchCriteria } from "../models/userlogsearchcriteria";

@Injectable({
  providedIn: 'root'
})

export class TravelhistoryService {

  constructor(private http: HttpClient) { }

  getUserTravelLogData() {
    return this.http.get<User[]>(environment.url+"/api/travel-logs");
  }

  deleteTravelLogData(travellogId: number) {
    return this.http.delete(environment.url+"/api/delete-travel-log?travellog=" + travellogId);
  }

  saveUserTravelLogData(travellog: Partial<Travellog>) {
    return this.http.post(environment.url+"/api/add-travel-log", travellog);
  }

  getTravelHistoryById(travelHistoryId: number) {
    return this.http.get<Travellog>(environment.url+"/api/single-travel-log?historyid=" + travelHistoryId);
  }

  updateTravelLogHistory(updatedTravelLogData: Partial<Travellog>) {
    return this.http.put<Travellog>(environment.url+"/api/edit-travel-log", updatedTravelLogData);
  }

  searchTravelHistory(searchCriteria: Partial<UserLogSearchCriteria>) {
    return this.http.post<User[]>(environment.url+"/api/travel-log-search", searchCriteria);
  }
}
