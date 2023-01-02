import { Card, Col, Row } from "antd";
import React from "react";
import { getCurrentUser } from "../../App";
import TheAvatar from "./Avatar/avatar";
import guard_info from "./guard_info/guard_info";

function Profile() {
  const currentUser = getCurrentUser();
  if (currentUser.roles.includes("ROLE_ADMIN")) {
    return (
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          height: "90%",
        }}>
        <Card
          style={{ backgroundColor: "#EFF5F5" }}
          title={
            <div
              style={{
                textAlign: "center",
              }}>
              <TheAvatar />
            </div>
          }>
          <Row>
            <Col style={{ textAlign: "center" }}>
              <Row></Row>
              {currentUser.id}
              <Row></Row>
              {currentUser.birthdate}
              <Row></Row>
              {currentUser.phone}
            </Col>
          </Row>
        </Card>
      </div>
    );
  } else if (currentUser.roles.includes("ROLE_USER")) {
    const contentList = {
      guard_info: guard_info(currentUser.id),
    };
    return (
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          height: "90%",
        }}>
        <Card
          style={{ backgroundColor: "#EFF5F5" }}
          title={
            <div
              style={{
                textAlign: "center",
              }}>
              <TheAvatar />
            </div>
          }>
          <Row>
            <Col style={{ textAlign: "center" }}>
              <Row></Row>
              {contentList.guard_info}
            </Col>
          </Row>
        </Card>
      </div>
    );
  }
}

export default Profile;
