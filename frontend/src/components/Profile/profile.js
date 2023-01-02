import { Card, Col, Row } from "antd";
import React from "react";
import { useState } from "react";
import { getCurrentUser } from "../../App";

function Profile() {
    const currentUser = getCurrentUser();

    if (currentUser.roles.includes("ROLE_ADMIN")) {

    } else if (currentUser.roles.includes("ROLE_USER")) {
        const contentList = {
            guard_info: guard_info(url_params.id),
            // timeline: timeline_guard(),
          };
        
          const [activeTabKey1, setActiveTabKey1] = useState("guard_info");
          const onTab1Change = (key) => {
            setActiveTabKey1(key);
          };
          return (
            <div
              style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                height: "90%",
              }}
            >
              <Card
                style={{ backgroundColor: "#EFF5F5" }}
                title={
                  <div
                    style={{
                      textAlign: "center",
                    }}
                  >
                    <TheAvatar />
                  </div>
                }
                tabList={tabList}
                tabProps={{ centered: true }}
                activeTabKey={activeTabKey1}
                onTabChange={(key) => {
                  onTab1Change(key);
                }}
              >
                <Row>
                  <Col style={{ textAlign: "center" }}>
                    <Row></Row>
                    {contentList[activeTabKey1]}
                  </Col>
                </Row>
              </Card>
            </div>
          );
        }
    };
}

export default Profile;