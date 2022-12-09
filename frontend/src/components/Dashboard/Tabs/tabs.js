import { Tabs } from "antd";
import React from "react";
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

const coisas = [
  "Heart Beat",
  "Stress Levels",
  "Glicose Levels",
  "Uric Acid",
  "Cholesterol",
  "Toxic Screen",
];
const DashboardTabs = () => (
  <Tabs
    centered
    type="card"
    items={new Array(coisas.length).fill(null).map((_, i) => {
      return {
        label: `${coisas[i]}`,
        key: i,
        children: <Example />,
      };
    })}
  />
);
export default DashboardTabs;

const data = [
  {
    name: 60,
    qty: 10,
  },
  {
    name: 65,
    qty: 5,
  },
  {
    name: 70,
    qty: 7,
  },
  {
    name: 75,
    qty: 3,
  },
  {
    name: 80,
    qty: 5,
  },
  {
    name: 85,
    qty: 9,
  },
  {
    name: 85,
    qty: 2,
  },
  {
    name: 90,
    qty: 8,
  },
  {
    name: 95,
    qty: 1,
  },
];

const Example = () => (
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
    <XAxis dataKey="name" />
    <YAxis />
    <Tooltip />
    <Legend verticalAlign="top" wrapperStyle={{ lineHeight: "40px" }} />
    <ReferenceLine y={0} stroke="#000" />
    <Brush dataKey="name" height={30} stroke="#8884d8" />
    <Bar dataKey="qty" fill="#82ca9d" />
  </BarChart>
);
