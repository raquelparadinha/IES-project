import { Card, Col, Space } from "antd";
import axios from "axios";
import { useState, useEffect } from "react";

function InmateNumbers() {
  const [dataSource, setDataSource] = useState();
  const fetchData = () => {
    try {
      return axios
        .get("http://localhost:5001/api/area/")
        .then((response) => setDataSource(response.data));
    } catch {
      console.log("Deu pylance");
    }
  };
  useEffect(() => {
    const interval = setInterval(() => {
      fetchData();
    }, 1000);
    return () => clearInterval(interval);
  }, []);
  if (dataSource !== undefined) {
    console.log(dataSource);
  }

  function SeeIfUndfined() {
    if (dataSource !== undefined) {
      return dataSource.map((zone) => (
        <>
          <p>
            {zone.name}: ??? / {zone.capacity} | Acess: {zone.access.toString()}
          </p>
        </>
      ));
    }
  }
  return (
    <>
      <Space>
        <Col>
          <br />
          <div style={{ textAlign: "center" }}>Inmate Numbers: 10</div>
          <br />
          <Card
            title="Areas"
            style={{ textAlign: "center", alignItems: "center", width: "270px" }}
          >
            {SeeIfUndfined()}
            {/* <p>Entrance: 12 / 100</p>
            <p>Visitors Wing: 12 / 100</p>
            <p>Staff Wing: 12 / 100</p>
            <p>Job Wing: 12 / 100</p>
            <p>Patio: 12 / 100</p>
            <p>Infirmary: 12 / 100</p>
            <p>Cell Block 1: 12 / 100</p>
            <p>Cell Block 2: 12 / 100</p>
            <p>Showers: 12 / 100</p>
            <p>Solitary: 12 / 100</p>
            <p>Canteen: 12 / 100</p> */}
          </Card>
        </Col>
      </Space>
    </>
  );
}

export default InmateNumbers;
