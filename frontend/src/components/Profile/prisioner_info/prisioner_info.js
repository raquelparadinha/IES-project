import React, { useEffect, useState } from "react";
import { Card, Col, Space } from "antd";
import { LoadingOutlined } from "@ant-design/icons/lib/icons";
import axios from "axios";

function Prisioner_info(id) {
  const [dataSource, setDataSource] = useState();
  const fetchData = () => {
    try {
      return axios
        .get("http://localhost:5001/api/inmate/" + id)
        .then((response) => setDataSource(response.data));
    } catch {
      console.log("Deu pylance");
      fetchData();
    }
  };
  useEffect(() => {
    const interval = setInterval(() => {
      fetchData();
    }, 1000);
    return () => clearInterval(interval);
  });

  function SeeIfUndfined() {
    if (dataSource !== undefined) {
      console.log(dataSource);
      // console.log(dataSource.birthdate.split("T")[0])
      return (
        <>
          <p>Id: {dataSource.id}</p>
          <p>WorkStationID: {dataSource.workstationId}</p>
          <p>HealthLogID: {dataSource.healthLogId}</p>
          <p>Solitary: {dataSource.solitary.toString()}</p>
          <p>Birthdate: {dataSource.birthDate}</p>
          <p>Sentence Start: {dataSource.entryDate}</p>
          <p>Sentence End: {dataSource.sentenceEnd}</p>
        </>
      );
    } else {
      fetchData();
      return (
        <div>
          <LoadingOutlined />
        </div>
      );
    }
  }
  let title_;
  if (dataSource !== undefined) {
    title_ = dataSource.name;
  } else {
    title_ = <LoadingOutlined />;
  }
  return (
    <Card title="Prisioner Personal Information">
      <Col
        style={{
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Space align="start">
          <Card title={title_} style={{ width: "250px", height: "350px" }}>
            {SeeIfUndfined()}
          </Card>
          <Card
            title="Health status"
            style={{ width: "250px", height: "350px" }}
          >
            <p>HeartBeat: 70</p>
            <p>Stress Levels: 15%</p>
            <p>Glicose Levels: 33</p>
            <p>Uric Acid: 23</p>
            <p>Cholestrol: 45</p>
            <p>Toxic Screen: 12</p>
          </Card>
        </Space>
      </Col>
    </Card>
  );
}
export default Prisioner_info;
