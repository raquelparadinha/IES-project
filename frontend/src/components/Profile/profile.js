import { Card } from "antd";
import React from "react";
import TheAvatar from "./Avatar/avatar";

const Profile = () => (
  <div
    style={{
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      height: "90%",
    }}
  >
    <Card>
      <TheAvatar />
    </Card>
  </div>
);
export default Profile;
