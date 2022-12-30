import { Card, Col } from "antd";
import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { areaCoords, buildingCoords, sensorCoords } from "./mapdata";
import "./mapstyle.css";

function MapaEstricado() {
  const mapRef = useRef(null);
  const width = (mapRef.current ? mapRef.current.clientWidth : 0) / 2;
  const height = (mapRef.current ? mapRef.current.clientHeight : 0) / 2;
  const scale = 3;

  const [areaColors, setAreaColors] = useState(areaCoords);
  const [sensorColors, setSensorColors] = useState(sensorCoords);
  const fetchMapData = () => {
    try {
      return axios.get("http://localhost:5001/api/map").then((response) => {
        const apiareasdata = response.data["areas"];
        const areasdata = { ...areaCoords };
        Object.keys(areaCoords).forEach((key) => {
          const areaname = areaCoords[key].name;
          areasdata[key] = { ...areaCoords[key], ...apiareasdata[areaname] };
        });
        setAreaColors(areasdata);

        const apisensorsdata = response.data["sensors"];
        setSensorColors(apisensorsdata);
      });
    } catch {
      console.log("failed fetching from api/map");
    }
  };
  const [areaData, setAreaData] = useState(areaCoords);
  const fetchAreaData = () => {
    try {
      return axios.get("http://localhost:5001/api/area").then((response) => {
        const apidata = response.data;
        apidata.forEach((area, i) => {
          apidata[i]["countInmates"] = area.currentInmateIds.length;
        });
        setAreaData(apidata);
      });
    } catch {
      console.log("Deu pylance");
    }
  };
  const [sensorData, setSensorData] = useState(sensorCoords);
  const fetchSensorData = () => {
    try {
      return axios.get("http://localhost:5001/api/sensor").then((response) => {
        const apidata = response.data;
        apidata.forEach((sensor, i) => {
          apidata[i]["entryArea"] = areaCoords[sensor["entryAreaId"] - 1].text;
          apidata[i]["exitArea"] = areaCoords[sensor["exitAreaId"] - 1].text;
        });
        setSensorData(apidata);
      });
    } catch {
      console.log("Deu pylance");
    }
  };

  useEffect(() => {
    const interval = setInterval(() => {
      fetchMapData();
      fetchAreaData();
      fetchSensorData();
    }, 1000);
    return () => clearInterval(interval);
  }, []);

  const [selectedArea, setSelectedArea] = useState(0);
  function selectArea(idx) {
    setSelectedArea(idx);
  }

  const [selectedSensor, setSelectedSensor] = useState(0);
  function selectSensor(idx) {
    setSelectedSensor(idx);
  }

  console.log(sensorData);
  return (
    <>
      <svg ref={mapRef} className="map-container" preserveAspectRatio="xMidYMid meet">
        <g transform={`translate(${width}, ${height}) scale(${scale})`}>
          {areaCoords.map((_, i) => (
            <g>
              <polygon key={i} className={selectedArea === i ? "area-selected" : "area"} points={areaColors[i].points} onClick={() => selectArea(i)} fill={areaColors[i]["color"]} />
              <text key={i + areaCoords.length} className="area-title" x={bigX(areaColors[i].points)} y={smallY(areaColors[i].points)}>
                {areaColors[i].text}
              </text>
            </g>
          ))}
          {buildingCoords.map((wall, i) => (
            <g>
              <polygon key={i} className="wall" points={wall.points} />
            </g>
          ))}
          {sensorCoords.map((sensor, i) => (
            <polygon key={i} className={selectedSensor === i ? "sensor-selected" : "sensor"} points={sensor.points} onClick={() => selectSensor(i)} fill={sensorColors[i]} />
          ))}
        </g>
      </svg>
      <Col className="cards">
        <Card className="area-card" title={areaColors[selectedArea].text}>
          <p>Inmate Count: {areaData[selectedArea].countInmates}</p>
          <p>Capacity: {areaData[selectedArea].capacity}</p>
          <p>Access: {areaData[selectedArea].access ? "Yes" : "No"}</p>
          <button onClick={() => lockArea(selectedArea)}>Lock Area</button>
          <button onClick={() => unlockArea(selectedArea)}>Unlock Area</button>
        </Card>
        <Card className="sensor-card" title={sensorCoords[selectedSensor].name}>
          <p>From: {sensorData[selectedSensor].entryArea}</p>
          <p>To: {sensorData[selectedSensor].exitArea}</p>
          <p>State: {sensorData[selectedSensor].active ? "Unlocked" : "Locked"}</p>
          <button onClick={() => lockSensor(selectedSensor)}>Lock Sensor</button>
          <button onClick={() => unlockSensor(selectedSensor)}>Unlock Sensor</button>
        </Card>
      </Col>
    </>
  );
}

export default MapaEstricado;

// text positioning
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

// api posts for datagen control
function lockArea(idx) {
  console.log(idx);
  const url = "http://localhost:5001/api/area/" + (idx + 1) + "/lock";
  axios.post(url).then((response) => {
    console.log(response);
  });
}

function unlockArea(idx) {
  console.log(idx);
  const url = "http://localhost:5001/api/area/" + (idx + 1) + "/unlock";
  axios.post(url).then((response) => {
    console.log(response);
  });
}

function lockSensor(idx) {
  console.log(idx);
  const url = "http://localhost:5001/api/sensor/" + (idx + 1) + "/lock";
  axios.post(url).then((response) => {
    console.log(response);
  });
}

function unlockSensor(idx) {
  console.log(idx);
  const url = "http://localhost:5001/api/sensor/" + (idx + 1) + "/unlock";
  axios.post(url).then((response) => {
    console.log(response);
  });
}
