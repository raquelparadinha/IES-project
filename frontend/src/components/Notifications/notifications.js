import React from "react";
import { Card, Col } from "antd";
import { DownOutlined } from "@ant-design/icons";
import { Dropdown, message, Space } from "antd";

const onClick = ({ key }) => {
  //message.info(`Click on item ${key}`);
    message.info(<button onClick={message.success("Espera pelo próximo espisódio")}>Open</button>)
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

const Notifications = () => (
  <>
    <Col>
      <Card title="Notifications">
        <Card
          type="inner"
          title="ESTRILHO"
          extra={
            <Dropdown
              menu={{
                items,
                onClick,
              }}
            >
              <a onClick={(e) => e.preventDefault()}>
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
          type="inner"
          title="ESTRILHO"
          extra={
            <Dropdown
              menu={{
                items,
                onClick,
              }}
            >
              <a onClick={(e) => e.preventDefault()}>
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
          type="inner"
          title="ESTRILHO"
          extra={
            <Dropdown
              menu={{
                items,
                onClick,
              }}
            >
              <a onClick={(e) => e.preventDefault()}>
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
          type="inner"
          title="ESTRILHO"
          extra={
            <Dropdown
              menu={{
                items,
                onClick,
              }}
            >
              <a onClick={(e) => e.preventDefault()}>
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
          type="inner"
          title="ESTRILHO"
          extra={
            <Dropdown
              menu={{
                items,
                onClick,
              }}
            >
              <a onClick={(e) => e.preventDefault()}>
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
          type="inner"
          title="ESTRILHO"
          extra={
            <Dropdown
              menu={{
                items,
                onClick,
              }}
            >
              <a onClick={(e) => e.preventDefault()}>
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
          type="inner"
          title="ESTRILHO"
          extra={
            <Dropdown
              menu={{
                items,
                onClick,
              }}
            >
              <a onClick={(e) => e.preventDefault()}>
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
export default Notifications;
