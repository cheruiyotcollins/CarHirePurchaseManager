import React, { Component } from "react";
import CustomerService from "../../services/CustomerService";

class ViewCustomerComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      customer: {},
    };
    this.editCustomer = this.editCustomer.bind(this);
    this.deleteCustomer = this.deleteCustomer.bind(this);
  }

  deleteCustomer(id) {
    CustomerService.deleteCustomer(id).then((res) => {
      this.props.history.push("/customers");
    });
  }

  editCustomer(id) {
    this.props.history.push(`/add-customer/${id}`);
  }

  componentDidMount() {
    CustomerService.getCustomerById(this.state.id).then((res) => {
      this.setState({ customer: res.data });
    });
  }

  render() {
    return (
      <div>
        <br></br>
        <div className="card col-md-6 offset-md-4">
          <h3 className="text-center">Customer Details</h3>
          <div className="card-body">
            <div className="row">
              <label>Id No: </label>
              <div> {this.state.customer.customerId}</div>
            </div>
            <div className="row">
              <label>Name: </label>
              <div> {this.state.customer.customerName}</div>
            </div>
            <div className="row">
              <label>Mobile No: </label>
              <div> {this.state.customer.mobileNo}</div>
            </div>
            <div className="row">
              <label>Email : </label>
              <div> {this.state.customer.email}</div>
            </div>
            <div className="row">
              <label>Status: </label>
              <div> {this.state.customer.status}</div>
            </div>
            <div className="row">
              <label>Sex: </label>
              <div> {this.state.customer.sex}</div>
            </div>
            <div className="row">
              <label>Registered On: </label>
              <div> {this.state.customer.registeredOn}</div>
            </div>
          </div>
          <br></br>
          <div className="row">
            <button
              type="button"
              className="btn btn-success"
              style={{
                marginBottom: "7px",
                marginRight: "290px",
                marginLeft: "20px",
              }}
              onClick={() => this.editCustomer(this.state.customer.customerId)}
            >
              Edit
            </button>
            <button
              type="button"
              className="btn btn-danger"
              style={{ marginBottom: "7px" }}
              onClick={() =>
                this.deleteCustomer(this.state.customer.customerId)
              }
            >
              Delete
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default ViewCustomerComponent;
