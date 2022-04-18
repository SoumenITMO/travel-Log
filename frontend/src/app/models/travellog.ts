export interface Travellog {
  id: number;
  userId: number;
  logdate: string|null;
  vehicleRegNumber: string;
  vehicleOdometerStart: number;
  vehicleOdometerEnd: number;
  travelRoute: string;
  travelShortDescription: string;
}
