import React, { Component } from "react";
import VehicleService from "../../services/VehicleService";

class CreateVehicleComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      // step 2
      id: this.props.match.params.id,
      registrationNo: "",
      chassisNo: "",
      modelId: "",
    };

    this.changeRegistrationNoHandler =
      this.changeRegistrationNoHandler.bind(this);
    this.changeChassisNoHandler = this.changeChassisNoHandler.bind(this);
    this.changeModelIdHandler = this.changeModelIdHandler.bind(this);
    this.saveOrUpdateVehicle = this.saveOrUpdateVehicle.bind(this);
  }

  // step 3
  componentDidMount() {
    // step 4
    if (this.state.id === "_add") {
      return;
    } else {
      VehicleService.getVehicleById(this.state.id).then((res) => {
        let vehicle = res.data;
        this.setState({
          registrationNo: vehicle.registrationNo,
          chassisNo: vehicle.chassisNo,
          modelId: vehicle.modelId,
        });
      });
    }
  }
  saveOrUpdateVehicle = (e) => {
    e.preventDefault();
    let vehicle = {
      registrationNo: this.state.registrationNo,
      chassisNo: this.state.chassisNo,
      modelId: this.state.modelId,
    };
    console.log("vehicle => " + JSON.stringify(vehicle));

    // step 5
    if (this.state.id === "_add") {
      VehicleService.createVehicle(vehicle).then((res) => {
        this.props.history.push("/vehicles");
      });
    } else {
      VehicleService.updateVehicle(vehicle, this.state.id).then((res) => {
        this.props.history.push("/vehicles");
      });
    }
  };

  changeRegistrationNoHandler = (event) => {
    this.setState({ registrationNo: event.target.value });
  };
  changeChassisNoHandler = (event) => {
    this.setState({ chassisNo: event.target.value });
  };
  changeModelIdHandler = (event) => {
    this.setState({ modelId: event.target.value });
  };

  cancel() {
    this.props.history.push("/vehicles");
  }

  getTitle() {
    if (this.state.id === "_add") {
      return <h3 className="text-center">New Vehicle Registration</h3>;
    } else {
      return <h3 className="text-center">Edit Vehicle Details</h3>;
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
                    <label>Registration No: </label>
                    <input
                      placeholder="Vehicle registration number"
                      name="registrationNo"
                      className="form-control"
                      value={this.state.registrationNo}
                      onChange={this.changeRegistrationNoHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label>Chassis No: </label>
                    <input
                      placeholder="Chessis Number"
                      name="chassisNo"
                      className="form-control"
                      value={this.state.chassisNo}
                      onChange={this.changeChassisNoHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Model Id: </label>
                    <input
                      placeholder="Model Id"
                      name="modelId"
                      className="form-control"
                      value={this.state.modelId}
                      onChange={this.changeModelIdHandler}
                    />
                  </div>

                  <button
                    className="btn btn-success"
                    onClick={this.saveOrUpdateVehicle}
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

export default CreateVehicleComponent;
