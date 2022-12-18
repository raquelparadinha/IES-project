import React, { useEffect, useState } from "react";
import { Card, Col } from "antd";
import {
  DownOutlined,
  LoadingOutlined,
  ToolFilled,
  HeartFilled,
  AlertFilled,
} from "@ant-design/icons";
import { Dropdown, message, Space } from "antd";
import axios from "axios";

const onClick = ({ key }) => {
  //message.info(`Click on item ${key}`);
  message.info(key);
};
let items = [
  {
    label: "Mark as viewed",
    key: "1",
  },
  {
    label: "Favorite",
    key: "2",
  },
  {
    label: "Delete",
    key: "3",
  },
];

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
    }, 30000);
    return () => clearInterval(interval);
  });

  fetchData();
  console.log(dataSource);
  const indexOfLastCard = currentPage * cardsPerPage;
  const indexOfFirstCard = indexOfLastCard - cardsPerPage;
  let currentCards;

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
                items = [
                  {
                    label: "Health Log ID: " + card.healthLogId,
                    key: "1",
                  },
                  {
                    label: "Time Stamp: " + card.timestamp,
                    key: "2",
                  },
                ];
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
                    extra={
                      <Dropdown menu={{ items }}>
                        <a
                          onClick={(e) => e.preventDefault()}
                          style={{ color: colors[`${card.type}`] }}
                        >
                          <Space>
                            More
                            <DownOutlined />
                          </Space>
                        </a>
                      </Dropdown>
                    }
                  >
                    {card.information}
                  </Card>
                );
              })}
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
