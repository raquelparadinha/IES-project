import { Button, Card, Col, ConfigProvider } from "antd";
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

  const [areaDetails, setAreaDetails] = useState(areaCoords[0]);
  const fetchAreaDetails = () => {
    try {
      return axios.get("http://localhost:5001/api/area/" + (selectedArea + 1) + "/details").then((response) => {
        const apidata = response.data;
        apidata["guardstr"] = apidata.guards.join(", ");
        setAreaDetails(apidata);
      });
    } catch {
      console.log("Deu pylance");
    }
  };
  useEffect(() => {
    fetchAreaDetails();
  }, [selectedArea]);

  const [selectedSensor, setSelectedSensor] = useState(0);
  function selectSensor(idx) {
    setSelectedSensor(idx);
  }

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
      <ConfigProvider theme={{ token: { colorPrimary: "#12494c" } }}>
        <Col className="cards">
          <Card className="area-card" title={areaColors[selectedArea].text}>
            <div className="lock-btns-container">
              <Button className="lock-btn" onClick={() => lockArea(selectedArea)}>
                Lock Area
              </Button>
              <Button className="lock-btn" onClick={() => unlockArea(selectedArea)}>
                Unlock Area
              </Button>
            </div>
            <table className="card-content">
              <col width="100px"></col>
              <col></col>
              <tr>
                <td className="label">Access</td>
                <td className="data">{areaData[selectedArea].access ? "Yes" : "No"}</td>
              </tr>
              <tr>
                <td className="label">Danger Level</td>
                <td className="data">{areaDetails.currentDangerLevel}</td>
              </tr>
              <tr>
                <td className="label">Inmate Count</td>
                <td className="data">{areaData[selectedArea].countInmates}</td>
              </tr>
              <tr>
                <td className="label">Capacity</td>
                <td className="data">{areaData[selectedArea].capacity}</td>
              </tr>
              <tr>
                <td className="label">Guards</td>
                <td className="data">{areaDetails.guardstr}</td>
              </tr>
            </table>
          </Card>
          <Card className="sensor-card" title={sensorCoords[selectedSensor].name}>
            <div className="lock-btns-container">
              <Button className="lock-btn" onClick={() => lockSensor(selectedSensor)}>
                Lock Sensor
              </Button>
              <Button className="lock-btn" onClick={() => unlockSensor(selectedSensor)}>
                Unlock Sensor
              </Button>
            </div>
            <table className="card-content">
              <col width="80px"></col>
              <col></col>
              <tr>
                <td className="label">State</td>
                <td className="data">{sensorData[selectedSensor].active ? "Unlocked" : "Locked"}</td>
              </tr>
              <tr>
                <td className="label">From</td>
                <td className="data">{sensorData[selectedSensor].entryArea}</td>
              </tr>
              <tr>
                <td className="label">To</td>
                <td className="data">{sensorData[selectedSensor].exitArea}</td>
              </tr>
            </table>
          </Card>
        </Col>
      </ConfigProvider>
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
  return Math.min(...numbers) + 3;
}

function smallY(points) {
  const strs = points.split(" ").filter((n, i) => {
    return i % 2 !== 0;
  });
  const numbers = strs.map((s) => parseInt(s));
  return Math.max(...numbers) - 4;
}

// api posts for datagen control
function lockArea(idx) {
  console.log(idx);
  const url = "http://localhost:5001/api/area/" + (idx + 1) + "/lock";
  axios.get(url).then((response) => {
    console.log(response);
  });
}

function unlockArea(idx) {
  console.log(idx);
  const url = "http://localhost:5001/api/area/" + (idx + 1) + "/unlock";
  axios.get(url).then((response) => {
    console.log(response);
  });
}

function lockSensor(idx) {
  console.log(idx);
  const url = "http://localhost:5001/api/sensor/" + (idx + 1) + "/lock";
  axios.get(url).then((response) => {
    console.log(response);
  });
}

function unlockSensor(idx) {
  console.log(idx);
  const url = "http://localhost:5001/api/sensor/" + (idx + 1) + "/unlock";
  axios.get(url).then((response) => {
    console.log(response);
  });
}
