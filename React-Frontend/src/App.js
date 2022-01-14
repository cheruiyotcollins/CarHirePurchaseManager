import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListCustomerComponent from './components/customer/ListCustomerComponent';
import ListLoanComponent from './components/loan/ListLoanComponent';
import ListVehicleComponent from './components/asset/ListVehicleComponent';
import CreateCustomerComponent from './components/customer/CreateCustomerComponent';
import CreateLoanComponent from './components/loan/CreateLoanComponent';
import CreateVehicleComponent from './components/asset/CreateVehicleComponent';
import ViewCustomerComponent from './components/customer/ViewCustomerComponent';
import ReportComponent from './components/report/ReportComponent';
import ViewLoanComponent from './components/loan/ViewLoanComponent';
import ViewVehicleComponent from './components/asset/ViewVehicleComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';

function App() {




  return (
    <div>
        <Router>
              <HeaderComponent />
                <div className="container">
                    <Switch > 
                          <Route path = "/" exact component = {ListCustomerComponent}></Route>
                          <Route path = "/customers" component = {ListCustomerComponent}></Route>
                          <Route path = "/vehicles" component = {ListVehicleComponent}></Route>
                          <Route path = "/loans" component = {ListLoanComponent}></Route>
                          <Route path = "/add-customer/:id" component = {CreateCustomerComponent}></Route>
                          <Route path = "/add-vehicle/:id" component = {CreateVehicleComponent}></Route>
                          <Route path = "/add-loan/:id" component = {CreateLoanComponent}></Route>
                          <Route path = "/view-customer/:id" component = {ViewCustomerComponent}></Route>
                          <Route path = "/view-loan/:id" component = {ViewLoanComponent}></Route>
                          <Route path = "/view-vehicle/:id" component = {ViewVehicleComponent}></Route>
                          <Route path = "/view-report" component = {ReportComponent}></Route>
            
                          
                    </Switch>
                </div>
              <FooterComponent />
        </Router>
    </div>
    
  );
}

export default App;
