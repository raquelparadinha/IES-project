import React from "react";
import { AndroidOutlined, AppleOutlined } from "@ant-design/icons";
import { Tabs } from "antd";
const DashboardTabs = () => (
  <Tabs
    style={{ display: "flex", flexDirection: "collumn" }}
    defaultActiveKey="1"
    centered
    items={[AppleOutlined, AndroidOutlined].map((Icon, i) => {
      const id = String(i + 1);
      return {
        label: (
          <span>
            <Icon />
            Tab {id}
          </span>
        ),
        key: id,
        children: `Tab ${id}`,
      };
    })}
  />
);
export default DashboardTabs;
