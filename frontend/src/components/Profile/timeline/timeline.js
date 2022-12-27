import React, { useEffect, useState } from "react";
import { Card, Col, Space, Timeline } from "antd";
import axios from "axios";
import { LoadingOutlined } from "@ant-design/icons";
function Timeline_prisioner(id) {
  const [dataSource, setDataSource] = useState();
  let data1 = [];
  let data2 = [];
  let data3 = [];
  const fetchData = () => {
    console.log(dataSource);
    try {
      return axios
        .get("http://localhost:5001/api/inmate/" + id + "/moves")
        .then((response) => setDataSource(response.data));
    } catch {
      console.log("Deu pylance");
      fetchData();
    }
  };
  useEffect(() => {
    const interval = setInterval(() => {
      fetchData();
    }, 100);
    return () => clearInterval(interval);
  });
  if (dataSource !== undefined) {
    console.log(dataSource[0]);
    data1 = dataSource.slice(0, 4);
    if (dataSource.length > 4) {
      data2 = dataSource.slice(4, 9);
    }
    if (dataSource.length > 8) {
      data3 = dataSource.slice(9, 14);
    }
    console.log("data1:" + data1);
    console.log("data2:" + data2);
    console.log("data3:" + data3);
    let val = 5;
    let val2 = 4;
    return (
      <Card
        title={<div style={{ color: "#12494c" }}>Movements Timeline</div>}
        style={{ backgroundColor: "#D6E4E5" }}
      >
        <Space>
          <Col
            style={{
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <Card
              title={<div style={{ color: "#12494c" }}>1-4</div>}
              style={{
                backgroundColor: "#EFF5F5",
                width: "230px",
                height: "400px",
              }}
            >
              <Timeline mode="left" pending="Next Move" reverse={true}>
                {data1.map((zone) => {
                  val -= 1;
                  return (
                    <Timeline.Item label={val}>Was at: {zone}</Timeline.Item>
                  );
                })}
              </Timeline>
            </Card>
          </Col>
          <Col
            style={{
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <Card
              title={<div style={{ color: "#12494c" }}>5-9</div>}
              style={{
                backgroundColor: "#EFF5F5",
                width: "230px",
                height: "400px",
              }}
            >
              <Timeline mode="left">
                {data2.map((zone) => {
                  val2 += 1;
                  return (
                    <Timeline.Item label={val2}>Was at: {zone}</Timeline.Item>
                  );
                })}
              </Timeline>
            </Card>
          </Col>
          <Col
            style={{
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <Card
              title={<div style={{ color: "#12494c" }}>10-14</div>}
              style={{
                backgroundColor: "#EFF5F5",
                width: "230px",
                height: "400px",
              }}
            >
              <Timeline mode="left">
                {data3.map((zone) => {
                  val2 += 1;
                  return (
                    <Timeline.Item label={val2}>Was at: {zone}</Timeline.Item>
                  );
                })}
              </Timeline>
            </Card>
          </Col>
        </Space>
      </Card>
    );
  } else {
    return (
      <div>
        <LoadingOutlined />
      </div>
    );
  }
}
export default Timeline_prisioner;
