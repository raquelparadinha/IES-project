import { Space, Col, Row } from "antd";
import InmateNumbers from "./InmateNumbers/InmateNumbers";
import DashboardTabs from "./Tabs/tabs";

const Dashboard = () => (
  <>
    <Row>
      <Space align="start">
        <Col push={1}>
          <InmateNumbers />
        </Col>
        <Col push={1}>
          <DashboardTabs />
        </Col>
      </Space>
    </Row>
  </>
);

export default Dashboard;
