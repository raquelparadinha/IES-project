import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

import AuthService from "../services/auth.service";

import { withRouter } from '../common/with-router';

const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

class Login extends Component {
  constructor(props) {
    super(props);
    this.handleLogin = this.handleLogin.bind(this);
    this.onChangeUsername = this.onChangeUsername.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);

    this.state = {
      username: "",
      password: "",
      loading: false,
      message: ""
    };
  }

  onChangeUsername(e) {
    this.setState({
      username: e.target.value
    });
  }

  onChangePassword(e) {
    this.setState({
      password: e.target.value
    });
  }

  handleLogin(e) {
    e.preventDefault();

    this.setState({
      message: "",
      loading: true
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      AuthService.login(this.state.username, this.state.password).then(
        () => {
          this.props.router.navigate("/dashboard");
          window.location.reload();
        },
        error => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          this.setState({
            loading: false,
            message: resMessage
          });
        }
      );
    } else {
      this.setState({
        loading: false
      });
    }
  }

  render() {
    return (
      <div className="col-md-12">
        <div className="card card-container">
          <img
            src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
            alt="profile-img"
            className="profile-img-card"
          />

          <Form
            onSubmit={this.handleLogin}
            ref={c => {
              this.form = c;
            }}
          >
            <div className="form-group">
              <label htmlFor="username">Email</label>
              <Input
                type="text"
                className="form-control"
                name="username"
                value={this.state.username}
                onChange={this.onChangeUsername}
                validations={[required]}
              />
            </div>

            <div className="form-group">
              <label htmlFor="password">Password</label>
              <Input
                type="password"
                className="form-control"
                name="password"
                value={this.state.password}
                onChange={this.onChangePassword}
                validations={[required]}
              />
            </div>

            <div className="form-group">
              <button
                className="btn btn-primary btn-block"
                disabled={this.state.loading}
              >
                {this.state.loading && (
                  <span className="spinner-border spinner-border-sm"></span>
                )}
                <span>Login</span>
              </button>
            </div>

            {this.state.message && (
              <div className="form-group">
                <div className="alert alert-danger" role="alert">
                  {this.state.message}
                </div>
              </div>
            )}
            <CheckButton
              style={{ display: "none" }}
              ref={c => {
                this.checkBtn = c;
              }}
            />
          </Form>
        </div>
      </div>
    );
  }
}

export default withRouter(Login);

// import React from "react";
// import { LockOutlined, UserOutlined, IdcardOutlined } from "@ant-design/icons";
// import Logo from "../../images/cartoon-pug-dog-in-prison-costume-with-sign-vector.jpeg";
// import { Button, Card, Form, Input, Modal } from "antd";
// import { useNavigate } from "react-router-dom";
// const { Meta } = Card;

// export let Logged = false;

// export function SetLogged(params) {
//   Logged = params;
// }

// const Login = () => {
//   const navigate = useNavigate();
//   // o que fazer quando carregamos no login
//   const onFinish = (values) => {
//     //console.log("Received values of form: ", values);
//     // nesgisse, nãp está como deve porque não está a DB pronta
//     if (values.remember) {
//       Logged = true;
//       navigate("/dashboard");
//     } else {
//       countDown();
//     }
//   };

//   // mensagem de erro qunado login invalido
//   const countDown = () => {
//     let secondsToGo = 3;

//     const modal = Modal.error({
//       title: "Login Invalid",
//       content: `This modal will be destroyed after ${secondsToGo} second.`,
//       okType: "danger",
//     });

//     const timer = setInterval(() => {
//       secondsToGo -= 1;
//       modal.update({
//         content: `This modal will be destroyed after ${secondsToGo} second.`,
//       });
//     }, 1000);

//     setTimeout(() => {
//       clearInterval(timer);
//       modal.destroy();
//     }, secondsToGo * 1000);
//   };
//   // form
//   return (
//     <div
//       style={{
//         display: "flex",
//         justifyContent: "center",
//         alignItems: "center",
//         height: "90%",
//       }}
//     >
//       <Card bordered cover={<img src="https://www.shutterstock.com/image-vector/minimalist-prison-logo-black-white-600w-1521162209.jpg" height={"300px"}></img>} style={{ width: "33%" }}>
//         <Form
//           name="normal_login"
//           className="login-form"
//           initialValues={{
//             remember: true,
//           }}
//           onFinish={onFinish}
//         >
//           <Form.Item
//             name="username"
//             rules={[
//               {
//                 required: true,
//                 message: "Please input your Email!",
//               },
//             ]}
//           >
//             <Input
//               // prefix={<UserOutlined className="site-form-item-icon" />}
//               // placeholder="Email"
//               type="text"
//               className="form-control"
//               name="username"
//               value={this.state.username}
//               onChange={this.onChangeUsername}
//               validations={[required]}
//             />
//           </Form.Item>
//           <Form.Item
//             name="password"
//             rules={[
//               {
//                 required: true,
//                 message: "Please input your Password!",
//               },
//             ]}
//           >
//             <Input
//               prefix={<LockOutlined className="site-form-item-icon" />}
//               type="password"
//               placeholder="Password"
//             />
//           </Form.Item>
//           <Form.Item>
//             <Button
//               type="primary"
//               htmlType="submit"
//               className="login-form-button"
//               block
//             >
//               Log in
//             </Button>
//           </Form.Item>
//         </Form>
//       </Card>
//     </div>
//   );
// };
// export default Login;
