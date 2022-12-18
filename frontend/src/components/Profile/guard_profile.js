import { Card, Col, Row } from "antd";
import React from "react";
import { useState } from "react";
import { useParams } from "react-router";
import TheAvatar from "./Avatar/avatar";
import guard_info from "./guard_info/guard_info";

let url_params;
const tabList = [
  {
    key: "guard_info",
    tab: "Guard Info",
  }
];

function Guard_Profile() {
  url_params = useParams();
  //console.log(url_params.id);
  const contentList = {
    guard_info: guard_info(url_params.id),
    // timeline: timeline_guard(),
  };

  const [activeTabKey1, setActiveTabKey1] = useState("guard_info");
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
        style={{ backgroundColor: "#EFF5F5" }}
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
export default Guard_Profile;
