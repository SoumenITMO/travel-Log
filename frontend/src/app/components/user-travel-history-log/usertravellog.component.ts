import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UsertravellogService } from "../../services/usertravellog.service";
import { UserLogSearchCriteria } from "../../models/userlogsearchcriteria";
import { Router } from "@angular/router";
import { TravelhistoryService } from "../../services/travelhistory.service";
import { FormBuilder } from "@angular/forms";
import { DatePipe } from "@angular/common";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: 'app-usertravellog',
  templateUrl: './usertravellog.component.html',
  styleUrls: ['./usertravellog.component.scss']
})
export class UsertravellogComponent implements OnInit {

  users: User[] = <User[]>[];
  showTravelHistoryInfo:boolean = true;
  searchCritera: Partial<UserLogSearchCriteria> = {};

  constructor(private toaster: ToastrService,
              private router: Router,
              private datePipe: DatePipe,
              private formBuilder: FormBuilder,
              private usertravellog: UsertravellogService,
              private travelHistoryService: TravelhistoryService) { }

  ngOnInit(): void {
    this.loadPartialData();
  }

  searchLogData = this.formBuilder.group({
    owner_name: '',
    vehicle_reg_number: '',
    date_from: '',
    date_to: ''
  });

  loadPartialData() {
    this.travelHistoryService.getUserTravelLogData().toPromise().then((data: User[]) => {
      this.users = data;
      if(data.length <= 0) {
        this.showTravelHistoryInfo = false;
      } else {
        this.showTravelHistoryInfo =  true;
      }
    });
  }

  updateUserTravelLogData(travelLogId: number) {
    this.router.navigate(["updatetravelhistory"], { queryParams: { travelhistoryid: travelLogId }});
  }

  deleteTravelLogDate(travelLogId: number) {
    this.travelHistoryService.deleteTravelLogData(travelLogId).subscribe(() => {
      this.users = [];
      this.loadPartialData();
    }, error => {
      this.toaster.error(error.error._embedded.errors[0].message);
    });

  }

  updateTravelLog(): void {
    this.router.navigate(["savetravellog"]);
  }

  resetSearch(): void {
    this.clearSearchForm();
    this.loadPartialData();
  }

  searchUserLogs() {
    if((this.searchLogData.value.owner_name != null || this.searchLogData.value.vehicle_reg_number != null) ||
      (this.datePipe.transform(this.searchLogData.value.date_from, "dd.MM.yyyy") !=null &&
        this.datePipe.transform(this.searchLogData.value.date_to, "dd.MM.yyyy") != null))
    {
      this.searchCritera.vehicleOwnerName = this.searchLogData.value.owner_name == "" ? null : this.searchLogData.value.owner_name;
      this.searchCritera.vehicleRegNumber = this.searchLogData.value.vehicle_reg_number == "" ? null : this.searchLogData.value.vehicle_reg_number;
      // @ts-ignore
      this.searchCritera.logStartDate = this.datePipe.transform(this.searchLogData.value.date_from, "dd.MM.yyyy");
      // @ts-ignore
      this.searchCritera.logEndDate = this.datePipe.transform(this.searchLogData.value.date_to, "dd.MM.yyyy");
      this.travelHistoryService.searchTravelHistory(this.searchCritera).toPromise().then((data: User[]) => {
        this.users = data;
        if(data.length == 0) {
          this.showTravelHistoryInfo = false;
        } else {
          this.showTravelHistoryInfo = true;
        }
      });
    }
  }

  clearSearchForm(): void {
    this.searchLogData.patchValue({
      "owner_name": "",
      "vehicle_reg_number": "",
      "date_from": "",
      "date_to": ""
    });
  }
}
