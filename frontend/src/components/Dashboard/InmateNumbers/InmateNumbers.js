import { Card, Col, Space } from "antd";
import axios from "axios";
import { useState, useEffect } from "react";
import { LoadingOutlined } from "@ant-design/icons";

function InmateNumbers() {
  const [dataSource, setDataSource] = useState();
  const [dataSource2, setDataSource2] = useState();
  const fetchData = () => {
    try {
      return axios
        .get("http://localhost:5001/api/area")
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

  const fetchData2 = () => {
    try {
      return axios
        .get("http://localhost:5001/api/inmate")
        .then((response) => setDataSource2(response.data));
    } catch {
      console.log("Deu pylance");
    }
  };
  useEffect(() => {
    const interval = setInterval(() => {
      fetchData2();
    }, 1000);
    return () => clearInterval(interval);
  }, []);

  function SeeIfUndfined() {
    console.log(dataSource);
    if (dataSource !== undefined) {
      return dataSource.map((zone) => (
        <>
          <p style={{ color: "#12494c" }}>
            {zone.name}: {zone.currentInmateIds.length} / {zone.capacity} |
            Acess: {zone.access.toString()}
          </p>
        </>
      ));
    } else {
      fetchData();
      return (
        <div>
          <LoadingOutlined />
        </div>
      );
    }
  }

  function SeeIfUndfined2() {
    if (dataSource2 !== undefined) {
      return (
        <p style={{ color: "#12494c" }}>Total Inmates: {dataSource2.length}</p>
      );
    } else {
      fetchData2();
      return (
        <div>
          <LoadingOutlined />
        </div>
      );
    }
  }
  return (
    <>
      <Space>
        <Col>
          <br />
          <div style={{ textAlign: "center" }}>{SeeIfUndfined2()}</div>
          <br />
          <Card
            title={<p style={{ color: "#12494c" }}>Areas</p>}
            style={{
              textAlign: "center",
              alignItems: "center",
              width: "270px",
              backgroundColor: "#EFF5F5",
            }}
          >
            {SeeIfUndfined()}
          </Card>
        </Col>
      </Space>
    </>
  );
}

export default InmateNumbers;
