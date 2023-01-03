import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button, Card, Col } from "antd";
import {
  DownOutlined,
  LoadingOutlined,
  ToolFilled,
  HeartFilled,
  AlertFilled,
} from "@ant-design/icons";
import { Dropdown, Space, Pagination } from "antd";
import axios from "axios";

export const icons = {
  health: <HeartFilled />,
  work: <ToolFilled />,
  riot: <AlertFilled />,
};

export const back_colors = {
  health: "#C3D8C2",
  work: "#DBDB9E",
  riot: "#D8C3C2",
};

export const colors = {
  health: "#134C12",
  work: "#757717",
  riot: "#771B17",
};

function Notifications() {
  const navigate = useNavigate();
  const [dataSource, setDataSource] = useState();
  const [currentPage, setCurrentPage] = useState(1);
  const [cardsPerPage] = useState(6);

  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  const fetchData = () => {
    //console.log(dataSource);
    try {
      return axios
        .get("http://localhost:5001/api/alert")
        .then((res) => {
          return res;
        })
        .then((res) => {
          return res;
        })
        .then((response) => setDataSource(response.data));
    } catch {
      console.log("Deu pylance");
      fetchData();
    }
  };
  useEffect(() => {
    const interval = setInterval(() => {
      fetchData();
    }, 100);
    return () => clearInterval(interval);
  });

  fetchData();
  //console.log(dataSource);
  const indexOfLastCard = currentPage * cardsPerPage;
  const indexOfFirstCard = indexOfLastCard - cardsPerPage;
  let currentCards;

  function Pagination_on() {
    if (dataSource.length > 0) {
      return (
        <Pagination
          current={currentPage}
          onChange={handlePageChange}
          total={dataSource.length}
          pageSize={cardsPerPage}
        />
      );
    } else {
      return "No Data Available";
    }
  }

  function Go_to_Profile(id) {
    console.log(id);
    if (id !== undefined) {
      navigate("/prisioners/" + id);
    }
  }

  function Alert_type(card, id) {
    console.log(id);
    if (card.type === "health") {
      const items = card.symptoms.map((symptom) => ({
        key: symptom,
        label: symptom,
      }));

      return (
        <Space>
          <Button
            onClick={() => Go_to_Profile(id)}
            type="text"
            style={{ color: colors[`${card.type}`] }}
          >
            Profile
          </Button>
          <Dropdown menu={{ items }}>
            <a style={{ color: colors[`${card.type}`] }}>
              <Space>
                Symptoms
                <DownOutlined />
              </Space>
            </a>
          </Dropdown>
        </Space>
      );
    } else if (card.type === "work") {
      return (
        <Button
          onClick={() => Go_to_Profile(id)}
          type="text"
          style={{ color: colors[`${card.type}`] }}
        >
          Profile
        </Button>
      );
    }
  }

  if (dataSource !== undefined) {
    //console.log(dataSource);
    currentCards = dataSource.slice(indexOfFirstCard, indexOfLastCard);

    return (
      <>
        <Col>
          <Card
            title={
              <div style={{ textAlign: "center", color: "#12494c" }}>
                Notifications
              </div>
            }
            bodyStyle={{
              display: "flex",
              justifyContent: "center",
            }}
            style={{ backgroundColor: "#D6E4E5" }}
          >
            <Space direction="vertical">
              {currentCards.map((card) => {
                //console.log(card["InmateID"]);
                return (
                  <Card
                    //
                    headStyle={{
                      backgroundColor: back_colors[`${card["Alert"].type}`],
                    }}
                    bodyStyle={{ backgroundColor: "#eff5f5" }}
                    style={{
                      marginTop: 16,
                      width: "1000px",
                    }}
                    type="inner"
                    title={
                      <div style={{ color: colors[`${card["Alert"].type}`] }}>
                        {icons[`${card["Alert"].type}`]}{" "}
                        {card["Alert"].type.charAt(0).toUpperCase() +
                          card["Alert"].type.slice(1)}
                      </div>
                    }
                    extra={Alert_type(card["Alert"], card["InmateID"])}
                  >
                    {card["Alert"].information}
                  </Card>
                );
              })}
              {Pagination_on()}
            </Space>
          </Card>
        </Col>
      </>
    );
  } else {
    fetchData();
    return (
      <div>
        <LoadingOutlined />
      </div>
    );
  }
}
export default Notifications;