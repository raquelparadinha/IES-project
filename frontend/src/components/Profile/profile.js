import { Card, Col, Row, Button, Space, Modal, Input, Form } from "antd";
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
  const [form] = Form.useForm();


  const ResetEditing = () => {
    setisEditing(false);
    setEditingGuard(null);
  };

  const onEditGuard = (record) => {
    setisEditing(true);
    // ... para fazer copia, tendi nada
    setEditingGuard({ ...record });
  };

  const startEditing = () => {
    try {
      const data = axios.get("http://localhost:5001/api/guard/" + currentUser.id)
      .then((response) => {
        onEditGuard(response.data);
      })
    } catch {
      console.log("failed fetching");
    }
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
          <div
            style={{ 
              width: "100%",
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              paddingTop : "25px"
            }}>
            <Button
              onClick={() => startEditing()}
              shape={"round"}
              style={{ backgroundColor: "#497174" }}
              type="primary" 
            >
              Update information
            </Button>
          </div>
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
          <div
            style={{ 
              width: "100%",
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              paddingTop : "25px"
            }}>
            <Button
              onClick={() => startEditing()}
              shape={"round"}
              style={{ backgroundColor: "#497174" }}
              type="primary" 
            >
              Update information
            </Button>
          </div>
        </Card>
        <Modal
          title="Edit information"
          open={isEditing}
          okText="Save"
          okType="primary"
          onCancel={() => {
            ResetEditing();
          }}
          onOk={form.submit}
          // onOk={() => {
          //   data((guard) => {
          //     const bool = editGuard(editingGuard);
          //     if (bool) {
          //       Modal.success({
          //         title: "Edit Successful",
          //         content: `Guard changed with success.`,
          //         okType: "ghost",
          //       });
          //       return editingGuard;
          //     } else {
          //       Modal.error({
          //         title: "Edit Error",
          //         content: `An error happened while changing the guard.`,
          //         okType: "danger",
          //       });
          //       return guard;
          //     }
          //   });
          //   ResetEditing();
          // }}
        >
          <Space direction="vertical" style={{ width: "100%", height: "100%" }}>
          <Form
            form={form}
            onFinish={(values) => {editGuard(values)}}
          >
            <Form.Item 
              name="name"
              label="Name"
              initialValue={editingGuard?.name}>
              <Input 
                onChange={(e) => {
                  setEditingGuard((pre) => {
                    return { ...pre, name: e.target.value };
                  });
                }}
              />
            </Form.Item>
            <Form.Item 
              name="email"
              label="Email"
              initialValue={editingGuard?.email}>
              <Input 
                onChange={(e) => {
                  setEditingGuard((pre) => {
                    return { ...pre, email: e.target.value };
                  });
                }}
              />
            </Form.Item>
            <Form.Item 
              name="phone"
              label="Phone"
              initialValue={editingGuard?.phone}>
              <Input 
                onChange={(e) => {
                  setEditingGuard((pre) => {
                    return { ...pre, phone: e.target.value };
                  });
                }}
              />
            </Form.Item>
            <Form.Item 
              name="areaId"
              label="AreaId"
              initialValue={editingGuard?.areaId}>
              <Input 
                onChange={(e) => {
                  setEditingGuard((pre) => {
                    return { ...pre, areaId: e.target.value };
                  });
                }}
              />
            </Form.Item>
            <Form.Item 
              name="password"
              label="Password"
              initialValue={editingGuard?.password}>
              <Input.Password
                onChange={(e) => {
                  setEditingGuard((pre) => {
                    return { ...pre, password: e.target.value };
                  });
                }}
              />
            </Form.Item>
            <Form.Item 
              name="confirm"
              label="Confirm password"
              dependencies={["Password"]}
              hasFeedback
              rules={[
                ({ getFieldValue }) => ({
                  validator(_, value) {
                    if (!value || getFieldValue('password') === value) {
                      return Promise.resolve();
                    }
                    return Promise.reject(new Error('The two passwords that you entered do not match!'));
                  },
                }),
              ]}>
              <Input.Password
                // onChange={(e) => {
                //   setEditingGuard((pre) => {
                //     return { ...pre, password: e.target.value };
                //   });
                // }}
              />
            </Form.Item>
            <Form.Item>
              <Button type="primary" htmlType="submit">
                Register
              </Button>
            </Form.Item>
          </Form>
            {/* <Input
              addonBefore="Name"
              value={editingGuard?.name}
              onChange={(e) => {
                setEditingGuard((pre) => {
                  return { ...pre, name: e.target.value };
                });
              }}
            /> */}
            {/* <Input
              addonBefore="Email"
              value={editingGuard?.email}
              onChange={(e) => {
                setEditingGuard((pre) => {
                  return { ...pre, email: e.target.value };
                });
              }}
            /> */}
            {/* <Input
              addonBefore="Phone"
              value={editingGuard?.phone}
              onChange={(e) => {
                setEditingGuard((pre) => {
                  return { ...pre, phone: e.target.value };
                });
              }}
            /> */}
            {/* <Input
              addonBefore="AreaId"
              value={editingGuard?.areaId}
              onChange={(e) => {
                setEditingGuard((pre) => {
                  return { ...pre, areaId: e.target.value };
                });
              }}
            /> */}
            {/* <Input
              addonBefore="Old password"
              value={editingGuard?.password}
              type="password"
            />
            <Input.Password
              addonBefore="New password"
              type="password"
              onChange={(e) => {
                setEditingGuard((pre) => {
                  return { ...pre, password: e.target.value };
                });
              }}
            />
            <Input.Password
              addonBefore="Confirm new password"
              type="password"
              onChange={(e) => {
                setEditingGuard((pre) => {
                  return { ...pre, password: e.target.value };
                });
              }}
            /> */}
          </Space>
        </Modal>
      </div>
    );
  }

  function editGuard(Edited_guard) {
    console.log(Edited_guard);
    try {
      axios.put(
        "http://localhost:5001/api/guard/" + currentUser.id,
        Edited_guard
      );
    } catch (error) {
      console.log("Deu pylance");
      return false;
    }
    return true;
  }
}




export default Profile;
