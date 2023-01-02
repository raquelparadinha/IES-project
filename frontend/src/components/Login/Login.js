import { IdcardOutlined, LockOutlined, UserOutlined } from "@ant-design/icons";
import { Button, Card, Form, Input, Modal } from "antd";
import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Logo from "../../images/cartoon-pug-dog-in-prison-costume-with-sign-vector.jpeg";
const { Meta } = Card;

export let Logged = undefined;

export function SetLogged(params) {
  Logged = params;
}

const Login = () => {
  const navigate = useNavigate();

  const handleLogin = (values) => {
    console.log(values);
    return axios
      .post("http://localhost:5001/api/auth/signin", {
        email: values.email,
        password: values.password,
      })
      .catch((error) => {
        countDown();
        console.log("error in auth/signin post");
      })
      .then((response) => {
        if (response) {
          sessionStorage.setItem("user", JSON.stringify(response.data));
          const data = response.data;
          console.log(data);
          if (data !== undefined) {
            if (data.roles.includes("ROLE_ADMIN")) {
              Logged = "admin";
            } else if (data.roles.includes("ROLE_USER")) {
              Logged = "user";
            }
            navigate("/map");
          }
        }
      });
  };

  // mensagem de erro quando login invalido
  const countDown = () => {
    console.log("counting down");
    let secondsToGo = 3;

    const modal = Modal.error({
      title: "Login Invalid",
      content: `Login credentials are not valid. Please try again.`,
      okType: "danger",
    });

    const timer = setInterval(() => {
      secondsToGo -= 1;
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
      }}>
      <Card bordered cover={<img src="https://www.shutterstock.com/image-vector/minimalist-prison-logo-black-white-600w-1521162209.jpg" height={"300px"}></img>} style={{ width: "33%" }}>
        <Form
          name="normal_login"
          className="login-form"
          initialValues={{
            remember: true,
          }}
          onFinish={handleLogin}>
          <Form.Item
            name="email"
            rules={[
              {
                required: true,
                type: "email",
                message: "Please input your email!",
              },
            ]}>
            <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Email" />
          </Form.Item>
          <Form.Item
            name="password"
            rules={[
              {
                required: true,
                type: "string",
                message: "Please input your Password!",
              },
            ]}>
            <Input prefix={<LockOutlined className="site-form-item-icon" />} type="password" placeholder="Password" />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit" className="login-form-button" block>
              Log in
            </Button>
          </Form.Item>
        </Form>
      </Card>
    </div>
  );
};
export default Login;

function authHeader() {
  const user = JSON.parse(sessionStorage.getItem("user"));

  if (user && user.accessToken) {
    return { Authorization: "Bearer " + user.accessToken };
  } else {
    return {};
  }
}
