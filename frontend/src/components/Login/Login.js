import React, { useState, useRef } from "react";
import { LockOutlined, UserOutlined, IdcardOutlined } from "@ant-design/icons";
import Logo from "../../images/cartoon-pug-dog-in-prison-costume-with-sign-vector.jpeg";
import { Button, Card, Form, Input, Modal } from "antd";
import { useNavigate } from "react-router-dom";
import CheckButton from "react-validation/build/button";
import AuthService from "../../services/auth.service";
const { Meta } = Card;

// export let Logged = false;

// export function SetLogged(params) {
//   Logged = params;
// }

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const Login = () => {
  let navigate = useNavigate(); 

  const form = useRef();
  const checkBtn = useRef();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeUsername = (e) => {
    const username = e.target.value;
    setUsername(username);
  };

  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };

  const handleLogin = (e) => {
    e.preventDefault();

    setMessage("");
    setLoading(true);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {
      AuthService.login(username, password).then(
        () => {
          navigate("/dashboard");
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

  // o que fazer quando carregamos no login
  // const onFinish = (values) => {
  //   //console.log("Received values of form: ", values);
  //   // nesgisse, nãp está como deve porque não está a DB pronta
  //   if (true) {
  //     Logged = true;
  //     navigate("/dashboard");
  //   } else {
  //     countDown();
  //   }
  // };

  // mensagem de erro quando login invalido
  // const countDown = () => {
  //   let secondsToGo = 3;

  //   const modal = Modal.error({
  //     title: "Login Invalid",
  //     content: `This modal will be destroyed after ${secondsToGo} second.`,
  //     okType: "danger",
  //   });

  //   const timer = setInterval(() => {
  //     secondsToGo -= 1;
  //     modal.update({
  //       content: `This modal will be destroyed after ${secondsToGo} second.`,
  //     });
  //   }, 1000);

  //   setTimeout(() => {
  //     clearInterval(timer);
  //     modal.destroy();
  //   }, secondsToGo * 1000);
  // };
  // form
  return (
    <div className="col-md-12">
      <div className="card card-container">
        <img
          src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
          alt="profile-img"
          className="profile-img-card"
        />

        <Form onSubmit={handleLogin} ref={form}>
          <div className="form-group">
            <label htmlFor="username">Username</label>
            <Input
              type="text"
              className="form-control"
              name="username"
              value={username}
              onChange={onChangeUsername}
              validations={[required]}
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">Password</label>
            <Input
              type="password"
              className="form-control"
              name="password"
              value={password}
              onChange={onChangePassword}
              validations={[required]}
            />
          </div>

          <div className="form-group">
            <button className="btn btn-primary btn-block" disabled={loading}>
              {loading && (
                <span className="spinner-border spinner-border-sm"></span>
              )}
              <span>Login</span>
            </button>
          </div>

          {message && (
            <div className="form-group">
              <div className="alert alert-danger" role="alert">
                {message}
              </div>
            </div>
          )}
          <CheckButton style={{ display: "none" }} ref={checkBtn} />
        </Form>
      </div>
    </div>
    // <div
    //   style={{
    //     display: "flex",
    //     justifyContent: "center",
    //     alignItems: "center",
    //     height: "90%",
    //   }}
    // >
    //   <Card
    //     bordered
    //     cover={
    //       <img
    //         src="https://www.shutterstock.com/image-vector/minimalist-prison-logo-black-white-600w-1521162209.jpg"
    //         height={"300px"}
    //       ></img>
    //     }
    //     style={{ width: "33%" }}
    //   >
    //     <Form
    //       name="normal_login"
    //       className="login-form"
    //       // initialValues={{
    //       //   remember: true,
    //       // }}
    //       // onFinish={onFinish}
    //       onSubmit={handleLogin} ref={form}
    //     >
    //       <Form.Item
    //         name="username"
    //         // rules={[
    //         //   {
    //         //     required: true,
    //         //     message: "Please input your Username!",
    //         //   },
    //         // ]}
    //       >
    //         <Input
    //           prefix={<UserOutlined className="site-form-item-icon" />}
    //           placeholder="Username"
    //           value={username}
    //           onChange={onChangeUsername}
    //           validations={[required]}
    //         />
    //       </Form.Item>
    //       <Form.Item
    //         name="password"
    //         // rules={[
    //         //   {
    //         //     required: true,
    //         //     message: "Please input your Password!",
    //         //   },
    //         // ]}
    //       >
    //         <Input
    //           prefix={<LockOutlined className="site-form-item-icon" />}
    //           type="password"
    //           placeholder="Password"
    //           value={password}
    //           onChange={onChangePassword}
    //           validations={[required]}
    //         />
    //       </Form.Item>
    //       <Form.Item>
    //         <Button 
    //           disabled={loading}
    //           type="primary"
    //           htmlType="submit"
    //           className="login-form-button"
    //           block
    //         >
    //           {loading && (
    //             <span className="spinner-border spinner-border-sm"></span>
    //           )}
    //           <span>Login</span>
    //         </Button>
    //       </Form.Item>
    //       {message && (
    //         <div className="form-group">
    //           <div className="alert alert-danger" role="alert">
    //             {message}
    //           </div>
    //         </div>
    //       )}
    //       <CheckButton style={{ display: "none" }} ref={checkBtn} />
    //     </Form>
    //   </Card>
    // </div>
  );
};
export default Login;