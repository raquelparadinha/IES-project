import React from "react";
import { Card, Col, Space, Timeline } from "antd";
const timeline_prisioner = () => {
  return (
    <Card title="Movements Timeline">
      <Space>
        <Col
          style={{
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <Card title="1-4">
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
          <Card title="5-9">
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
          <Card title="10-14">
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
