import { Card, Col, Row, Space, Timeline } from "antd";
import React from "react";
import { useState } from "react";
import TheAvatar from "./Avatar/avatar";
import prisioner_info from "./prisioner_info/prisioner_info";
import timeline_prisioner from "./timeline/timeline";

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
  prisioner_info: prisioner_info(),
  timeline: timeline_prisioner(),
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
