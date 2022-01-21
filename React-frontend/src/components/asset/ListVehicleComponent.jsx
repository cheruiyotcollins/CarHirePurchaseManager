import React, { useState, useEffect, Component } from "react";
import VehicleService from "../../services/VehicleService";
import UsingFetch from "../UsingFetch";

class ListVehicleComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      vehicles: [],
    };
    this.addVehicle = this.addVehicle.bind(this);
  }

  viewVehicle(id) {
    this.props.history.push(`/view-vehicle/${id}`);
  }

  componentDidMount() {
    VehicleService.getVehicles().then((res) => {
      this.setState({ vehicles: res.data });
    });
  }

  addVehicle() {
    this.props.history.push("/add-vehicle/_add");
  }

  render() {
    return (
      <div>
        <div className="top-bar">
          <div className="text-center">
            <h4> Vehicle Records (Total: {this.state.vehicles.length})</h4>
          </div>
          <div style={{ display: "flex", justifyContent: "right" }}>
            <button
              className="btn btn-info"
              style={{ textDecorationThickness: "15px" }}
              onClick={this.addVehicle}
            >
              New Vehicle
            </button>
          </div>
        </div>
        <br></br>

        <div className="row">{this.renderVehicles()}</div>
      </div>
    );
  }

  renderVehicles() {
    if (this.state.vehicles.length === 0)
      return (
        <h4
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          {" "}
          No Vehicle record found{" "}
        </h4>
      );

    return (
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th> Id</th>
            <th> Registration No</th>
            <th> Chassis No</th>
            <th> Model Id</th>
            <th> Actions</th>
          </tr>
        </thead>
        <tbody>
          {this.state.vehicles.map((vehicle) => (
            <tr key={vehicle.vehicleId}>
              <td> {vehicle.vehicleId} </td>
              <td> {vehicle.registrationNo} </td>
              <td> {vehicle.chassisNo} </td>
              <td> {vehicle.modelId}</td>
              <td>
                <button
                  style={{ marginRight: "5px" }}
                  onClick={() => this.viewVehicle(vehicle.vehicleId)}
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

export default ListVehicleComponent;
