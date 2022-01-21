import React, { Component } from "react";
import { Link } from "react-router-dom";
import logo from "../pics/logo.PNG";

class HeaderComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    return (
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <a
          className="navbar-brand"
          href="#"
          style={{ display: "flex", marginRight: "100px" }}
        >
          <img src={logo} width="30" height="30" alt="" />
          <p> Loan Manager </p>
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <li>
              <a className="nav-link" href="#">
                <Link to="/">Home</Link>
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">
                <Link to="/customers">Customers</Link>
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link enabled" href="#">
                <Link to="/loans">Loans</Link>
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link enabled" href="#">
                <Link to="/vehicles">Vehicles</Link>
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link enabled" href="#">
                <Link to="/view-report">Reports</Link>
              </a>
            </li>

            <li className="nav-item dropdown">
              <a
                className="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdown"
                role="button"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="true"
              >
                More
              </a>
              <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                <a className="dropdown-item" href="#">
                  Sales Per week
                </a>
                <a className="dropdown-item" href="#">
                  Sales Per Month
                </a>
                <div className="dropdown-divider"></div>
                <a className="dropdown-item" href="#">
                  Sign Out
                </a>
              </div>
            </li>
          </ul>
          <form className="form-inline my-2 my-lg-0">
            <input
              className="form-control mr-sm-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
            />
            <button
              className="btn btn-outline-success my-2 my-sm-0"
              type="submit"
            >
              Search
            </button>
          </form>
        </div>
      </nav>
    );
  }
}

export default HeaderComponent;
