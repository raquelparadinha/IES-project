import {
  ControlOutlined,
  DashboardOutlined,
  LoginOutlined,
  LogoutOutlined,
  MedicineBoxOutlined,
  NotificationOutlined,
  TeamOutlined,
  ToolOutlined,
  UnorderedListOutlined,
  UserOutlined,
} from "@ant-design/icons/lib/icons";
import { faHandcuffs } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Menu, notification } from "antd";
import "antd/dist/reset.css";
import { Navigate, Route, Routes, useNavigate } from "react-router-dom";
import "./App.css";
//import Logo from "./images/cartoon-pug-dog-in-prison-costume-with-sign-vector.jpeg";
import axios from "axios";
import { useEffect, useState } from "react";
import Dashboard from "./components/Dashboard/dashboard";
import GuardsList from "./components/GuardsList/guardsList";
import Login from "./components/Login/Login";
import MapaEstricado from "./components/MapaEstricado/MapaEstricado";
import Notifications, { back_colors, colors, icons } from "./components/Notifications/notifications";
import PrisionersList from "./components/prisionersList/prisionersList";
import Guard_Profile from "./components/Profile/guard_profile";
import Prisioner_Profile from "./components/Profile/prisioner_profile";
import Profile from "./components/Profile/profile";
import Workstations from "./components/Workstations/workstations";

function App() {
  const [api, contextHolder] = notification.useNotification();
  const [max_id, setMaxId] = useState(0);
  const fetchData = () => {
    try {
      return axios.get("http://localhost:5001/api/alert/new").then((response) => {
        response.data.forEach((message_) => {
          api.open({
            duration: 2,
            message: message_.type.charAt(0).toUpperCase() + message_.type.slice(1),
            description: message_.information,
            icon: (
              <div
                style={{
                  color: colors[`${message_.type}`],
                }}>
                {icons[`${message_.type}`]}
              </div>
            ),
          });
        });
      });
    } catch {
      console.log("Deu pylance");
      fetchData();
    }
  };
  useEffect(() => {
    const interval = setInterval(() => {
      fetchData();
    }, 1000);
    return () => clearInterval(interval);
  });

  const [currentUser, setCurrentUser] = useState(getCurrentUser());
  function checkLogin() {
    if (currentUser) {
      return contextHolder;
    } else {
      return null;
    }
  }

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        flex: 1,
        height: "100vh",
      }}>
      {checkLogin()}
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
      }}>
      <Menu
        onClick={({ key }) => {
          if (key === "logout") {
            logout();
            navigate("/login");
          } else {
            navigate(key);
          }
        }}
        defaultSelectedKeys={[window.location.pathname]}
        items={islogged()}
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
        <Route path="/" element={<Navigate to="/login" />}></Route>
        <Route
          path="/dashboard"
          element={
            <div>
              <Dashboard />
            </div>
          }></Route>
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
        <Route path="/profile" element={<Profile />}></Route>
        <Route path="/login" element={<Login />}></Route>
      </Routes>
    </div>
  );
}
function islogged() {
  const currentUser = getCurrentUser();
  if (!currentUser) {
    return [];
  } else if (currentUser.roles.includes("ROLE_ADMIN")) {
    return [
      {
        label: "Prisioners",
        key: "/prisioners",
        icon: <FontAwesomeIcon icon={faHandcuffs} style={{ height: "14px", width: "14px" }} />,
      },
      {
        label: "Guards",
        key: "/guards",
        icon: <TeamOutlined />,
      },
      {
        label: "Health",
        key: "/dashboard",
        icon: <MedicineBoxOutlined />,
      },
      {
        label: "Workstations",
        key: "/workstations",
        icon: <ToolOutlined />,
      },
      {
        label: "Notifications",
        key: "/notifications",
        icon: <NotificationOutlined />,
      },
      { label: "Map", key: "/map", icon: <ControlOutlined /> },
      {
        label: "Profile",
        key: "profile",
        icon: <UserOutlined />,
        danger: true,
      },
      {
        label: "Logout",
        key: "logout",
        icon: <LogoutOutlined />,
        danger: true,
      },
    ];
  } else if (currentUser.roles.includes("ROLE_USER")) {
    return [
      {
        label: "Prisioners",
        key: "/prisioners",
        icon: <FontAwesomeIcon icon={faHandcuffs} style={{ height: "14px", width: "14px" }} />,
      },
      {
        label: "Health",
        key: "/dashboard",
        icon: <MedicineBoxOutlined />,
      },
      {
        label: "Workstations",
        key: "/workstations",
        icon: <ToolOutlined />,
      },
      {
        label: "Notifications",
        key: "/notifications",
        icon: <NotificationOutlined />,
      },
      { label: "Map", key: "/map", icon: <ControlOutlined /> },
      {
        label: "Profile",
        key: "profile",
        icon: <UserOutlined />,
        danger: true,
      },
      {
        label: "Logout",
        key: "logout",
        icon: <LogoutOutlined />,
        danger: true,
      },
    ];
  }
}

export default App;

const logout = () => {
  sessionStorage.removeItem("user");
};

export const getCurrentUser = () => {
  return JSON.parse(sessionStorage.getItem("user"));
};
