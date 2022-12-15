import React from "react";
import { LockOutlined, UserOutlined, IdcardOutlined } from "@ant-design/icons";
import Logo from "../../images/cartoon-pug-dog-in-prison-costume-with-sign-vector.jpeg";
import { Button, Card, Form, Input, Modal } from "antd";
import { useNavigate } from "react-router-dom";
const { Meta } = Card;

export let Logged = false;

export function SetLogged(params) {
  Logged = params;
}

const Login = () => {
  const navigate = useNavigate();
  // o que fazer quando carregamos no login
  const onFinish = (values) => {
    //console.log("Received values of form: ", values);
    // nesgisse, nãp está como deve porque não está a DB pronta
    if (true) {
      Logged = true;
      navigate("/dashboard");
    } else {
      countDown();
    }
  };

  // mensagem de erro quando login invalido
  const countDown = () => {
    let secondsToGo = 3;

    const modal = Modal.error({
      title: "Login Invalid",
      content: `This modal will be destroyed after ${secondsToGo} second.`,
      okType: "danger",
    });

    const timer = setInterval(() => {
      secondsToGo -= 1;
      modal.update({
        content: `This modal will be destroyed after ${secondsToGo} second.`,
      });
    }, 1000);

    setTimeout(() => {
      clearInterval(timer);
      modal.destroy();
    }, secondsToGo * 1000);
  };
  // form
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
        bordered
        cover={
          <img
            src="https://www.shutterstock.com/image-vector/minimalist-prison-logo-black-white-600w-1521162209.jpg"
            height={"300px"}
          ></img>
        }
        style={{ width: "33%" }}
      >
        <Form
          name="normal_login"
          className="login-form"
          initialValues={{
            remember: true,
          }}
          onFinish={onFinish}
        >
          <Form.Item
            name="username"
            rules={[
              {
                required: true,
                message: "Please input your Username!",
              },
            ]}
          >
            <Input
              prefix={<UserOutlined className="site-form-item-icon" />}
              placeholder="Username"
            />
          </Form.Item>
          <Form.Item
            name="password"
            rules={[
              {
                required: true,
                message: "Please input your Password!",
              },
            ]}
          >
            <Input
              prefix={<LockOutlined className="site-form-item-icon" />}
              type="password"
              placeholder="Password"
            />
          </Form.Item>
          <Form.Item>
            <Button
              type="primary"
              htmlType="submit"
              className="login-form-button"
              block
            >
              Log in
            </Button>
          </Form.Item>
        </Form>
      </Card>
    </div>
  );
};
export default Login;
