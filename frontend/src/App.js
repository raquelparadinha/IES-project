import "antd/dist/reset.css";
import "./App.css";
import { Menu } from "antd";
import { Route, Routes, useNavigate } from "react-router-dom";
import {
  HomeOutlined,
  DashboardOutlined,
  UnorderedListOutlined,
  UserOutlined,
  PoweroffOutlined,
} from "@ant-design/icons/lib/icons";

function App() {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        flex: 1,
        height: "100vh",
      }}
    >
      <Header />
      <SideMenu />
      <Footer />
    </div>
  );
}

export function Header() {
  return (
    <div
      style={{
        height: 60,
        backgroundColor: "orange",
        color: "black",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        fontWeight: "bold",
      }}
    >
      Header
    </div>
  );
}

export function Footer() {
  return (
    <div
      style={{
        height: 60,
        backgroundColor: "orange",
        color: "black",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        fontWeight: "bold",
      }}
    >
      Footer
    </div>
  );
}

export function SideMenu() {
  const navigate = useNavigate();
  return (
    <div style={{ display: "flex", flexDirection: "row", flex: 1 }}>
      <Menu
        onClick={({ key }) => {
          if (key === "signout") {
            // TODO, não fiz e continuo lindo
          } else {
            navigate(key);
          }
        }}
        defaultSelectedKeys={[window.location.pathname]}
        items={[
          { label: "Home", key: "/", icon: <HomeOutlined /> },
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
          { label: "Profile", key: "/profile", icon: <UserOutlined /> },
          {
            label: "SignOut",
            key: "signout",
            icon: <PoweroffOutlined />,
            danger: true,
          },
        ]}
      ></Menu>
      {/* <Content /> */}
    </div>
  );
}

function Content() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<div>Home</div>}></Route>
        <Route path="/dashboard" element={<div>Dashboard</div>}></Route>
        <Route path="/userlist" element={<div>User List</div>}></Route>
        <Route path="/profile" element={<div>Profile</div>}></Route>
        <Route path="/signout" element={<div>Sign Out</div>}></Route>
      </Routes>
    </div>
  );
}

export default App;
