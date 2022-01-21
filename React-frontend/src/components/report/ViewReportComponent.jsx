import React, { Component } from "react";
import LoanService from "../../services/LoanService";
import VehicleService from "../../services/VehicleService";
import CustomerService from "../../services/CustomerService";

class ReportComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      loans: [],
      customers: [],
      vehicles: [],
    };
  }

  renderTotalInterest(loans)
   {
  let valueAdded = 0
  let count = 0
  loans.map((loan) => (
    
     valueAdded+=(loan.outstandingAmount-loan.loanAmount)
     
       
  ))
  return valueAdded
 }
 renderTotalSales(loans)
   {
  let valueAdded = 0
  let count = 0
  loans.map((loan) => (
    
     valueAdded+=loan.loanAmount
     
       
  ))
  return valueAdded
 }
  componentDidMount() {
    LoanService.getLoans().then((res) => {
      this.setState({ loans: res.data });
    });
    VehicleService.getVehicles().then((res) => {
      this.setState({ vehicles: res.data });
    });
    CustomerService.getCustomers().then((res) => {
      this.setState({ customers: res.data });
    });
  }

  render() {
    return <div>
      <div class="jumbotron text-center">
    <h1>Business Report</h1>
      <p>Total sales: {this.renderTotalSales(this.state.loans)}</p>
      <p>Total Interest: {this.renderTotalInterest(this.state.loans)}</p>
  </div>
  
  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <h3>Customers</h3>
        <p>Total number of customer: {this.state.customers.length}</p>
      </div>
      <div class="col-sm-4">
        <h3>Loans</h3>
        <p>Total number of loans disbursed: {this.state.loans.length}</p>
      </div>
      <div class="col-sm-4">
        <h3>Assets </h3>
        <p>Total number of assets disbursed: {this.state.vehicles.length}</p>
      </div>
    </div>
  </div>
  </div>;
  }
}

export default ReportComponent;
