import React, { Component } from "react";
import CustomerService from "../../services/CustomerService";
import dateFormat from 'dateformat';
class ListCustomerComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      customers: [],
    };
    this.addCustomer = this.addCustomer.bind(this);
  }

  viewCustomer(id) {
    this.props.history.push(`/view-customer/${id}`);
  }

  componentDidMount() {
    CustomerService.getCustomers().then((res) => {
      this.setState({ customers: res.data });
    });
  }

  // addCustomer() {
  //   this.props.history.push("/add-customer/_add");
  // }

  addCustomer() {
    this.props.history.push("/add-customer/_add");
  }

  render() {
    return (
      <div>
        <div className="top-bar">
          <div className="text-center">
            <h4> Customer Records (Total: {this.state.customers.length})</h4>
          </div>
          <div style={{ display: "flex", justifyContent: "right" }}>
            <button
              className="btn btn-info"
              style={{ textDecorationThickness: "15px" }}
              onClick={this.addCustomer}
            >
              New Customer
            </button>
          </div>
        </div>
        <br></br>

        <div className="row">{this.renderCustomers()}</div>
      </div>
    );
  }

  renderCustomers() {
    if (this.state.customers.length === 0)
      return (
        <h4
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          {" "}
          No customer record found{" "}
        </h4>
      );

    return (
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th> Id</th>
            <th> Name</th>
            <th> Mobile</th>
            <th> Email</th>
            <th> Status</th>
            <th> Sex</th>
            <th> Registered On</th>
            <th> Actions</th>
          </tr>
        </thead>
        <tbody>
          {this.state.customers.map((customer) => (
            <tr key={customer.customerId}>
              <td> {customer.customerId} </td>
              <td> {customer.customerName} </td>
              <td> {customer.mobileNo}</td>
              <td> {customer.email}</td>
              <td> {customer.status}</td>
              <td> {customer.sex}</td>
              <td> {dateFormat(customer.registeredOn, "mmmm dS, yyyy, h:MM:ss TT")}</td>
              <td>
                <button
                  style={{ marginRight: "5px" }}
                  onClick={() => this.viewCustomer(customer.customerId)}
                  className="btn btn-primary"
                >
                  View{" "}
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    );
  }
}

export default ListCustomerComponent;
