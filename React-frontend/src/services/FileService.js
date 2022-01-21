import service from '../components/customer/Service.jsx';

export class FileService {
    uploadFileToServer(file,customer_id ){
        //returns Promise object
        return service.getRestClient().post('/photo/add/'+customer_id, file);
    }
}
export default new FileService()