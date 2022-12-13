import React from 'react';
import { UserOutlined } from '@ant-design/icons';
import { Avatar } from 'antd';
const TheAvatar = () => (
  <Avatar
    size={{
      xs: 24,
      sm: 32,
      md: 40,
      lg: 64,
      xl: 80,
      xxl: 100,
    }}
    icon={<UserOutlined />}
  />
);
export default TheAvatar;