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
  "Cholesterol",
  "toxicscreen",
];
const DashboardTabs = () => (
  <Tabs
    centered
    type="card"
    items={new Array(coisas.length).fill(null).map((_, i) => {
      console.log(i);
      return {
        label: `${coisas[i]}`,
        key: i,
        children: <Example data_={i} />,
      };
    })}
  />
);
export default DashboardTabs;

const data = [
  {
    number: 60,
    qty: 10,
  },
  {
    number: 65,
    qty: 5,
  },
  {
    number: 70,
    qty: 7,
  },
  {
    number: 75,
    qty: 3,
  },
  {
    number: 80,
    qty: 5,
  },
  {
    number: 85,
    qty: 9,
  },
  {
    number: 85,
    qty: 2,
  },
  {
    number: 90,
    qty: 8,
  },
  {
    number: 95,
    qty: 1,
  },
];

const Example = (props) => {
  console.log(props);
  const [dataSource, setDataSource] = useState();
  const fetchData = () => {
    try {
      return axios
        .get(
          "http://localhost:5001/api/inmate/health/" +
            coisas2[props.data_] +
            "/data"
        )
        .then((response) => setDataSource(response.data));
    } catch {
      console.log("Deu pylance");
    }
  };
  console.log(dataSource);
  useEffect(() => {
    const interval = setInterval(() => {
      fetchData();
    }, 1000);
    return () => clearInterval(interval);
  }, []);
  console.log(dataSource);
  return (
    <BarChart
      width={1200}
      height={500}
      data={data}
      margin={{
        top: 5,
        right: 30,
        left: 20,
        bottom: 5,
      }}
    >
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="type" />
      <YAxis />
      <Tooltip />
      <Legend verticalAlign="top" wrapperStyle={{ lineHeight: "40px" }} />
      <ReferenceLine y={0} stroke="#000" />
      <Brush dataKey="name" height={30} stroke="#8884d8" />
      <Bar dataKey="qty" fill="#82ca9d" />
    </BarChart>
  );
};
