import React, { useEffect, useState } from "react";
import { Card, Col, Space } from "antd";
import axios from "axios";
import {
  LoadingOutlined,
  RightCircleFilled,
} from "@ant-design/icons/lib/icons";

function Guard_info(id) {
  const [dataSource, setDataSource] = useState();
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
            <p style={{ textAlign: "left" }}>
              <RightCircleFilled /> Area Guards:
            </p>
            <p>Zé Alberto</p>
            <p>Ana Maria</p>
            <p>Manco das ajudas</p>
            <p style={{ textAlign: "left" }}>
              <RightCircleFilled /> Area Status:
            </p>
            <p>Open</p>
          </Card>
        </Space>
      </Col>
    </Card>
  );
}
export default Guard_info;
