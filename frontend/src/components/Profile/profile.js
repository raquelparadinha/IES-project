import { Card, Col, Row, Space, Timeline } from "antd";
import React from "react";
import { useState } from "react";
import TheAvatar from "./Avatar/avatar";

const tabList = [
  {
    key: "prisioner_info",
    tab: "Prisioner Info",
  },
  {
    key: "timeline",
    tab: "Timeline",
  },
];
const contentList = {
  prisioner_info: (
    <Col
      style={{
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Space align="start">
        <Card title="Data">
          <p>Id: 12</p>
          <p>Name: PP</p>
          <p>Birthdate: 12-10-2001</p>
          <p>Entry Date: 12-10-2021</p>
          <p>Sentence End: 12-10-2031</p>
          <p>Solitary: True</p>
          <p>WorkStationId: 1234567890</p>
          <p>Health Log Id: 0987654321</p>
        </Card>
        <Card title="Heath status">
          <p>HeathBeat: 70</p>
          <p>Stress Levels: 15%</p>
          <p>Glicose Levels: 33</p>
          <p>Uric Acid: 23</p>
          <p>Cholestrol: 45</p>
          <p>Toxic Screen: 12</p>
        </Card>
      </Space>
    </Col>
  ),
  timeline: (
    <Space>
      <Col
        style={{
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Timeline mode="left">
          <Timeline.Item label="2015-09-01">Create a services</Timeline.Item>
          <Timeline.Item label="2015-09-01 09:12:11">
            Solve initial network problems
          </Timeline.Item>
          <Timeline.Item>Technical testing</Timeline.Item>
          <Timeline.Item label="2015-09-01 09:12:11">
            Network problems being solved
          </Timeline.Item>
          <Timeline.Item label="2015-09-01 09:12:11">
            Network problems being solved
          </Timeline.Item>
        </Timeline>
      </Col>
      <Col
        style={{
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Timeline mode="left">
          <Timeline.Item label="2015-09-01">Create a services</Timeline.Item>
          <Timeline.Item label="2015-09-01 09:12:11">
            Solve initial network problems
          </Timeline.Item>
          <Timeline.Item>Technical testing</Timeline.Item>
          <Timeline.Item label="2015-09-01 09:12:11">
            Network problems being solved
          </Timeline.Item>
          <Timeline.Item label="2015-09-01 09:12:11">
            Network problems being solved
          </Timeline.Item>
        </Timeline>
      </Col>
      <Col
        style={{
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Timeline mode="left">
          <Timeline.Item label="2015-09-01">Create a services</Timeline.Item>
          <Timeline.Item label="2015-09-01 09:12:11">
            Solve initial network problems
          </Timeline.Item>
          <Timeline.Item>Technical testing</Timeline.Item>
          <Timeline.Item label="2015-09-01 09:12:11">
            Network problems being solved
          </Timeline.Item>
          <Timeline.Item label="2015-09-01 09:12:11">
            Network problems being solved
          </Timeline.Item>
        </Timeline>
      </Col>
    </Space>
  ),
};
const Profile = () => {
  const [activeTabKey1, setActiveTabKey1] = useState("prisioner_info");
  const onTab1Change = (key) => {
    setActiveTabKey1(key);
  };
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "90%",
      }}
    >
      <Card
        title={
          <div
            style={{
              textAlign: "center",
            }}
          >
            <TheAvatar />
          </div>
        }
        tabList={tabList}
        tabProps={{ centered: true }}
        activeTabKey={activeTabKey1}
        onTabChange={(key) => {
          onTab1Change(key);
        }}
      >
        <Row>
          <Col style={{ textAlign: "center" }}>
            <Row></Row>
            {contentList[activeTabKey1]}
          </Col>
        </Row>
      </Card>
    </div>
  );
};
export default Profile;
