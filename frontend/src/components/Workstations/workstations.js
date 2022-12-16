import React, { useState } from "react";
import { Card, Col, Row } from "antd";
import { LoadingOutlined } from "@ant-design/icons";
import { Space, Collapse } from "antd";
import axios from "axios";
import {
  ScatterChart,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  Scatter,
  ReferenceLine,
  Label,
} from "recharts";
const data01 = [
  {
    x: "12/16/2022",
    y: 20,
  },
  {
    x: "12/17/2022",
    y: 10,
  },
  {
    x: "12/18/2022",
    y: 30,
  },
  {
    x: "12/19/2022",
    y: 25,
  },
  {
    x: "12/20/2022",
    y: 40,
  },
  {
    x: "12/21/2022",
    y: 28,
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

const Workstations = () => {
  const [dataSource, setDataSource] = useState();
  const fetchData = () => {
    try {
      return axios
        .get("http://localhost:5001/api/workstation")
        .then((response) => setDataSource(response.data));
    } catch {
      console.log("Deu pylance");
      fetchData();
    }
  };

  function SeeIfUndfined() {
    if (dataSource !== undefined) {
      console.log(dataSource);
      return new Array(dataSource.length).fill(null).map((_, i) => {
        console.log(dataSource[i]);
        return (
          <Card
            type="inner"
            headStyle={{ backgroundColor: "#c2d8d8", textAlign: "center" }}
            bodyStyle={{ backgroundColor: "#eff5f5" }}
            title={
              <div style={{ color: "#12494c" }}>
                {dataSource[i].description}
              </div>
            }
          >
            <Row>
              <Col>
                <p style={{ marginTop: "10px" }}>
                  Expected Quota : {dataSource[i].expectedQuota}
                </p>
                <p style={{ marginTop: "40px" }}>
                  Expected Quota : {dataSource[i].expectedQuota}
                </p>
                <p style={{ marginTop: "40px" }}>
                  Expected Quota : {dataSource[i].expectedQuota}
                </p>
              </Col>
              <Col style={{ alignItems: "end" }}>
                <ScatterChart
                  width={1000}
                  height={300}
                  margin={{ top: 20, right: 20, bottom: 10, left: 10 }}
                >
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="x" name="TimeStamp" />
                  <YAxis dataKey="y" name="Quota" />
                  <Tooltip cursor={{ strokeDasharray: "3 3" }} />
                  <ReferenceLine
                    y={dataSource[i].expectedQuota}
                    stroke="#EB6440"
                    strokeDasharray="3 3"
                    children={
                      <Label value="Expected" position="top" stroke="#EB6440" />
                    }
                  />
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
        );
      });
    } else {
      fetchData();
      return (
        <div>
          <LoadingOutlined />
        </div>
      );
    }
  }
  return (
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
          <Space align="center" direction="vertical" style={{ width: "100%" }}>
            <Collapse accordion>
              {SeeIfUndfined()}
              {/* <Card
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
              </Card> */}
            </Collapse>
          </Space>
        </Card>
      </Col>
    </>
  );
};
export default Workstations;
