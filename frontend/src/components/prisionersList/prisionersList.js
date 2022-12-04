import { Button, Modal, Table, Input, Space } from "antd";
import { useState } from "react";
import { DeleteOutlined, EditOutlined } from "@ant-design/icons/lib/icons";

function PrisionersList() {
  const countDown = () => {
    let secondsToGo = 5;

    const modal = Modal.error({
      title: "Edit Invalid, insert only 'true' or 'false'.",
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
  const [isEditing, setisEditing] = useState(false);
  const [editingPrisioner, setEditingPrisioner] = useState(null);
  const [dataSource, setDataSource] = useState([
    {
      id: 1,
      name: "Pareidreds",
      birthdate: "10-06-2001",
      sentence: "21-10-2021",
      duration: 1000,
      workstation: "Ser puta",
      solitary: false.toString(),
    },
    {
      id: 2,
      name: "Soralexina",
      birthdate: "10-06-2001",
      sentence: "21-10-2021",
      duration: 1000,
      workstation: "Ser gay",
      solitary: false.toString(),
    },
    {
      id: 3,
      name: "MancoGordo",
      birthdate: "10-06-2001",
      sentence: "21-10-2021",
      duration: 1000,
      workstation: "Ser drogado",
      solitary: true.toString(),
    },
    {
      id: 4,
      name: "PP_segundo",
      birthdate: "10-06-2001",
      sentence: "21-10-2021",
      duration: 1000,
      workstation: "Ser lindo",
      solitary: false.toString(),
    },
  ]);
  const columns = [
    // prisioner main traits
    { key: 1, title: "ID", dataIndex: "id" },
    { key: 2, title: "Name", dataIndex: "name" },
    { key: 3, title: "Birthdate", dataIndex: "birthdate" },
    { key: 4, title: "Sentece Start", dataIndex: "sentence" },
    { key: 5, title: "Duration (Days?)", dataIndex: "duration" },
    { key: 6, title: "Workstation", dataIndex: "workstation" },
    { key: 7, title: "Solitary", dataIndex: "solitary" },

    {
      key: 8,
      title: "Actions",
      render: (record) => {
        return (
          <>
            {" "}
            <EditOutlined
              onClick={() => {
                onEditPrisioner(record);
              }}
              style={{ color: "green", marginLeft: 12 }}
            />
            <DeleteOutlined
              onClick={() => {
                onDeletePrisioner(record);
              }}
              style={{ color: "red", marginLeft: 12 }}
            />
          </>
        );
      },
    },
  ];

  const onAddPrisioner = () => {
    // Aqui para fazer os adds de novo prisioneiro, está estatico aqui
    const randomNumber = parseInt(Math.random() * 1000);
    const newPrisioner = {
      id: randomNumber,
      name: "Cotovelo da Perna " + randomNumber,
      birthdate: "10-06-2001",
      sentence: "21-10-2021",
      duration: 1000,
      workstation: "Ser lindo",
      solitary: false.toString(),
    };
    setDataSource((pre) => {
      return [...pre, newPrisioner];
    });
  };
  const onDeletePrisioner = (record) => {
    Modal.confirm({
      title: "You REALLY want to delete this priosioner?",
      okText: "Yes",
      okType: "danger",
      cancelText: "No",
      onOk: () => {
        setDataSource((pre) => {
          return pre.filter((prisioner) => prisioner.id !== record.id);
        });
      },
    });
  };
  const onEditPrisioner = (record) => {
    setisEditing(true);
    // ... para fazer copia, tendi nada
    setEditingPrisioner({ ...record });
  };

  const ResetEditing = () => {
    setisEditing(false);
    setEditingPrisioner(null);
  };

  return (
    <div>
      <Table columns={columns} dataSource={dataSource}></Table>
      <Button
        onClick={onAddPrisioner}
        shape={"round"}
        style={{ marginLeft: "45%" }}
        type="primary"
      >
        Add new prisioner
      </Button>
      <Modal
        title="Edit Prisioner"
        visible={isEditing}
        okText="Save"
        okType="primary"
        onCancel={() => {
          ResetEditing();
        }}
        onOk={() => {
          setDataSource((pre) => {
            if (
              editingPrisioner.solitary === "true" ||
              editingPrisioner.solitary === "false"
            ) {
            //   editingPrisioner.solitary = editingPrisioner.solitary === "true";
            //   console.log(editingPrisioner.solitary.type())
              return pre.map((prisioner) => {
                if (prisioner.id === editingPrisioner.id) {
                  return editingPrisioner;
                } else {
                  return prisioner;
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
            value={editingPrisioner?.name}
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                return { ...pre, name: e.target.value };
              });
            }}
          />
          <Input
            addonBefore="Birthdate"
            value={editingPrisioner?.birthdate}
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                return { ...pre, birthdate: e.target.value };
              });
            }}
          />
          <Input
            addonBefore="Sentence Start"
            value={editingPrisioner?.sentence}
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                return { ...pre, sentence: e.target.value };
              });
            }}
          />
          <Input
            addonBefore="Duration"
            value={editingPrisioner?.duration}
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                return { ...pre, duration: e.target.value };
              });
            }}
          />
          <Input
            addonBefore="Workstation"
            value={editingPrisioner?.workstation}
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                return { ...pre, workstation: e.target.value };
              });
            }}
          />
          <Input
            addonBefore="Solitary"
            value={editingPrisioner?.solitary}
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                return { ...pre, solitary: e.target.value.toLowerCase() };
              });
            }}
          />
        </Space>
      </Modal>
      {/* o ? é para dizer que o argumento é opcional */}
    </div>
  );
}

export default PrisionersList;
