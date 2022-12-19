import React from "react";
import VectorMap, { Layer, Tooltip, Label } from "devextreme-react/vector-map";
import { roomsData, buildingData } from "./data.js";

const projection = {
    to: ([l, lt]) => [l / 100, lt / 100],
    from: ([x, y]) => [x * 100, y * 100],
  };
  
const controlBar = {
    enabled: false,
};
  
const size = {
    height: "1000",
    width: "2000                                                                                                                                                                                                    ",
}

export default function MapaEstricado() {
    return (
      <VectorMap id="vector-map" maxZoomFactor={4} size={size} projection={projection} controlBar={controlBar}>
        <Layer dataSource={buildingData} hoverEnabled={false} name="building"></Layer>
        
        <Layer dataSource={roomsData} name="rooms" borderWidth={1} color="transparent">
          <Label enabled={true} dataField="name"></Label>
        </Layer>
        
        <Tooltip enabled={true} customizeTooltip={customizeTooltip}></Tooltip>
      </VectorMap>
    );
}

function customizeTooltip(arg) {
    if (arg.layer.name === "rooms") {
      if (arg.attribute("name").startsWith("Sensor")) {
        return { text: `Sensor ${arg.attribute("id")}\nLocked: ${arg.attribute("access")}` };
      } else {
        return { text: `Area ${arg.attribute("id")}\nInmates: ${arg.attribute("inmates")}` };
      }
    }
    return null;
}
