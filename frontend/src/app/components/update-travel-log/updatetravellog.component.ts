import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { Travellog } from "../../models/travellog";
import { FormBuilder, Validators } from "@angular/forms";
import { DatePipe } from "@angular/common";
import { ToastrService } from 'ngx-toastr';
import { TravelhistoryService } from "../../services/travelhistory.service";

@Component({
  selector: 'app-updatetravellog',
  templateUrl: './updatetravellog.component.html',
  styleUrls: ['./updatetravellog.component.scss']
})
export class UpdatetravellogComponent implements OnInit {

  userId: number = 0;
  travelLogId: number = 0;
  maxDateForCalender: string | null;
  updatedTravelLog: Partial<Travellog> = {};

  form = this.formBuilder.group({
    travel_route: ['', { validators: [Validators.required] }],
    travel_short_description: ['', { validators: [Validators.required] }],
    vechile_registration_number: ['', { validators: [Validators.required] }],
    vechile_odometer_start_number: ['', { validators: [Validators.required] }],
    vechile_odometer_end_number: ['', { validators: [Validators.required] }],
    vechile_history_log_date: ['', { validators: [Validators.required] }],
  });

  constructor(private toaster: ToastrService,
              private datePipe: DatePipe,
              private formBuilder: FormBuilder,
              private router: Router,
              private activeRoute: ActivatedRoute,
              private travelHistoryDataService: TravelhistoryService) {
    this.maxDateForCalender = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  }

  ngOnInit(): void {
    this.activeRoute.queryParams.subscribe((param) => {
      this.travelHistoryDataService.getTravelHistoryById(param["travelhistoryid"])
        .subscribe((data: Travellog) => this.setFormValues(data));
    });
  }

  updateHistoryLogData(): void {
    if(this.form.value) {
      this.updatedTravelLog.userId = this.userId;
      this.updatedTravelLog.id = this.travelLogId;
      this.updatedTravelLog.travelRoute = this.form.value.travel_route;
      this.updatedTravelLog.travelShortDescription = this.form.value.travel_short_description;
      this.updatedTravelLog.logdate = this.datePipe.transform(this.form.value.vechile_history_log_date, 'M.dd.yyyy');
      this.updatedTravelLog.vehicleRegNumber = this.form.value.vechile_registration_number;
      this.updatedTravelLog.vehicleOdometerStart = this.form.value.vechile_odometer_start_number;
      this.updatedTravelLog.vehicleOdometerEnd = this.form.value.vechile_odometer_end_number;
      this.travelHistoryDataService.updateTravelLogHistory(this.updatedTravelLog).subscribe(() => {
        this.router.navigate([""]);
      }, error => {
        this.toaster.error(error.error._embedded.errors[0].message);
      })
    }
  }

  goToHome(): void {
    this.router.navigate([""]);
  }

  setFormValues(travelHistoryLog: Travellog) {
    this.form.patchValue({
      "travel_route": travelHistoryLog.travelRoute,
      "vechile_history_log_date" : this.datePipe.transform(travelHistoryLog.logdate, 'yyyy-MM-dd'),
      "vechile_registration_number": travelHistoryLog.vehicleRegNumber,
      "vechile_odometer_end_number": travelHistoryLog.vehicleOdometerEnd,
      "travel_short_description": travelHistoryLog.travelShortDescription,
      "vechile_odometer_start_number": travelHistoryLog.vehicleOdometerStart
    });
    this.travelLogId = travelHistoryLog.id;
    this.userId = travelHistoryLog.userId;
  }
}
