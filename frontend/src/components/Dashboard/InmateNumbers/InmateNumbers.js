import { Card, Space } from "antd";

const InmateNumbers = () => (
  <div
    style={{
      display: "flex",
      flexDirection: "collumn",
      alignItems: "center",
    }}
  >
    <Space>
      <div>Inmate Numbers: 10</div> <br />
      <Card></Card>
    </Space>
  </div>
);

export default InmateNumbers;
