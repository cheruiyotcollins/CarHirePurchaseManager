import React, { useEffect, useState } from "react";

const UsingFetch = () => {
  const [customers, setCustomers] = useState([]);

  const fetchData = () => {
    fetch("http://localhost:9191/customer")
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setCustomers(data);
      });
  };

  useEffect(() => {
    fetchData();
  }, []);

  return customers;
};

export default UsingFetch;
