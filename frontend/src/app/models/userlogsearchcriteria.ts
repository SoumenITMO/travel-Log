export interface UserLogSearchCriteria {
  vehicleRegNumber: string;
  vehicleOwnerName: string;
  logStartDate: Date|null;
  logEndDate: Date;
}
