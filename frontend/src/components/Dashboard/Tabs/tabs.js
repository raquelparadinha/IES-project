import { Tabs } from "antd";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { Bar, BarChart, Brush, CartesianGrid, Legend, ReferenceLine, ResponsiveContainer, Tooltip, XAxis, YAxis } from "recharts";
import "../../../App.css";
import "./tabs.css";

const coisas = ["Heart Beat", "Stress Levels", "Glicose Levels", "Uric Acid", "Cholesterol", "Toxic Screen"];

const coisas2 = ["heartbeat", "stress", "glicose", "uricacid", "cholesterol", "toxicscreen"];
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
      return axios.get("http://localhost:5001/api/inmate/all/health/" + coisas2[props.data_] + "/data").then((response) => setDataSource(response.data));
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
    <ResponsiveContainer width="100%" height={700}>
      <BarChart
        width={1200}
        height={500}
        data={dataSource}
        margin={{
          top: 5,
          right: 30,
          left: 20,
          bottom: 5,
        }}>
        <CartesianGrid strokeDasharray="3 3" stroke="#8da6a8" />
        <XAxis dataKey="value" name="value" />
        <YAxis dataKey="qty" />
        <Tooltip />
        <Legend verticalAlign="top" wrapperStyle={{ lineHeight: "40px" }} />
        <ReferenceLine y={0} stroke="#8da6a8" />
        <Brush dataKey="value" height={30} stroke="#497174" />
        <Bar name="Inmates" dataKey="qty" fill="#497174" />
      </BarChart>
    </ResponsiveContainer>
  );
};
