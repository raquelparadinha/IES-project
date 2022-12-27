import React, { useState, useRef } from "react";

import { LockOutlined, UserOutlined, IdcardOutlined } from "@ant-design/icons";
import Logo from "../../images/cartoon-pug-dog-in-prison-costume-with-sign-vector.jpeg";
import { Button, Card, Form, Input, Modal } from "antd";
import { useNavigate } from "react-router-dom";

// import Form from "react-validation/build/form";
// import Input from "react-validation/build/input";
// import CheckButton from "react-validation/build/button";
import AuthService from "../../services/auth.service";




export let Logged = false;

export function SetLogged(params) {
  Logged = params;
}

const Login = () => {
  console.log("Olá");
  const navigate = useNavigate();
  const form = useRef(null);
  const checkBtn = useRef();
  // o que fazer quando carregamos no login
  const onFinish = (values) => {
    console.log("finish");
    //console.log("Received values of form: ", values);
    // nesgisse, nãp está como deve porque não está a DB pronta
    if (true) {
      Logged = true;
      navigate("/dashboard");
    } else {
      countDown();
    }
  };

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const handleLogin = (e) => {
    console.log("Adeus");
    console.log(username, password);
    // e.preventDefault();

    setMessage("");
    setLoading(true);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {
      
      AuthService.login(username, password).then(
        () => {
          navigate("/profile");
          window.location.reload();
        },
        (error) => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          setLoading(false);
          setMessage(resMessage);
        }
      );
    } else {
      setLoading(false);
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
          
          onFinish={handleLogin} 
          // onFinish={onFinish}
          ref={form}
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
              value={username}
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
              value={password}
            />
          </Form.Item>
          <Form.Item>
            <Button
              type="primary"
              htmlType="submit"
              className="login-form-button"
              ref={checkBtn}
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