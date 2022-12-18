import { Tabs } from "antd";
import React, { useEffect, useState } from "react";
import {
  BarChart,
  Bar,
  ReferenceLine,
  Brush,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";
import axios from "axios";
import "../../../App.css";

const coisas = [
  "Heart Beat",
  "Stress Levels",
  "Glicose Levels",
  "Uric Acid",
  "Cholesterol",
  "Toxic Screen",
];

const coisas2 = [
  "heartbeat",
  "stress",
  "glicose",
  "uricacid",
  "cholesterol",
  "toxicscreen",
];
const DashboardTabs = () => (
  <Tabs
    centered
    type="card"
    items={new Array(coisas.length).fill(null).map((_, i) => {
      return {
        label: `${coisas[i]}`,
        key: i,
        children: <Example data_={i} />,
      };
    })}
  />
);
export default DashboardTabs;

const Example = (props) => {
  const [dataSource, setDataSource] = useState();
  const fetchData = () => {
    try {
      return axios
        .get(
          "http://localhost:5001/api/inmate/all/health/" +
            coisas2[props.data_] +
            "/data"
        )
        .then((response) => setDataSource(response.data));
    } catch {
      console.log("Deu pylance");
      fetchData();
    }
  };
  useEffect(() => {
    const interval = setInterval(() => {
      fetchData();
    }, 100000);
    return () => clearInterval(interval);
  }, []);
  if (dataSource === undefined) {
    fetchData();
  }
  //console.log(dataSource);
  return (
    <BarChart
      width={1200}
      height={500}
      data={dataSource}
      margin={{
        top: 5,
        right: 30,
        left: 20,
        bottom: 5,
      }}
    >
      <CartesianGrid strokeDasharray="3 3" stroke="#8da6a8" />
      <XAxis dataKey="value" />
      <YAxis dataKey="qty" />
      <Tooltip />
      <Legend verticalAlign="top" wrapperStyle={{ lineHeight: "40px" }}   />
      <ReferenceLine y={0} stroke="#8da6a8" />
      <Brush dataKey="value" height={30} stroke="#497174" />
      <Bar dataKey="qty" fill="#497174" />
    </BarChart>
  );
};
