import { Card, Col, Row } from "antd";
import React from "react";
import { useState } from "react";
import { useParams } from "react-router";
import TheAvatar from "./Avatar/avatar";
import guard_info from "./guard_info/guard_info";
// import { Navigate } from "react-router-dom";
// import AuthService from "../../services/auth.service";

let url_params;
const tabList = [
  {
    key: "guard_info",
    tab: "Guard Info",
  }
];

function Guard_Profile() {
  url_params = useParams();
  //console.log(url_params.id);
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
export default Guard_Profile;

// ---------------------------------------------------------------- //

// import React, { Component } from "react";
// import { Navigate } from "react-router-dom";
// import AuthService from "../services/auth.service";

// export default class Profile extends Component {
//   constructor(props) {
//     super(props);

//     this.state = {
//       redirect: null,
//       userReady: false,
//       currentUser: { username: "" }
//     };
//   }

//   componentDidMount() {
//     const currentUser = AuthService.getCurrentUser();

//     if (!currentUser) this.setState({ redirect: "/home" });
//     this.setState({ currentUser: currentUser, userReady: true })
//   }

//   render() {
//     if (this.state.redirect) {
//       return <Navigate to={this.state.redirect} />
//     }

//     const { currentUser } = this.state;

//     return (
//       <div className="container">
//         {(this.state.userReady) ?
//         <div>
//         <header className="jumbotron">
//           <h3>
//             <strong>{currentUser.username}</strong> Profile
//           </h3>
//         </header>
//         <p>
//           <strong>Token:</strong>{" "}
//           {currentUser.accessToken.substring(0, 20)} ...{" "}
//           {currentUser.accessToken.substr(currentUser.accessToken.length - 20)}
//         </p>
//         <p>
//           <strong>Id:</strong>{" "}
//           {currentUser.id}
//         </p>
//         <p>
//           <strong>Email:</strong>{" "}
//           {currentUser.email}
//         </p>
//         <strong>Authorities:</strong>
//         <ul>
//           {currentUser.roles &&
//             currentUser.roles.map((role, index) => <li key={index}>{role}</li>)}
//         </ul>
//       </div>: null}
//       </div>
//     );
//   }
// }
