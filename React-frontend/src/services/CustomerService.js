import axios from 'axios';

const CUSTOMER_API_BASE_URL = "http://localhost:9191/customer";

class CustomerService {

    getCustomers(){
        return axios.get(CUSTOMER_API_BASE_URL+'/list');
    }

    createCustomer(customer){
        return axios.post(CUSTOMER_API_BASE_URL +'/add', customer);
    }

    getCustomerById(customer_id){
        return axios.get(CUSTOMER_API_BASE_URL + '/view/' + customer_id);
    }

    updateCustomer(customer, customer_id){
        return axios.put(CUSTOMER_API_BASE_URL + '/update/' + customer_id, customer);
    }

    deleteCustomer(customer_id){
        return axios.delete(CUSTOMER_API_BASE_URL + '/delete/' + customer_id);
    }
}

export default new CustomerService()