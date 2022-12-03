import React from "react";
import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { Button, Checkbox, Form, Input, Modal } from "antd";
import { useNavigate } from "react-router-dom";
const Login = () => {
  const navigate = useNavigate();
  const onFinish = (values) => {
    console.log("Received values of form: ", values);
    if (values.remember) {
      navigate("/dashboard");
    } else {
      countDown();
    }
  };
  const countDown = () => {
    let secondsToGo = 3;

    const modal = Modal.error({
      title: "Login Invalid",
      content: `This modal will be destroyed after ${secondsToGo} second.`,
      okType: "danger"
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
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        marginTop: "25vh",
      }}
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
          <Form.Item name="remember" valuePropName="checked" noStyle>
            <Checkbox>Remember me</Checkbox>
          </Form.Item>

          <a className="login-form-forgot" href="/signup">
            Forgot password
          </a>
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
          Or <a href="/signup">register now!</a>
        </Form.Item>
      </Form>
    </div>
  );
};
export default Login;
