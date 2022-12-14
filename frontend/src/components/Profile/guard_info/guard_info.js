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
        .get("http://localhost:5001/api/guard/" + id + "/sidebar")
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
          <p>Id: {dataSource.id}</p>
          <p>AreaID: {dataSource.areaId}</p>
          <p>Birthdate: {dataSource.birthdate}</p>
          <p>Phone: {dataSource.phone}</p>
          <p>Email: {dataSource.email}</p>
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
      return (
        <>
          <p style={{ textAlign: "left" }}>
            <RightCircleFilled /> Area Guards:
          </p>
          {new Array(dataSource2.length).fill(null).map((_, i) => {
            //  colocar i+1 no lugar do i pois o i = 0 vai ser sobre o estado da area e não é para ser usado aqui
            return <p>{dataSource2[i + 1]}</p>;
          })}
          <p style={{ textAlign: "left" }}>
            <RightCircleFilled /> Area Status:
          </p>
          {dataSource2[0]}
        </>
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

  let title_;
  if (dataSource !== undefined) {
    title_ = dataSource.name;
  } else {
    title_ = <LoadingOutlined />;
  }
  return (
    <Card title="Guard Personal Information">
      <Col
        style={{
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Space align="start">
          <Card title={title_} style={{ width: "300px", height: "300px" }}>
            {SeeIfUndfined()}
          </Card>
          <Card title="Area" style={{ width: "250px", height: "300px" }}>
            {SeeIfUndfined2()}
          </Card>
        </Space>
      </Col>
    </Card>
  );
}
export default Guard_info;
