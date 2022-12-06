import React from "react";
import { InfoCircleOutlined } from "@ant-design/icons";
import { Timeline } from "antd";
import { useNavigate } from "react-router";

const Navegar = () => {
    const navigate = useNavigate()
    navigate("/dashboard")
}


const Notifications = () => (
  <>
    <br />
    <Timeline pending={true}>
      <Timeline.Item 
        label="2015-09-01"
        dot={<InfoCircleOutlined color="red" /> }
      >
        Create a services
      </Timeline.Item>
      <Timeline.Item label="2015-09-01 09:12:11">
        Solve initial network problems
      </Timeline.Item>
      <Timeline.Item>Technical testing</Timeline.Item>
      <Timeline.Item label="2015-09-01 09:12:11">
        Network problems being solved
      </Timeline.Item>
    </Timeline>
  </>
);
export default Notifications;

