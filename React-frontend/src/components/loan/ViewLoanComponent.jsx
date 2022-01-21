import React, { Component } from "react";
import LoanService from "../../services/LoanService";

class ViewLoanComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      loan: {},
    };
    this.editLoan = this.editLoan.bind(this);
    this.deleteLoan = this.deleteLoan.bind(this);
  }

  deleteLoan(id) {
    LoanService.deleteLoan(id).then((res) => {
      this.props.history.push("/loans");
    });
  }

  editLoan(id) {
    this.props.history.push(`/add-loan/${id}`);
  }

  componentDidMount() {
    LoanService.getLoanById(this.state.id).then((res) => {
      this.setState({ loan: res.data });
    });
  }

  render() {
    return (
      <div>
        <br></br>
        <div className="card col-md-6 offset-md-4">
          <h3 className="text-center">Loan Details</h3>
          <div className="card-body">
            <div className="row">
              <label>Loan Id: </label>
              <div> {this.state.loan.loanId}</div>
            </div>
            <div className="row">
              <label>Customer Id: </label>
              <div> {this.state.loan.customerId}</div>
            </div>
            <div className="row">
              <label>Vehicle Make: </label>
              <div> {this.state.loan.vehicleMakeId}</div>
            </div>
            <div className="row">
              <label>Principal Amount: </label>
              <div> {this.state.loan.loanAmount}</div>
            </div>
            <div className="row">
              <label>Interest: </label>
              <div> {this.state.loan.loanInterest}%</div>
            </div>
            <div className="row">
              <label>Total Due: </label>
              <div> {this.state.loan.outstandingAmount}</div>
            </div>
            <div className="row">
              <label>Disbursed on: </label>
              <div> {this.state.loan.disbusedOnDate}</div>
            </div>
            <div className="row">
              <label>Due On: </label>
              <div> {this.state.loan.loanDueDate}</div>
            </div>
            <div className="row">
              <label>Duration: </label>
              <div> {this.state.loan.loanDuration} days</div>
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
              onClick={() => this.editLoan(this.state.loan.loanId)}
            >
              Edit
            </button>
            <button
              type="button"
              className="btn btn-danger"
              style={{ marginBottom: "7px" }}
              onClick={() => this.deleteLoan(this.state.loan.loanId)}
            >
              Delete
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default ViewLoanComponent;
