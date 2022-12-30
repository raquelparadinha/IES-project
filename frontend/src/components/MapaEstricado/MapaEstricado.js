//eslint-disable-next-line
import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { areaCoords, buildingCoords, sensorCoords } from "./mapdata";
import "./mapstyle.css";

function MapaEstricado() {
  const mapRef = useRef(null);
  const width = (mapRef.current ? mapRef.current.clientWidth : 0) / 2;
  const height = (mapRef.current ? mapRef.current.clientHeight : 0) / 2;
  const scale = 3;

  const [areaData, setAreaData] = useState(areaCoords);
  const [sensorData, setSensorData] = useState(sensorCoords);
  const fetchData = () => {
    try {
      return axios.get("http://localhost:5001/api/map").then((response) => {
        const apiareasdata = response.data["areas"];
        const areasdata = { ...areaCoords };
        Object.keys(areaCoords).forEach((key) => {
          const areaname = areaCoords[key].name;
          areasdata[key] = { ...areaCoords[key], ...apiareasdata[areaname] };
        });
        setAreaData(areasdata);

        const apisensorsdata = response.data["sensors"];
        setSensorData(apisensorsdata);
        console.log(apisensorsdata);
      });
    } catch {
      console.log("failed fetching from api/map");
    }
  };
  useEffect(() => {
    const interval = setInterval(() => {
      fetchData();
    }, 1000);
    return () => clearInterval(interval);
  }, []);

  console.log(areaData);
  return (
    <svg ref={mapRef} className="map-container" preserveAspectRatio="xMidYMid meet">
      <g transform={`translate(${width}, ${height}) scale(${scale})`}>
        {areaCoords.map((_, i) => (
          <g>
            <polygon key={i} className="area" points={areaData[i].points} fill={areaData[i]["color"]} />
            <text key={i + areaCoords.length} className="area-title" x={bigX(areaData[i].points)} y={smallY(areaData[i].points)}>
              {areaData[i].text}
            </text>
          </g>
        ))}
        {sensorCoords.map((sensor, i) => (
          <polygon key={i} className="sensor" points={sensor.points} fill={sensorData[i]} />
        ))}
        {buildingCoords.map((wall, i) => (
          <g>
            <polygon key={i} className="wall" points={wall.points} />
          </g>
        ))}
      </g>
    </svg>
  );
}

export default MapaEstricado;

function bigX(points) {
  const strs = points.split(" ").filter((n, i) => {
    return i % 2 === 0;
  });
  const numbers = strs.map((s) => parseInt(s));
  return Math.min(...numbers) + 2;
}

function smallY(points) {
  const strs = points.split(" ").filter((n, i) => {
    return i % 2 !== 0;
  });
  const numbers = strs.map((s) => parseInt(s));
  return Math.max(...numbers) - 3;
}
