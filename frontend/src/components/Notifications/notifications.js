import React, { useEffect, useState } from "react";
import { Card, Col } from "antd";
import {
  DownOutlined,
  LoadingOutlined,
  ToolFilled,
  HeartFilled,
  AlertFilled,
} from "@ant-design/icons";
import { Dropdown, message, Space, Pagination } from "antd";
import axios from "axios";

const icons = {
  health: <HeartFilled />,
  work: <ToolFilled />,
  riot: <AlertFilled />,
};

const back_colors = {
  health: "#C3D8C2",
  work: "#DBDB9E",
  riot: "#D8C3C2",
};

const colors = {
  health: "#134C12",
  work: "#757717",
  riot: "#771B17",
};

function Notifications() {
  const [dataSource, setDataSource] = useState();
  const [currentPage, setCurrentPage] = useState(1);
  const [cardsPerPage] = useState(6);

  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  const fetchData = () => {
    console.log(dataSource);
    try {
      return axios
        .get("http://localhost:5001/api/alert")
        .then((response) => setDataSource(response.data));
    } catch {
      console.log("Deu pylance");
      fetchData();
    }
  };
  useEffect(() => {
    const interval = setInterval(() => {
      fetchData();
    }, 1000);
    return () => clearInterval(interval);
  });

  fetchData();
  console.log(dataSource);
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

  function Alert_type(card) {
    if (card.type === "health") {
      const items = card.symptoms.map((symptom) => ({
        key: symptom,
        label: symptom,
      }));

      return (
        <Dropdown menu={{ items }}>
          <a
            onClick={(e) => e.preventDefault()}
            style={{ color: colors[`${card.type}`] }}
          >
            <Space>
              Symptoms
              <DownOutlined />
            </Space>
          </a>
        </Dropdown>
      );
    }
  }

  if (dataSource !== undefined) {
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
                return (
                  <Card
                    //
                    headStyle={{ backgroundColor: back_colors[`${card.type}`] }}
                    bodyStyle={{ backgroundColor: "#eff5f5" }}
                    style={{
                      marginTop: 16,
                      width: "1000px",
                    }}
                    type="inner"
                    title={
                      <div style={{ color: colors[`${card.type}`] }}>
                        {icons[`${card.type}`]}{" "}
                        {card.type.charAt(0).toUpperCase() + card.type.slice(1)}
                      </div>
                    }
                    extra={Alert_type(card)}
                  >
                    {card.information}
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
    return (
      <div>
        <LoadingOutlined />
      </div>
    );
  }
}
export default Notifications;
