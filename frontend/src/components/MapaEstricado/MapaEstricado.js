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
  const fetchData = () => {
    try {
      return axios.get("http://localhost:5001/api/map").then((response) => {
        const colors = response.data["areas"];
        const data = { ...areaCoords };
        Object.keys(areaCoords).forEach((key) => {
          // ganza nas keys, my bad mas tou estiado
          const areaname = areaCoords[key][key].name;
          data[key] = { ...areaCoords[key][key], ...colors[areaname] };
        });
        setAreaData(data);
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
    <svg ref={mapRef} className="map-container" viewBox="0 0 " preserveAspectRatio="xMidYMid meet">
      <g transform={`translate(${width}, ${height - 50}) scale(${scale}) rotate(180)`}>
        {buildingCoords.map((wall, i) => (
          <polygon key={i} className="wall" points={wall[i].points} />
        ))}
        {areaCoords.map((_, i) => {
          console.log(areaData[i].color);
          return <polygon key={i} className="area" points={areaData[i].points} fill={areaData[i]["color"]} />;
        })}
        {sensorCoords.map((sensor, i) => (
          <polygon key={i} className="sensor" points={sensor[i].points} />
        ))}
      </g>
    </svg>
  );
}

export default MapaEstricado;
