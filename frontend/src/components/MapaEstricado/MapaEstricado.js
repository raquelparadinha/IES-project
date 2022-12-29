import React from "react";
import VectorMap, { Layer, Tooltip, Label } from "devextreme-react/vector-map";
import { roomsData, buildingData } from "./data.js";
import axios from "axios";
import { useState, useEffect } from "react";
import { LoadingOutlined } from "@ant-design/icons";

const projection = {
  to: ([l, lt]) => [l / 100, lt / 100],
  from: ([x, y]) => [x * 100, y * 100],
};

const controlBar = {
  enabled: false,
};

const size = {
  height: "1000",
  width:
    "2000                                                                                                                                                                                                    ",
};

export default function MapaEstricado() {
  const [dataSource, setDataSource] = useState();
  const fetchData = () => {
    try {
      return axios
        .get("http://localhost:5001/api/area")
        .then((res) => {
          return res;
        })
        .then((response) => setDataSource(response.data));
    } catch {
      console.log("Deu pylance");
    }
  };
  useEffect(() => {
    const interval = setInterval(() => {
      fetchData();
    }, 1000);
    return () => clearInterval(interval);
  }, []);

  function SeeIfUndfined() {
    console.log(dataSource);
    if (dataSource !== undefined) {
      return dataSource.map((zone) => (
        <>
          <p style={{ color: "#12494c" }}>
            {zone.name}: {zone.currentInmateIds.length} / {zone.capacity} |
            Acess: {zone.access.toString()}
          </p>
        </>
      ));
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
    <VectorMap
      id="vector-map"
      maxZoomFactor={4}
      size={size}
      projection={projection}
      controlBar={controlBar}
    >
      <Layer
        dataSource={buildingData}
        hoverEnabled={false}
        name="building"
      ></Layer>

      <Layer
        dataSource={roomsData}
        name="rooms"
        borderWidth={1}
        color="transparent"
      >
        <Label enabled={true} dataField="name"></Label>
      </Layer>

      <Tooltip enabled={true} customizeTooltip={customizeTooltip}></Tooltip>
    </VectorMap>
  );
}

function customizeTooltip(arg) {
  if (arg.layer.name === "rooms") {
    if (arg.attribute("name").startsWith("Sensor")) {
      return { text: `Sensor ${arg.attribute("id")}` };
    }
  }
  return null;
}
