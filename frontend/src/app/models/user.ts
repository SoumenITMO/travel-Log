import {Travellog} from "./travellog";

export interface User {
    id: number;
    name: string;
    age: number;
    address: string;
    distanceTraveld: string;
    logs: Travellog[];
}
