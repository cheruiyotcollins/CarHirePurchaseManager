import axios from 'axios';

const VEHICLE_API_BASE_URL = "http://localhost:9090/vehicle";

class VehicleService {

    getVehicles(){
        return axios.get(VEHICLE_API_BASE_URL+'/list');
    }

    createVehicle(vehicle){
        return axios.post(VEHICLE_API_BASE_URL +'/add', vehicle);
    }

    getVehicleById(id){
        return axios.get(VEHICLE_API_BASE_URL + '/view/' + id);
    }

    updateVehicle(vehicle, id){
        return axios.put(VEHICLE_API_BASE_URL + '/update/' + id, vehicle);
    }

    deleteVehicle(id){
        return axios.delete(VEHICLE_API_BASE_URL + '/delete/' + id);
    }
}

export default new VehicleService()