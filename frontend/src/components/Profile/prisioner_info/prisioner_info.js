import React from "react";
import { Card, Col, Space } from "antd";

const prisioner_info = (id) => {
  console.log("Aqui " + id);
  const title_ = "Prisioner " + id + " Data";
  return (
    <Card title="Prisioner Personal Information">
      <Col
        style={{
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Space align="start">
          <Card title={title_} style={{ width: "250px" }}>
            <p>Id: 12</p>
            <p>Name: Paulo Pinto</p>
            <p>Birthdate: 12-10-2001</p>
            <p>Entry Date: 12-10-2021</p>
            <p>Sentence End: 12-10-2031</p>
            <p>Solitary: True</p>
            <p>WorkStationId: 1234567890</p>
            <p>Health Log Id: 0987654321</p>
          </Card>
          <Card title="Health status" style={{ width: "250px" }}>
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
};
export default prisioner_info;
