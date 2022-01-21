import React, { useState, useEffect, Component } from "react";
import LoanService from "../../services/LoanService";
import dateFormat from 'dateformat';


class ListLoanComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      loans: [],
    };
    this.addLoan = this.addLoan.bind(this);
  }

  viewLoan(id) {
    this.props.history.push(`/view-loan/${id}`);
  }

  componentDidMount() {
    LoanService.getLoans().then((res) => {
      this.setState({ loans: res.data });
    });
  }

  addLoan() {
    this.props.history.push("/add-loan/_add");
  }

  render() {
    return (
      <div>
        <div className="top-bar">
          <div className="text-center">
            <h4> Loan Records (Total: {this.state.loans.length})</h4>
          </div>
          <div style={{ display: "flex", justifyContent: "right" }}>
            <button
              className="btn btn-info"
              style={{ textDecorationThickness: "15px" }}
              onClick={this.addLoan}
            >
              New Loan
            </button>
          </div>
        </div>
        <br></br>

        <div className="row">{this.renderLoans()}</div>
      </div>
    );
  }

  renderLoans() {
    if (this.state.loans.length === 0)
      return (
        <h4
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          {" "}
          No loan record found{" "}
        </h4>
      );

    return (
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th> Loan Id</th>
            <th> Customer Id</th>
            <th> Vehicle Make</th>
            <th> Amount</th>
            <th> Interest</th>
            <th> Total</th>
            <th> Disbursed On</th>
            <th> Due On</th>
            <th> Duration-Days</th>
            <th> Actions</th>
          </tr>
        </thead>
        <tbody>
          {this.state.loans.map((loan) => (
            <tr key={loan.loanId}>
              <td> {loan.loanId} </td>
              <td> {loan.customerId} </td>
              <td> {loan.vehicleMakeId}</td>
              <td> {loan.loanAmount}</td>
              <td> {loan.loanInterest}</td>
              <td> {loan.outstandingAmount}</td>
              <td> {dateFormat(loan.disbusedOnDate, "mmmm dS, yyyy, h:MM:ss TT")}</td>
               <td> {dateFormat(loan.loanDueDate, "mmmm dS, yyyy")}</td>
              <td> {loan.loanDuration}</td>
              <td>
                <button
                  style={{ marginRight: "5px" }}
                  onClick={() => this.viewLoan(loan.loanId)}
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

export default ListLoanComponent;
