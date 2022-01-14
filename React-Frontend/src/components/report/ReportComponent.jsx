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
    return <div>hello</div>;
  }
}

export default ReportComponent;
