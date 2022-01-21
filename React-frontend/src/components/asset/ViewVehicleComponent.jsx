import React, { Component } from "react";
import VehicleService from "../../services/VehicleService";

class ViewVehicleComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      vehicle: {},
    };
    this.editVehicle = this.editVehicle.bind(this);
    this.deleteVehicle = this.deleteVehicle.bind(this);
  }

  deleteVehicle(id) {
    VehicleService.deleteVehicle(id).then((res) => {
      this.props.history.push("/vehicles");
    });
  }

  editVehicle(id) {
    this.props.history.push(`/add-vehicle/${id}`);
  }

  componentDidMount() {
    VehicleService.getVehicleById(this.state.id).then((res) => {
      this.setState({ vehicle: res.data });
    });
  }

  render() {
    return (
      <div>
        <br></br>
        <div className="card col-md-6 offset-md-4">
          <h3 className="text-center">Vehicle Details</h3>

          <table className="table table-striped table-bordered">
            <thead>
              <tr>
                <th> Name</th>
                <th> info</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td> Vehicle Id </td>
                <td> {this.state.vehicle.vehicleId} </td>
              </tr>
              <tr>
                <td> Registration No </td>
                <td> {this.state.vehicle.registrationNo} </td>
              </tr>

              <tr>
                <td>Chassis No</td>
                <td> {this.state.vehicle.chassisNo} </td>
              </tr>
              <tr>
                <td> Model Id </td>
                <td> {this.state.vehicle.modelId}</td>
              </tr>
            </tbody>
          </table>
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
              onClick={() => this.editVehicle(this.state.vehicle.vehicleId)}
            >
              Edit
            </button>
            <button
              type="button"
              className="btn btn-danger"
              style={{ marginBottom: "7px" }}
              onClick={() => this.deleteVehicle(this.state.vehicle.vehicleId)}
            >
              Delete
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default ViewVehicleComponent;
