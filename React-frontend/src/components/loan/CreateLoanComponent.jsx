import React, { Component } from "react";
import LoanService from "../../services/LoanService";

class CreateLoanComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      customerId: "",
      vehicleMakeId: "",
      loanAmount: "",
      loanDuration: "",
    };

    this.changeCustomerIdHandler = this.changeCustomerIdHandler.bind(this);
    this.changeVehicleMakeIdHandler = this.changeVehicleMakeIdHandler.bind(this);
    this.changeLoanAmountHandler = this.changeLoanAmountHandler.bind(this);
    this.changeLoanDurationHandler = this.changeLoanDurationHandler.bind(this);
    this.saveOrUpdateLoan = this.saveOrUpdateLoan.bind(this);
  }

  // step 3
  componentDidMount() {
    // step 4
    if (this.state.id === "_add") {
      return;
    } else {
      LoanService.getLoanById(this.state.id).then((res) => {
        let loan = res.data;
        this.setState({
          customerId: loan.customerId,
          vehicleMakeId: loan.vehicleMakeId,
          loanAmount: loan.loanAmount,
          loanDuration: loan.loanDuration,
        });
      });
    }
  }
  saveOrUpdateLoan = (e) => {
    e.preventDefault();
    let loan = {
      customerId: this.state.customerId,
      vehicleMakeId: this.state.vehicleMakeId,
      loanAmount: this.state.loanAmount,
      loanDuration: this.state.loanDuration,
    };
    console.log("loan => " + JSON.stringify(loan));

    // step 5
    if (this.state.id === "_add") {
      LoanService.createLoan(loan).then((res) => {
        this.props.history.push("/loans");
      });
    } else {
      LoanService.updateLoan(loan, this.state.id).then((res) => {
        this.props.history.push("/loans");
      });
    }
  };

  changeCustomerIdHandler = (event) => {
    this.setState({ customerId: event.target.value });
  };
  changeVehicleMakeIdHandler = (event) => {
    this.setState({ vehicleMakeId: event.target.value });
  };
  changeLoanAmountHandler = (event) => {
    this.setState({ loanAmount: event.target.value });
  };

  changeLoanDurationHandler = (event) => {
    this.setState({ loanDuration: event.target.value });
  };

  cancel() {
    this.props.history.push("/loans");
  }

  getTitle() {
    if (this.state.id === "_add") {
      return <h3 className="text-center">New Loan</h3>;
    } else {
      return <h3 className="text-center">Edit Loan</h3>;
    }
  }
  render() {
    return (
      <div>
        <br></br>
        <div className="container">
          <div className="row">
            <div className="card col-md-6 offset-md-3 offset-md-3">
              {this.getTitle()}
              <div className="card-body">
                <form>
                  <div className="form-group">
                    <label>Customer Id: </label>
                    <input
                      placeholder="National Id"
                      name="customerId"
                      className="form-control"
                      value={this.state.customerId}
                      onChange={this.changeCustomerIdHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Vehicle Make: </label>
                    <input
                      placeholder="Vehicle Make"
                      name="vehicleMakeId"
                      className="form-control"
                      value={this.state.vehicleMakeId}
                      onChange={this.changeVehicleMakeIdHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Loan Amount: </label>
                    <input
                      placeholder="Loan Amount"
                      name="loanAmount"
                      className="form-control"
                      value={this.state.loanAmount}
                      onChange={this.changeLoanAmountHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Loan Duration : </label>
                    <input
                      placeholder="Loan Duration; 3/6 or 12 Months"
                      name="loanDuration"
                      className="form-control"
                      value={this.state.loanDuration}
                      onChange={this.changeLoanDurationHandler}
                    />
                  </div>

                  <button
                    className="btn btn-success"
                    onClick={this.saveOrUpdateLoan}
                  >
                    Save
                  </button>
                  <button
                    className="btn btn-danger"
                    onClick={this.cancel.bind(this)}
                    style={{ marginLeft: "10px" }}
                  >
                    Cancel
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default CreateLoanComponent;
