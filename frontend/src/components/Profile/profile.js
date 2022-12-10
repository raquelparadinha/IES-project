import { Card, Col, Row } from "antd";
import React from "react";
import { useState } from "react";
import { useParams } from "react-router";
import TheAvatar from "./Avatar/avatar";
import prisioner_info from "./prisioner_info/prisioner_info";
import timeline_prisioner from "./timeline/timeline";

let url_params;
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

function Profile() {
  url_params = useParams();
  console.log(url_params.id);
  const contentList = {
    prisioner_info: prisioner_info(url_params.id),
    timeline: timeline_prisioner(),
  };

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
}
export default Profile;
