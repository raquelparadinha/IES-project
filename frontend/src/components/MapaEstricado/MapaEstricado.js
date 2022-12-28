//eslint-disable-next-line
import { useEffect, useRef, useState } from "react";
import { areaData, buildingData, sensorData } from "./mapdata";

import "./mapstyle.css";

const MapaEstricado = () => {
  const mapRef = useRef(null);
  const width = (mapRef.current ? mapRef.current.clientWidth : 0) / 2;
  const height = (mapRef.current ? mapRef.current.clientHeight : 0) / 2;
  const scale = 3;

  return (
    <svg ref={mapRef} className="map-container" preserveAspectRatio="xMidYMid meet">
      <g transform={`translate(${width}, ${height - 50}) scale(${scale}) rotate(180)`}>
        {buildingData.map((wall, i) => (
          <polygon key={i} points={wall[i].points} stroke="black" strokeWidth={0.1} />
        ))}
        {areaData.map((area, i) => (
          <g>
            <polygon className="area" key={i} points={area[i].points} />
            <text></text>
          </g>
        ))}
        {sensorData.map((sensor, i) => (
          <polygon className="sensor" key={i} points={sensor[i].points} />
        ))}
      </g>
    </svg>
  );
};

export default MapaEstricado;
