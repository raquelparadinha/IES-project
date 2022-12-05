import { Card, Col, Space } from "antd";

const InmateNumbers = () => (
  <>
    <Space>
      <Col>
        <br />
        <div style={{textAlign: "center"}}>Inmate Numbers: 10</div>
        <br />
        <Card title="Areas" style={{textAlign: "center", alignItems: "center"}}>
          <p>Entrance: 12 / 100</p>
          <p>Visitors Wing: 12 / 100</p>
          <p>Staff Wing: 12 / 100</p>
          <p>Job Wing: 12 / 100</p>
          <p>Patio: 12 / 100</p>
          <p>Infirmary: 12 / 100</p>
          <p>Cell Block 1: 12 / 100</p>
          <p>Cell Block 2: 12 / 100</p>
          <p>Showers: 12 / 100</p>
          <p>Solitary: 12 / 100</p>
          <p>Canteen: 12 / 100</p>
        </Card>
      </Col>
    </Space>
  </>
);

export default InmateNumbers;
