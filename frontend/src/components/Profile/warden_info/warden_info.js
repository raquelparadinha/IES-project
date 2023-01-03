
import React, { useState } from "react";
import { Card, Col, Space } from "antd";
import { LoadingOutlined } from "@ant-design/icons/lib/icons";
import axios from "axios";

function Warden_info() {
    const [dataSource, setDataSource] = useState();

    const fetchData = () => {
        try {
          return axios
            .get("http://localhost:5001/api/warden/profile")
            .then((response) => setDataSource(response.data));
        } catch {
          console.log("Deu pylance");
          fetchData();
        }
    };

    function SeeIfUndfined() {
        if (dataSource !== undefined) {
            console.log(dataSource);
          return (
            <>
              <p style={{ color: "#12494c" }}>Id: {dataSource.id}</p>
              <p style={{ color: "#12494c" }}>Birthdate: {dataSource.birthdate}</p>
              <p style={{ color: "#12494c" }}>Phone: {dataSource.phone}</p>
              <p style={{ color: "#12494c" }}>Email: {dataSource.email}</p>
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
    };

    let title_;
    if (dataSource !== undefined) {
        title_ = <div style={{ color: "#12494c" }}>{dataSource.name}</div>;
    } else {
        title_ = <LoadingOutlined />;
    }
    return (
        <Card
            title={<div style={{ color: "#12494c" }}>Warden Personal Information</div>}
            style={{ backgroundColor: "#D6E4E5" }}
        >
            <Col
                style={{
                justifyContent: "center",
                alignItems: "center",
                }}
            >
                <Space align="start">
                <Card
                    title={title_}
                    style={{
                    width: "550px",
                    height: "350px",
                    backgroundColor: "#EFF5F5",
                    }}
                >
                    {SeeIfUndfined()}
                </Card>
                </Space>
            </Col>
        </Card>
    );
}

export default Warden_info;