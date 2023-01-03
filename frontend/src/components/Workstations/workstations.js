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
  const [dataSource2, setDataSource2] = useState([]);
  const [dataSource3, setDataSource3] = useState([]);
  const [dataSource4, setDataSource4] = useState([]);
  const [dataSource5, setDataSource5] = useState([]);
  const [dataSource6, setDataSource6] = useState([]);

  const [details, setDetails] = useState([]);
  const [details2, setDetails2] = useState([]);
  const [details3, setDetails3] = useState([]);
  const [details4, setDetails4] = useState([]);
  const [details5, setDetails5] = useState([]);

  const myDataSources = [
    dataSource2,
    dataSource3,
    dataSource4,
    dataSource5,
    dataSource6,
  ];
  const setMyDataSources = [
    setDataSource2,
    setDataSource3,
    setDataSource4,
    setDataSource5,
    setDataSource6,
  ];

  const myDetails = [details, details2, details3, details4, details5];
  const setMyDetails = [
    setDetails,
    setDetails2,
    setDetails3,
    setDetails4,
    setDetails5,
  ];
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

  const fetchData2 = (id) => {
    console.log(id);
    try {
      return axios
        .get("http://localhost:5001/api/workstation/" + id + "/worklogs")
        .then((response) => {
          setMyDataSources[id - 1](response.data);
          console.log(myDataSources);
        });
    } catch {
      console.log("Deu pylance");
      fetchData2(id);
    }
  };

  const fetchData3 = (id) => {
    try {
      return axios
        .get("http://localhost:5001/api/workstation/" + id + "/details")
        .then((response) => {
          console.log(id);
          setMyDetails[id - 1](response.data);
          console.log(myDetails);
        });
    } catch {
      console.log("Deu pylance");
      fetchData3(id);
    }
  };

  function SeeIfUndfined() {
    if (dataSource !== undefined) {
      return new Array(dataSource.length).fill(null).map((_, i) => {
        fetchData2(dataSource[i].id);
        fetchData3(dataSource[i].id);
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
            <Row >
              <Col style={{ width: "20%" }}>
                {console.log(myDetails[i])}
                <p style={{ marginTop: "10px" }}>
                  Total worklogs : {myDetails[i].numWorkLogs}
                </p>
                <p style={{ marginTop: "40px" }}>
                  Expected Quota : {myDetails[i].expectedQuota}
                </p>
                <p style={{ marginTop: "40px" }}>
                  Average Quota : {myDetails[i].averageQuota}
                </p>
                <p style={{ marginTop: "40px" }}>
                  Best Worker: {myDetails[i].bestWorker}
                </p>
                <p style={{ marginTop: "40px" }}>
                  Worst Worker: {myDetails[i].worstWoker}
                </p>
              </Col>
              <Col style={{ alignItems: "end", width: "80%" }}>
                <ScatterChart
                  width={800}
                  height={400}
                  margin={{ top: 20, right: 20, bottom: 10, left: 10 }}
                >
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="x" name="WorkLog" />
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
                    data={myDataSources[i]}
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
            <Collapse ghost={true} style={{ backgroundColor: "#D6E4E5" }}>
              <Space direction="vertical" size={50}>
                {SeeIfUndfined()}
              </Space>
            </Collapse>
          </Space>
        </Card>
      </Col>
    </>
  );
};
export default Workstations;
