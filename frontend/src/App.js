import { DashboardOutlined, LoginOutlined, LogoutOutlined, NotificationOutlined, ToolOutlined, UnorderedListOutlined, UserOutlined } from "@ant-design/icons/lib/icons";
import { Menu } from "antd";
import "antd/dist/reset.css";
import { Navigate, Route, Routes, useNavigate } from "react-router-dom";
import "./App.css";
//import Logo from "./images/cartoon-pug-dog-in-prison-costume-with-sign-vector.jpeg";
import axios from "axios";
import { useEffect, useState } from "react";
import Dashboard from "./components/Dashboard/dashboard";
import GuardsList from "./components/GuardsList/guardsList";
import Login, { Logged, SetLogged } from "./components/Login/Login";
import MapaEstricado from "./components/MapaEstricado/MapaEstricado";
import Notifications from "./components/Notifications/notifications";
import PrisionersList from "./components/prisionersList/prisionersList";
import Guard_Profile from "./components/Profile/guard_profile";
import Prisioner_Profile from "./components/Profile/prisioner_profile";
import Workstations from "./components/Workstations/workstations";

function App() {
  const [dataSource, setDataSource] = useState();
  const fetchData = () => {
    try {
      return axios.get("http://localhost:5001/api/alert").then((response) => setDataSource(response.data));
    } catch {
      console.log("Deu pylance");
      fetchData();
    }
  };
  useEffect(() => {
    const interval = setInterval(() => {
      fetchData();
    }, 30000);
    return () => clearInterval(interval);
  });
  fetchData();
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        flex: 1,
        height: "100vh",
      }}>
      <SideMenu />
    </div>
  );
}

// export function Header() {
//   return (
//     <div
//       style={{
//         height: 60,
//         backgroundColor: "orange",
//         color: "black",
//         display: "flex",
//         justifyContent: "center",
//         alignItems: "center",
//         fontWeight: "bold",
//       }}
//     >
//       <img src={Logo} alt="logo" style={{ height: "100%" }} />
//     </div>
//   );
// }

// export function Footer() {
//   return (
//     <div
//       style={{
//         height: 60,
//         backgroundColor: "orange",
//         color: "black",
//         display: "flex",
//         justifyContent: "center",
//         alignItems: "center",
//         fontWeight: "bold",
//       }}
//     >
//       Footer
//     </div>
//   );
// }

export function SideMenu() {
  const navigate = useNavigate();
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "row",
        flex: 1,
      }}>
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
        style={{ backgroundColor: "#EFF5F5" }}></Menu>
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
          }></Route>
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
          }></Route>
        <Route path="/notifications" element={<Notifications />}></Route>
        <Route path="/workstations" element={<Workstations />}></Route>
        <Route path="/map" element={<MapaEstricado />}></Route>
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
      { label: "Map", key: "/map", icon: <UserOutlined /> },
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
    { label: "Map", key: "/map", icon: <UserOutlined /> },
    {
      label: "Login",
      key: "/login",
      icon: <LoginOutlined />,
      style: { color: "red" },
    },
  ];
}

export default App;
