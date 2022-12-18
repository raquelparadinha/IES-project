import React from "react";
import { Card, Col, Space, Timeline } from "antd";
const timeline_prisioner = () => {
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
            style={{ backgroundColor: "#EFF5F5" }}
          >
            <Timeline mode="left" pending="Next Move" reverse={true}>
              <Timeline.Item label="4">
                Solve initial network problems
              </Timeline.Item>
              <Timeline.Item label="3">Technical testing</Timeline.Item>
              <Timeline.Item label="2">
                Network problems being solved
              </Timeline.Item>
              <Timeline.Item label="1">
                Network problems being solved
              </Timeline.Item>
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
            style={{ backgroundColor: "#EFF5F5" }}
          >
            <Timeline mode="left">
              <Timeline.Item label="5">Create a services</Timeline.Item>
              <Timeline.Item label="6">
                Solve initial network problems
              </Timeline.Item>
              <Timeline.Item label="7">Technical testing</Timeline.Item>
              <Timeline.Item label="8">
                Network problems being solved
              </Timeline.Item>
              <Timeline.Item label="9">
                Network problems being solved
              </Timeline.Item>
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
            style={{ backgroundColor: "#EFF5F5" }}
          >
            <Timeline mode="left">
              <Timeline.Item label="10">Create a services</Timeline.Item>
              <Timeline.Item label="11">
                Solve initial network problems
              </Timeline.Item>
              <Timeline.Item label="12">Technical testing</Timeline.Item>
              <Timeline.Item label="13">
                Network problems being solved
              </Timeline.Item>
              <Timeline.Item label="14">
                Network problems being solved
              </Timeline.Item>
            </Timeline>
          </Card>
        </Col>
      </Space>
    </Card>
  );
};
export default timeline_prisioner;
