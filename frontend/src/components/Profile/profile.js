import { Card, Col, Row, Button, Space, Modal, Input } from "antd";
import React, { useState } from "react";
import { getCurrentUser } from "../../App";
import axios from "axios";
import TheAvatar from "./Avatar/avatar";
import guard_info from "./guard_info/guard_info";
import warden_info from "./warden_info/warden_info";

function Profile() {
  const currentUser = getCurrentUser();
  const [isEditing, setisEditing] = useState(false);
  const [editingGuard, setEditingGuard] = useState(null);

  const ResetEditing = () => {
    setisEditing(false);
    setEditingGuard(null);
  };

  const onEditGuard = (record) => {
    setisEditing(true);
    // ... para fazer copia, tendi nada
    setEditingGuard({ ...record });
  };

  if (currentUser.roles.includes("ROLE_ADMIN")) {
    const data = warden_info();
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
              {data}
            </Col>
          </Row>
        </Card>
      </div>
    );
    
  } else if (currentUser.roles.includes("ROLE_USER")) {
    const data = guard_info(currentUser.id);
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
              {data}
            </Col>
          </Row>
          <Button
            onClick={() => {
              onEditGuard(data);
            }}
            shape={"round"}
            style={{ marginLeft: "40%", backgroundColor: "#497174" }}
            type="primary" 
          >
            Update information
          </Button>
        </Card>
        <Modal
          title="Edit information"
          open={isEditing}
          okText="Save"
          okType="primary"
          onCancel={() => {
            ResetEditing();
          }}
          onOk={() => {
            data((guard) => {
              const bool = editGuard(editingGuard);
              if (bool) {
                Modal.success({
                  title: "Edit Successful",
                  content: `Guard changed with success.`,
                  okType: "ghost",
                });
                return editingGuard;
              } else {
                Modal.error({
                  title: "Edit Error",
                  content: `An error happened while changing the guard.`,
                  okType: "danger",
                });
                return guard;
              }
            });
            ResetEditing();
          }}
        >
          <Space direction="vertical" style={{ width: "100%", height: "100%" }}>
            <Input
              addonBefore="Name"
              value={editingGuard?.name}
              onChange={(e) => {
                setEditingGuard((pre) => {
                  return { ...pre, name: e.target.value };
                });
              }}
            />
            <Input
              addonBefore="Email"
              value={editingGuard?.email}
              onChange={(e) => {
                setEditingGuard((pre) => {
                  return { ...pre, email: e.target.value };
                });
              }}
            />
            <Input
              addonBefore="Phone"
              value={editingGuard?.phone}
              onChange={(e) => {
                setEditingGuard((pre) => {
                  return { ...pre, phone: e.target.value };
                });
              }}
            />
            <Input
              addonBefore="AreaId"
              value={editingGuard?.areaId}
              onChange={(e) => {
                setEditingGuard((pre) => {
                  return { ...pre, areaId: e.target.value };
                });
              }}
            />
            <Input
              addonBefore="Password"
              value={editingGuard?.password}
              onChange={(e) => {
                setEditingGuard((pre) => {
                  return { ...pre, password: e.target.value };
                });
              }}
            />
          </Space>
        </Modal>
      </div>
    );
  }
}

function editGuard(Edited_guard) {
  try {
    axios.put(
      "http://localhost:5001/api/guard/" + Edited_guard.id,
      Edited_guard
    );
  } catch (error) {
    console.log("Deu pylance");
    return false;
  }
  return true;
}

export default Profile;
