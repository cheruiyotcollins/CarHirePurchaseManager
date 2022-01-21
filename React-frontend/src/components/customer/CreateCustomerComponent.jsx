import React, { Component } from "react";
import CustomerService from "../../services/CustomerService";
import FileService from "../../services/FileService";


class CreateCustomerComponent extends Component {
  constructor(props) {
    super(props);
   
    this.state = {
      // step 2
      id: this.props.match.params.id,
      customerId: "",
      customerName: "",
      mobileNo: "",
      email: "",
      status: "",
      sex: "",
      data:null,
      
          };
    this.changeCustomerIdHandler = this.changeCustomerIdHandler.bind(this);
    this.changeCustomerNameHandler = this.changeCustomerNameHandler.bind(this);
    this.changeEmailHandler = this.changeEmailHandler.bind(this);
    this.changeMobileNoHandler = this.changeMobileNoHandler.bind(this);
    this.changeStatusHandler = this.changeStatusHandler.bind(this);
    this.changeSexHandler = this.changeSexHandler.bind(this);
    this.saveOrUpdateCustomer = this.saveOrUpdateCustomer.bind(this);
    this.handleUploadFile = this.handleUploadFile.bind(this);
  }

  // step 3
  componentDidMount() {
    // step 4
    if (this.state.id === "_add") {
      return;
    } else {
      CustomerService.getCustomerById(this.state.id).then((res) => {
        let customer = res.data;
        this.setState({
          customerId: customer.customerId,
          customerName: customer.customerName,
          email: customer.email,
          mobileNo: customer.mobileNo,
          status: customer.status,
          sex: customer.sex,
            
        });
      });
    }
  }
  saveOrUpdateCustomer = (e) => {
    e.preventDefault();
    let customer = {
      customerId: this.state.customerId,
      customerName: this.state.customerName,
      email: this.state.email,
      mobileNo: this.state.mobileNo,
      status: this.state.status,
      sex: this.state.sex,
      
    };
   
   
    console.log("customer => " + JSON.stringify(customer));
    
    // step 5
    if (this.state.id === "_add") {
      CustomerService.createCustomer(customer).then((res) => {
        this.props.history.push("/customers");
      });

    let data= this.state.data
    const file = new FormData();
    console.log("Uploading file", data);
    file.append('file', data);
    file.append('name', 'my_file');
    file.append('description', 'this file is uploaded by collins');
        //calling async Promise and handling response or error situation
        FileService.uploadFileToServer(file,customer.customerId).then((response) => {
        console.log("File " + data.name + " is uploaded");
    }).catch(function (error) {
        console.log(error);
        if (error.response) {
            //HTTP error happened
            console.log("Upload error. HTTP error/status code=",error.response.status);
        } else {
            //some other error happened
           console.log("Upload error. HTTP error/status code=",error.message);
        }
    });
         

    } else {
      CustomerService.updateCustomer(customer, this.state.id).then((res) => {
        this.props.history.push("/customers");
      });
    }
  };

  changeCustomerNameHandler = (event) => {
    this.setState({ customerName: event.target.value });
  };
  changeSexHandler = (event) => {
    this.setState({ sex: event.target.value });
  };

  changeCustomerIdHandler = (event) => {
    this.setState({ customerId: event.target.value });
  };
   
  changeEmailHandler = (event) => {
    this.setState({ email: event.target.value });
  };

  changeMobileNoHandler = (event) => {
    this.setState({ mobileNo: event.target.value });
  };

  changeStatusHandler = (event) => {
    this.setState({ status: event.target.value });
  };
  handleUploadFile = (event) => {
    this.setState({ data: event.target.files[0] });
        
};
    


  cancel() {
    this.props.history.push("/customers");
  }

  getTitle() {
    if (this.state.id === "_add") {
      return <h3 className="text-center">New Customer</h3>;
    } else {
      return <h3 className="text-center">Customer Update</h3>;
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
                    <label> Id: </label>
                    <input
                      placeholder="National Id"
                      name="customerId"
                      className="form-control"
                      value={this.state.customerId}
                      onChange={this.changeCustomerIdHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Name: </label>
                    <input
                      placeholder="Full Names"
                      name="customerName"
                      className="form-control"
                      value={this.state.customerName}
                      onChange={this.changeCustomerNameHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Email: </label>
                    <input
                      placeholder="Official Email"
                      name="email"
                      className="form-control"
                      value={this.state.email}
                      onChange={this.changeEmailHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Mobile No: </label>
                    <input
                      placeholder="Mobile Number"
                      name="mobileNo"
                      className="form-control"
                      value={this.state.mobileNo}
                      onChange={this.changeMobileNoHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Status: </label>
                    <input
                      placeholder="Customer Status"
                      name="status"
                      className="form-control"
                      value={this.state.status}
                      onChange={this.changeStatusHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Gender: </label>
                    <input
                      placeholder="Gender"
                      name="status"
                      className="form-control"
                      value={this.state.sex}
                      onChange={this.changeSexHandler}
                    />
                  </div>
                 
                   <div className="form-group files color">
                      <label>Upload Your Photo </label>
                      <input type="file" className="form-control" name="file" onChange={this.handleUploadFile}/>
                   </div>
                  <button
                    className="btn btn-success"
                    onClick={this.saveOrUpdateCustomer}
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

export default CreateCustomerComponent;
