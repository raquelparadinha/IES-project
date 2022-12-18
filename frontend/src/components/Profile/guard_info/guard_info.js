import React, { useEffect, useState } from "react";
import { Card, Col, Space } from "antd";
import axios from "axios";
import {
  LoadingOutlined,
  RightCircleFilled,
} from "@ant-design/icons/lib/icons";

function Guard_info(id) {
  const [dataSource, setDataSource] = useState();
  const [dataSource2, setDataSource2] = useState();

  const fetchData = () => {
    try {
      return axios
        .get("http://localhost:5001/api/guard/" + id)
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

  const fetchData2 = () => {
    try {
      return axios
        .get("http://localhost:5001/api/guard/" + id + "/details")
        .then((response) => setDataSource2(response.data));
    } catch {
      console.log("Deu pylance");
      fetchData2();
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
      // console.log(dataSource)
      // console.log(dataSource.birthdate.split("T")[0])
      return (
        <>
          <p style={{ color: "#12494c" }}>Id: {dataSource.id}</p>
          <p style={{ color: "#12494c" }}>AreaID: {dataSource.areaId}</p>
          <p style={{ color: "#12494c" }}>Birthdate: {dataSource.birthdate}</p>
          <p style={{ color: "#12494c" }}>Phone: {dataSource.phone}</p>
          <p style={{ color: "#12494c" }}>Email: {dataSource.email}</p>
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
  function SeeIfUndfined2() {
    if (dataSource2 !== undefined) {
      console.log(dataSource2);
      // console.log(dataSource.birthdate.split("T")[0])
      if (dataSource2.length > 1) {
        return (
          <>
            <p style={{ textAlign: "left", color: "#12494c" }}>
              <RightCircleFilled /> Area Guards:
            </p>
            {new Array(dataSource2.length).fill(null).map((_, i) => {
              //  colocar i+1 no lugar do i pois o i = 0 vai ser sobre o estado da area e não é para ser usado aqui
              return <p style={{ color: "#12494c" }}>{dataSource2[i + 1]}</p>;
            })}
            <p style={{ textAlign: "left", color: "#12494c" }}>
              <RightCircleFilled /> Area Status:
            </p>
            <div style={{ color: "#12494c" }}>{dataSource2[0]}</div>
          </>
        );
      } else {
        return (
          <>
            <p style={{ textAlign: "left", color: "#12494c" }}>
              <RightCircleFilled /> Area Guards:
            </p>
            <p>No Colleagues</p>
            <p style={{ textAlign: "left", color: "#12494c" }}>
              <RightCircleFilled /> Area Status:
            </p>
            <div style={{ color: "#12494c" }}>{dataSource2[0]}</div>
          </>
        );
      }
    } else {
      fetchData2();
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
      title={<div style={{ color: "#12494c" }}>Guard Personal Information</div>}
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
              width: "300px",
              height: "350px",
              backgroundColor: "#EFF5F5",
            }}
          >
            {SeeIfUndfined()}
          </Card>
          <Card
            title={<div style={{ color: "#12494c" }}>Area</div>}
            style={{
              width: "250px",
              height: "350px",
              backgroundColor: "#EFF5F5",
            }}
          >
            {SeeIfUndfined2()}
          </Card>
        </Space>
      </Col>
    </Card>
  );
}
export default Guard_info;
