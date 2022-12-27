import "antd/dist/reset.css";
import "./App.css";
import { Menu, notification } from "antd";
import { Route, Routes, useNavigate, Navigate } from "react-router-dom";
import {
  DashboardOutlined,
  UnorderedListOutlined,
  UserOutlined,
  LogoutOutlined,
  LoginOutlined,
  NotificationOutlined,
  ToolOutlined,
} from "@ant-design/icons/lib/icons";
//import Logo from "./images/cartoon-pug-dog-in-prison-costume-with-sign-vector.jpeg";
import PrisionersList from "./components/prisionersList/prisionersList";
import Login from "./components/Login/Login";
import { Logged, SetLogged } from "./components/Login/Login";
import Dashboard from "./components/Dashboard/dashboard";
import GuardsList from "./components/GuardsList/guardsList";
import Notifications from "./components/Notifications/notifications";
import Prisioner_Profile from "./components/Profile/prisioner_profile";
import Guard_Profile from "./components/Profile/guard_profile";
import axios from "axios";
import { useEffect, useState } from "react";
import Workstations from "./components/Workstations/workstations";
import MapaEstricado from "./components/MapaEstricado/MapaEstricado";
import {
  icons,
  back_colors,
  colors,
} from "./components/Notifications/notifications";

function App() {
  const [api, contextHolder] = notification.useNotification();
  const [max_id, setMaxId] = useState(0);
  // const fetchData = () => {
  //   try {
  //     return axios
  //       .get("http://localhost:5001/api/alert/new")
  //       .then((response) => {
  //         console.log(response.data)
  //         response.data.forEach((message_) => {
  //           api.open({
  //             duration: 2,
  //             message:
  //               message_.type.charAt(0).toUpperCase() + message_.type.slice(1),
  //             description: message_.information,
  //             icon: (
  //               <div
  //                 style={{
  //                   color: colors[`${message_.type}`],
  //                 }}
  //               >
  //                 {icons[`${message_.type}`]}
  //               </div>
  //             ),
  //           });
  //         });
  //       });
  //   } catch {
  //     console.log("Deu pylance");
  //     fetchData();
  //   }
  // };
  useEffect(() => {
    const interval = setInterval(() => {
      // fetchData();
    }, 1000);
    return () => clearInterval(interval);
  });

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        flex: 1,
        height: "100vh",
      }}
    >
      {contextHolder}
      <SideMenu />
    </div>
  );
}

export function SideMenu() {
  const navigate = useNavigate();
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "row",
        flex: 1,
      }}
    >
      <Menu
        onClick={({ key }) => {
          if (key === "logout") {
            SetLogged(false);
            navigate("/dashboard");
          } else {
            navigate(key);
          }
        }}
        defaultSelectedKeys={[window.location.pathname]}
        items={islogged(Logged)}
        style={{ backgroundColor: "#EFF5F5" }}
      ></Menu>
      <Content />
    </div>
  );
}

function Content() {
  return (
    <div style={{ width: "100%", backgroundColor: "#D6E4E5" }}>
      {/* <BreadCrumb /> */}
      <Routes>
        <Route path="/" element={<Navigate to="/dashboard" />}></Route>
        <Route
          path="/dashboard"
          element={
            <div>
              <Dashboard />
            </div>
          }
        ></Route>
        <Route path="/userlist" element={<div>User List</div>}></Route>
        <Route path="/prisioners" element={<PrisionersList />}></Route>
        <Route path="/prisioners/:id" element={<Prisioner_Profile />}></Route>
        <Route path="/guards/:id" element={<Guard_Profile />}></Route>
        <Route
          path="/guards"
          element={
            <div>
              <GuardsList />
            </div>
          }
        ></Route>
        <Route path="/notifications" element={<Notifications />}></Route>
        <Route path="/workstations" element={<Workstations />}></Route>
        <Route path="/profile" element={<MapaEstricado />}></Route>
        <Route path="/login" element={<Login />}></Route>
      </Routes>
    </div>
  );
}
function islogged(params) {
  if (params) {
    return [
      {
        label: "Dashboard",
        key: "/dashboard",
        icon: <DashboardOutlined />,
      },
      {
        label: "Users List",
        key: "/UserList",
        icon: <UnorderedListOutlined />,
        children: [
          {
            label: "Prisioners",
            key: "/prisioners",
          },
          {
            label: "Guards",
            key: "/guards",
          },
        ],
      },
      {
        label: "Notifications",
        key: "/notifications",
        icon: <NotificationOutlined />,
      },
      {
        label: "Workstations",
        key: "/workstations",
        icon: <ToolOutlined />,
      },
      { label: "Profile", key: "/profile", icon: <UserOutlined /> },
      {
        label: "LogOut",
        key: "logout",
        icon: <LogoutOutlined />,
        danger: true,
      },
    ];
  }
  return [
    {
      label: "Dashboard",
      key: "/dashboard",
      icon: <DashboardOutlined />,
    },
    {
      label: "Users List",
      key: "/UserList",
      icon: <UnorderedListOutlined />,
      children: [
        {
          label: "Prisioners",
          key: "/prisioners",
        },
        {
          label: "Guards",
          key: "/guards",
        },
      ],
    },
    {
      label: "Notifications",
      key: "/notifications",
      icon: <NotificationOutlined />,
    },
    {
      label: "Workstations",
      key: "/workstations",
      icon: <ToolOutlined />,
    },
    { label: "Profile", key: "/profile", icon: <UserOutlined /> },
    {
      label: "Login",
      key: "/login",
      icon: <LoginOutlined />,
      style: { color: "red" },
    },
  ];
}

export default App;