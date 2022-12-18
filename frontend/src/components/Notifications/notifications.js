import React, { useEffect, useState } from "react";
import { Card, Col } from "antd";
import { DownOutlined } from "@ant-design/icons";
import { Dropdown, message, Space } from "antd";
import axios from "axios";

const onClick = ({ key }) => {
  //message.info(`Click on item ${key}`);
  message.info(key);
};
const items = [
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

function Notifications() {
  const [dataSource, setDataSource] = useState();
  const fetchData = () => {
    //console.log("dataaa");
    return axios.get("http://localhost:5001/api/alert").then((response) => {
      setDataSource(response.data);
      console.log(dataSource)
    });
  };

  useEffect(() => {
    setInterval(fetchData(), 10000); // The function will be called
  }, []);

  // while (dataSource === undefined) {
  //   console.log("pylance");
  //   fetchData();
  // }
  console.log(dataSource);
  return (
    <>
      <Col>
        <Card
          title={
            <div style={{ textAlign: "center", color: "#12494c" }}>
              Notifications
            </div>
          }
          style={{ backgroundColor: "#D6E4E5" }}
        >
          <Card
            type="inner"
            headStyle={{ backgroundColor: "#c2d8d8" }}
            bodyStyle={{ backgroundColor: "#eff5f5" }}
            title={<div style={{ color: "#12494c" }}>ESTRILHO</div>}
            extra={
              <Dropdown
                menu={{
                  items,
                  onClick,
                }}
              >
                <a
                  onClick={(e) => e.preventDefault()}
                  style={{ color: "#169197" }}
                >
                  <Space>
                    More
                    <DownOutlined />
                  </Space>
                </a>
              </Dropdown>
            }
          >
            ESTRILHO MESMO COMPLICADO NA CANTINA
          </Card>
          <Card
            style={{
              marginTop: 16,
            }}
            headStyle={{ backgroundColor: "#c2d8d8" }}
            bodyStyle={{ backgroundColor: "#eff5f5" }}
            type="inner"
            title={<div style={{ color: "#12494c" }}>ESTRILHO</div>}
            extra={
              <Dropdown
                menu={{
                  items,
                  onClick,
                }}
              >
                <a
                  onClick={(e) => e.preventDefault()}
                  style={{ color: "#169197" }}
                >
                  <Space>
                    More
                    <DownOutlined />
                  </Space>
                </a>
              </Dropdown>
            }
          >
            ESTRILHO MESMO COMPLICADO NA CANTINA
          </Card>
          <Card
            headStyle={{ backgroundColor: "#c2d8d8" }}
            bodyStyle={{ backgroundColor: "#eff5f5" }}
            style={{
              marginTop: 16,
            }}
            type="inner"
            title={<div style={{ color: "#12494c" }}>ESTRILHO</div>}
            extra={
              <Dropdown
                menu={{
                  items,
                  onClick,
                }}
              >
                <a
                  onClick={(e) => e.preventDefault()}
                  style={{ color: "#169197" }}
                >
                  <Space>
                    More
                    <DownOutlined />
                  </Space>
                </a>
              </Dropdown>
            }
          >
            ESTRILHO MESMO COMPLICADO NA CANTINA
          </Card>
          <Card
            headStyle={{ backgroundColor: "#c2d8d8" }}
            bodyStyle={{ backgroundColor: "#eff5f5" }}
            style={{
              marginTop: 16,
            }}
            type="inner"
            title={<div style={{ color: "#12494c" }}>ESTRILHO</div>}
            extra={
              <Dropdown
                menu={{
                  items,
                  onClick,
                }}
              >
                <a
                  onClick={(e) => e.preventDefault()}
                  style={{ color: "#169197" }}
                >
                  <Space>
                    More
                    <DownOutlined />
                  </Space>
                </a>
              </Dropdown>
            }
          >
            ESTRILHO MESMO COMPLICADO NA CANTINA
          </Card>
          <Card
            headStyle={{ backgroundColor: "#c2d8d8" }}
            bodyStyle={{ backgroundColor: "#eff5f5" }}
            style={{
              marginTop: 16,
            }}
            type="inner"
            title={<div style={{ color: "#12494c" }}>ESTRILHO</div>}
            extra={
              <Dropdown
                menu={{
                  items,
                  onClick,
                }}
              >
                <a
                  onClick={(e) => e.preventDefault()}
                  style={{ color: "#169197" }}
                >
                  <Space>
                    More
                    <DownOutlined />
                  </Space>
                </a>
              </Dropdown>
            }
          >
            ESTRILHO MESMO COMPLICADO NA CANTINA
          </Card>
          <Card
            headStyle={{ backgroundColor: "#c2d8d8" }}
            bodyStyle={{ backgroundColor: "#eff5f5" }}
            style={{
              marginTop: 16,
            }}
            type="inner"
            title={<div style={{ color: "#12494c" }}>ESTRILHO</div>}
            extra={
              <Dropdown
                menu={{
                  items,
                  onClick,
                }}
              >
                <a
                  onClick={(e) => e.preventDefault()}
                  style={{ color: "#169197" }}
                >
                  <Space>
                    More
                    <DownOutlined />
                  </Space>
                </a>
              </Dropdown>
            }
          >
            ESTRILHO MESMO COMPLICADO NA CANTINA
          </Card>
          <Card
            headStyle={{ backgroundColor: "#c2d8d8" }}
            bodyStyle={{ backgroundColor: "#eff5f5" }}
            style={{
              marginTop: 16,
            }}
            type="inner"
            title={<div style={{ color: "#12494c" }}>ESTRILHO</div>}
            extra={
              <Dropdown
                menu={{
                  items,
                  onClick,
                }}
              >
                <a
                  onClick={(e) => e.preventDefault()}
                  style={{ color: "#169197" }}
                >
                  <Space>
                    More
                    <DownOutlined />
                  </Space>
                </a>
              </Dropdown>
            }
          >
            ESTRILHO MESMO COMPLICADO NA CANTINA
          </Card>
        </Card>
      </Col>
    </>
  );
}
export default Notifications;
