import { Button, Modal, Table, Input, Space } from "antd";
import { useState, useEffect } from "react";
import {
  DeleteOutlined,
  EditOutlined,
  EyeOutlined,
} from "@ant-design/icons/lib/icons";
import axios from "axios";
import { useNavigate } from "react-router";

function GuardsList() {
  const countDown = () => {
    let secondsToGo = 5;

    const modal = Modal.error({
      title: "Solitary state invalid, insert only 'true' or 'false'.",
      content: `This modal will be destroyed after ${secondsToGo} second.`,
      okType: "danger",
    });

    const timer = setInterval(() => {
      secondsToGo -= 1;
      modal.update({
        content: `This modal will be destroyed after ${secondsToGo} second.`,
      });
    }, 1000);

    setTimeout(() => {
      clearInterval(timer);
      modal.destroy();
    }, secondsToGo * 1000);
  };

  const [dataSource, setDataSource] = useState();
  const fetchData = () => {
    try {
      return axios
        .get("http://localhost:5001/api/guard")
        .then((response) => setDataSource(response.data));
    } catch (error) {
      fetchData();
    }
  };

  useEffect(() => {
    setInterval(fetchData(), 300000); // The function will be called
  }, []);

  const navigate = useNavigate();
  const [isEditing, setisEditing] = useState(false);
  const [editingGuard, setEditingGuard] = useState(null);

  const columns = [
    // guard main traits
    { key: 1, title: "ID", dataIndex: "id" },
    { key: 2, title: "Name", dataIndex: "name" },
    { key: 3, title: "Email", dataIndex: "email" },
    { key: 4, title: "Phone", dataIndex: "phone" },
    { key: 5, title: "Birthdate", dataIndex: "birthdate" },
    { key: 6, title: "AreaID", dataIndex: "areaId" },

    {
      key: 7,
      title: "Actions",
      render: (record) => {
        return (
          <>
            <EyeOutlined
              onClick={() => {
                navigate("/guards/" + record.id);
              }}
              style={{ color: "blue", marginLeft: 12 }}
            />
            <EditOutlined
              onClick={() => {
                onEditGuard(record);
              }}
              style={{ color: "green", marginLeft: 12 }}
            />
            <DeleteOutlined
              onClick={() => {
                onDeleteGuard(record);
              }}
              style={{ color: "red", marginLeft: 12 }}
            />
          </>
        );
      },
    },
  ];

  const onAddGuard = () => {
    // Aqui para fazer os adds de novo guarda, está estatico aqui
    const randomNumber = parseInt(Math.random() * 1000);
    const newGuard = {
      id: randomNumber,
      name: "Cotovelo da Perna " + randomNumber,
      birthdate: "10-06-2001",
      sentence: "21-10-2021",
      duration: 1000,
      workstation: "Ser lindo",
      solitary: false.toString(),
    };
    setDataSource((pre) => {
      return [...pre, newGuard];
    });
  };
  const onDeleteGuard = (record) => {
    Modal.confirm({
      title: "You REALLY want to delete this guard?",
      okText: "Yes",
      okType: "danger",
      cancelText: "No",
      onOk: () => {
        setDataSource((pre) => {
          return pre.filter((guard) => guard.id !== record.id);
        });
      },
    });
  };
  const onEditGuard = (record) => {
    setisEditing(true);
    // ... para fazer copia, tendi nada
    setEditingGuard({ ...record });
  };

  const ResetEditing = () => {
    setisEditing(false);
    setEditingGuard(null);
  };

  return (
    <div>
      <Table
        columns={columns}
        dataSource={dataSource}
        pagination={{ defaultPageSize: 14 }}
      ></Table>
      <Button
        onClick={onAddGuard}
        shape={"round"}
        style={{ marginLeft: "45%" }}
        type="primary"
      >
        Add new guard
      </Button>
      <Modal
        title="Edit Guard"
        open={isEditing}
        okText="Save"
        okType="primary"
        onCancel={() => {
          ResetEditing();
        }}
        onOk={() => {
          // alterar o guarda, estático :)
          setDataSource((pre) => {
            if (true) {
              return pre.map((guard) => {
                if (guard.id === editingGuard.id) {
                  return editingGuard;
                } else {
                  return guard;
                }
              });
            } else {
              countDown();
              return pre;
            }
          });
          ResetEditing();
        }}
      >
        {" "}
        <Space direction="vertical" style={{ width: "100%", height: "100%" }}>
          <Input
            addonBefore="Name"
            value={editingGuard?.name}
            onChange={(e) => {
              setEditingGuard((pre) => {
                return { ...pre, name: e.target.value };
              });
            }}
          />
          <Input
            addonBefore="Email"
            value={editingGuard?.email}
            onChange={(e) => {
              setEditingGuard((pre) => {
                return { ...pre, email: e.target.value };
              });
            }}
          />
          <Input
            addonBefore="Phone"
            value={editingGuard?.phone}
            onChange={(e) => {
              setEditingGuard((pre) => {
                return { ...pre, phone: e.target.value };
              });
            }}
          />
          <Input
            addonBefore="AreadId"
            value={editingGuard?.areaId}
            onChange={(e) => {
              setEditingGuard((pre) => {
                return { ...pre, areaId: e.target.value };
              });
            }}
          />
        </Space>
      </Modal>
      {/* o ? é para dizer que o argumento é opcional */}
    </div>
  );
}

export default GuardsList;
