import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Users } from "../../models/userlist";
import { UsertravellogService } from "../../services/usertravellog.service";
import { Travellog } from "../../models/travellog";
import { Router } from "@angular/router";
import { TravelhistoryService } from "../../services/travelhistory.service";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: 'app-travellogform',
  templateUrl: './travellogform.component.html',
  styleUrls: ['./travellogform.component.scss']
})
export class TravellogformComponent implements OnInit {

  allUsers: Users[] = <Users[]>[];
  maxDateForCalender: string | null;
  newTravelLogData: Partial<Travellog> = {};

  constructor(private router: Router,
              private datePipe: DatePipe,
              private toaster: ToastrService,
              private formBuilder: FormBuilder,
              private userService: UsertravellogService,
              private travelHistoryService: TravelhistoryService) {
    this.maxDateForCalender = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe((userData: Users[]) => this.allUsers = userData);
  }

  createNewTravelLog = this.formBuilder.group({
    userid: [null, { validators: [Validators.required] }],
    travel_route: [null, { validators: [Validators.required] }],
    travel_short_description: [null, { validators: [Validators.required] }],
    vechile_registration_number: [null, { validators: [Validators.required] }],
    vechile_odometer_start_number: [null, { validators: [Validators.required] }],
    vechile_odometer_end_number: [null, { validators: [Validators.required] }],
    vechine_travel_log_date: [null, { validators: [Validators.required] }]
  });

  onSubmit(): void {
    if(this.createNewTravelLog.valid) {
      this.newTravelLogData.travelRoute = this.createNewTravelLog.value.travel_route;
      this.newTravelLogData.travelShortDescription = this.createNewTravelLog.value.travel_short_description;
      this.newTravelLogData.logdate = this.datePipe.transform(this.createNewTravelLog.value.vechine_travel_log_date, 'M.dd.yyyy');
      this.newTravelLogData.userId = this.createNewTravelLog.value.userid;
      this.newTravelLogData.vehicleRegNumber = this.createNewTravelLog.value.vechile_registration_number;
      this.newTravelLogData.vehicleOdometerStart = this.createNewTravelLog.value.vechile_odometer_start_number;
      this.newTravelLogData.vehicleOdometerEnd = this.createNewTravelLog.value.vechile_odometer_end_number;

      this.travelHistoryService.saveUserTravelLogData(this.newTravelLogData).subscribe(() => {
        this.router.navigate([""]);
      }, error => {
        this.toaster.error(error.error._embedded.errors[0].message);
      });
    }
  }

  goToHome(): void {
    this.router.navigate([""]);
  }
}
