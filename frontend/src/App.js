import "antd/dist/reset.css";
import "./App.css";
import { Menu } from "antd";
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
// import { Logged, SetLogged } from "./components/Login/Login";
import Dashboard from "./components/Dashboard/dashboard";
import GuardsList from "./components/GuardsList/guardsList";
import Notifications from "./components/Notifications/notifications";
import Prisioner_Profile from "./components/Profile/prisioner_profile";
import Guard_Profile from "./components/Profile/guard_profile";
import axios from "axios";
import { useEffect, useState } from "react";
import Workstations from "./components/Workstations/workstations";
import EventBus from "./common/EventBus";
import AuthService from "./services/auth.service";

// const dashboardData = [
//   {
//     label: "Dashboard",
//     key: "/dashboard",
//     icon: <DashboardOutlined />,
//   },
//   {
//     label: "Users List",
//     key: "/UserList",
//     icon: <UnorderedListOutlined />,
//     children: [
//       {
//         label: "Prisioners",
//         key: "/prisioners",
//       },
//       {
//         label: "Guards",
//         key: "/guards",
//       },
//     ],
//   },
//   {
//     label: "Notifications",
//     key: "/notifications",
//     icon: <NotificationOutlined />,
//   },
//   {
//     label: "Workstations",
//     key: "/workstations",
//     icon: <ToolOutlined />,
//   },
//   { label: "Profile", key: "/profile", icon: <UserOutlined /> },
//   {
//     label: "Login",
//     key: "/login",
//     icon: <LoginOutlined />,
//     style: { color: "red" },
//   },
// ] 

function App() {
  const [showAdminBoard, setShowAdminBoard] = useState(false);
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
      setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));
    }

    EventBus.on("logout", () => {
      logOut();
    });

    return () => {
      EventBus.remove("logout");
    };
  }, []);

  const logOut = () => {
    AuthService.logout();
    setShowAdminBoard(false);
    setCurrentUser(undefined);
  };

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        flex: 1,
        height: "100vh",
      }}
    >
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
      }}
    >
      <Menu
        onClick={({ key }) => {
          if (key === "logout") {
            // SetLogged(false);
            App.logOut();
            navigate("/dashboard");
          } else {
            navigate(key);
          }
        }}
        defaultSelectedKeys={[window.location.pathname]}
        items={islogged(App.showAdminBoard)}
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
        <Route path="/profile" element={<Prisioner_Profile />}></Route>
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
