import axios from 'axios';

const CUSTOMER_API_BASE_URL = "http://localhost:9191/customer";

class PhotosService {

    getPhotos(){
        return axios.get(CUSTOMER_API_BASE_URL+'/list');
    }

    createPhoto(photo, photo_id){
        return axios.post(CUSTOMER_API_BASE_URL +'/photo/add/'+photo_id, photo);
    }

    getPhotoById(customer_id){
        return axios.get(CUSTOMER_API_BASE_URL + '/view/' + customer_id);
    }

    updatePhoto(customer, customer_id){
        return axios.put(CUSTOMER_API_BASE_URL + '/update/' + customer_id, customer);
    }

    deletePhoto(customer_id){
        return axios.delete(CUSTOMER_API_BASE_URL + '/delete/' + customer_id);
    }
}

export default new PhotosService()