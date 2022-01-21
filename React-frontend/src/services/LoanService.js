import axios from 'axios';

const LOAN_API_BASE_URL = "http://localhost:5656/loan";

class LoanService {

    getLoans(){
        return axios.get(LOAN_API_BASE_URL+'/list');
    }

    createLoan(loan){
        return axios.post(LOAN_API_BASE_URL +'/add', loan);

       

                  
          
          
    }

    getLoanById(id){
        return axios.get(LOAN_API_BASE_URL + '/view/' + id);
    }

    updateLoan(loan, id){
        return axios.put(LOAN_API_BASE_URL + '/update/' + id, loan);
    }

    deleteLoan(id){
        return axios.delete(LOAN_API_BASE_URL + '/delete/' + id);
    }
}

export default new LoanService()