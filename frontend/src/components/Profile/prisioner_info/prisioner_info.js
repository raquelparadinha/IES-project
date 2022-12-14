import React, { useEffect, useState } from "react";
import { Card, Col, Space } from "antd";
import { LoadingOutlined } from "@ant-design/icons/lib/icons";
import axios from "axios";
import "../profile.css";

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
          <p style={{ color: "#12494c" }}>Id: {dataSource.id}</p>
          <p style={{ color: "#12494c" }}>
            WorkStationID: {dataSource.workstationId}
          </p>
          <p style={{ color: "#12494c" }}>
            HealthLogID: {dataSource.healthLogId}
          </p>
          <p style={{ color: "#12494c" }}>
            Solitary: {dataSource.solitary.toString()}
          </p>
          <p style={{ color: "#12494c" }}>Birthdate: {dataSource.birthDate}</p>
          <p style={{ color: "#12494c" }}>
            Sentence Start: {dataSource.entryDate}
          </p>
          <p style={{ color: "#12494c" }}>
            Sentence End: {dataSource.sentenceEnd}
          </p>
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
    title_ = <div style={{ color: "#12494c" }}>{dataSource.name}</div>;
  } else {
    title_ = <LoadingOutlined />;
  }
  return (
    <Card
      title={
        <div style={{ color: "#12494c" }}>Prisioner Personal Information</div>
      }
      style={{ backgroundColor: "#D6E4E5" }}
    >
      <Col
        style={{
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Space align="start">
          <Card
            title={title_}
            style={{
              width: "250px",
              height: "350px",
              backgroundColor: "#EFF5F5",
            }}
          >
            {SeeIfUndfined()}
          </Card>
          <Card
            title={<div style={{ color: "#12494c" }}>Health Status</div>}
            style={{
              width: "250px",
              height: "350px",
              backgroundColor: "#EFF5F5",
            }}
          >
            <p style={{ color: "#12494c" }}>HeartBeat: 70</p>
            <p style={{ color: "#12494c" }}>Stress Levels: 15%</p>
            <p style={{ color: "#12494c" }}>Glicose Levels: 33</p>
            <p style={{ color: "#12494c" }}>Uric Acid: 23</p>
            <p style={{ color: "#12494c" }}>Cholestrol: 45</p>
            <p style={{ color: "#12494c" }}>Toxic Screen: 12</p>
          </Card>
        </Space>
      </Col>
    </Card>
  );
}
export default Prisioner_info;
