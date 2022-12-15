import React from "react";
import { Card, Col, Row } from "antd";
import { DownOutlined } from "@ant-design/icons";
import { Dropdown, message, Space, Collapse } from "antd";
import {
  ScatterChart,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  Scatter,
} from "recharts";
const data01 = [
  {
    x: 100,
    y: 200,
  },
  {
    x: 120,
    y: 100,
  },
  {
    x: 170,
    y: 300,
  },
  {
    x: 140,
    y: 250,
  },
  {
    x: 150,
    y: 400,
  },
  {
    x: 110,
    y: 280,
  },
];

const styles = {
  card: {
    maxHeight: "100%",
    backgroundColor: "#D6E4E5",
  },
  cardBody: {
    overflow: "auto",
    maxHeight: "900px",
  },
};

const Workstations = () => (
  <>
    <Col>
      <Card
        style={styles.card}
        bodyStyle={styles.cardBody}
        title={
          <div style={{ textAlign: "center", color: "#12494c" }}>
            Workstations
          </div>
        }
      >
        <Space align="center" direction="vertical" style={{width: "100%"}}>
          <Collapse accordion>
            <Card
              type="inner"
              headStyle={{ backgroundColor: "#c2d8d8" }}
              bodyStyle={{ backgroundColor: "#eff5f5" }}
              title={<div style={{ color: "#12494c" }}>ESTRILHO</div>}
            >
              <Row>
                <Col>Velhice</Col>
                <Col style={{ alignItems: "end" }}>
                  <ScatterChart
                    width={700}
                    height={250}
                    margin={{ top: 20, right: 20, bottom: 10, left: 10 }}
                  >
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="x" name="TimeStamp" />
                    <YAxis dataKey="y" name="Quota" unit="kg" />
                    <Tooltip cursor={{ strokeDasharray: "3 3" }} />
                    <Legend />
                    <Scatter
                      name="Worker Quota per day"
                      data={data01}
                      fill="#497174"
                    />
                  </ScatterChart>
                </Col>
              </Row>
            </Card>
            <Card
              type="inner"
              headStyle={{ backgroundColor: "#c2d8d8" }}
              bodyStyle={{ backgroundColor: "#eff5f5" }}
              title={<div style={{ color: "#12494c" }}>ESTRILHO</div>}
            >
              <Row>
                <Col>Velhice</Col>
                <Col style={{ alignItems: "end" }}>
                  <ScatterChart
                    width={700}
                    height={250}
                    margin={{ top: 20, right: 20, bottom: 10, left: 10 }}
                  >
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="x" name="TimeStamp" />
                    <YAxis dataKey="y" name="Quota" unit="kg" />
                    <Tooltip cursor={{ strokeDasharray: "3 3" }} />
                    <Legend />
                    <Scatter
                      name="Worker Quota per day"
                      data={data01}
                      fill="#497174"
                    />
                  </ScatterChart>
                </Col>
              </Row>
            </Card>
            <Card
              type="inner"
              headStyle={{ backgroundColor: "#c2d8d8" }}
              bodyStyle={{ backgroundColor: "#eff5f5" }}
              title={<div style={{ color: "#12494c" }}>ESTRILHO</div>}
            >
              <Row>
                <Col>Velhice</Col>
                <Col style={{ alignItems: "end" }}>
                  <ScatterChart
                    width={700}
                    height={250}
                    margin={{ top: 20, right: 20, bottom: 10, left: 10 }}
                  >
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="x" name="TimeStamp" />
                    <YAxis dataKey="y" name="Quota" unit="kg" />
                    <Tooltip cursor={{ strokeDasharray: "3 3" }} />
                    <Legend />
                    <Scatter
                      name="Worker Quota per day"
                      data={data01}
                      fill="#497174"
                    />
                  </ScatterChart>
                </Col>
              </Row>
            </Card>
            <Card
              type="inner"
              headStyle={{ backgroundColor: "#c2d8d8" }}
              bodyStyle={{ backgroundColor: "#eff5f5" }}
              title={<div style={{ color: "#12494c" }}>ESTRILHO</div>}
            >
              <Row>
                <Col>Velhice</Col>
                <Col style={{ alignItems: "end" }}>
                  <ScatterChart
                    width={700}
                    height={250}
                    margin={{ top: 20, right: 20, bottom: 10, left: 10 }}
                  >
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="x" name="TimeStamp" />
                    <YAxis dataKey="y" name="Quota" unit="kg" />
                    <Tooltip cursor={{ strokeDasharray: "3 3" }} />
                    <Legend />
                    <Scatter
                      name="Worker Quota per day"
                      data={data01}
                      fill="#497174"
                    />
                  </ScatterChart>
                </Col>
              </Row>
            </Card>
            <Card
              type="inner"
              headStyle={{ backgroundColor: "#c2d8d8" }}
              bodyStyle={{ backgroundColor: "#eff5f5" }}
              title={<div style={{ color: "#12494c" }}>ESTRILHO</div>}
            >
              <Row>
                <Col>Velhice</Col>
                <Col style={{ alignItems: "end" }}>
                  <ScatterChart
                    width={700}
                    height={250}
                    margin={{ top: 20, right: 20, bottom: 10, left: 10 }}
                  >
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="x" name="TimeStamp" />
                    <YAxis dataKey="y" name="Quota" unit="kg" />
                    <Tooltip cursor={{ strokeDasharray: "3 3" }} />
                    <Legend />
                    <Scatter
                      name="Worker Quota per day"
                      data={data01}
                      fill="#497174"
                    />
                  </ScatterChart>
                </Col>
              </Row>
            </Card>
          </Collapse>
        </Space>
      </Card>
    </Col>
  </>
);
export default Workstations;
