import { Button, Modal, Table, Input, Space } from "antd";
import { useState } from "react";
import { DeleteOutlined, EditOutlined } from "@ant-design/icons/lib/icons";

function PrisionersList() {
  const [isEditing, setisEditing] = useState(false);
  const [editingPrisioner, setEditingPrisioner] = useState(null);
  const [dataSource, setDataSource] = useState([
    {
      id: 1,
      name: "Pareidreds",
      email: "Presi@aenossa.com",
      adress: "Rua do Espanha",
    },
    {
      id: 2,
      name: "Soralexina",
      email: "surtado@devops.com",
      adress: "Rua do Sun Tzu",
    },
    {
      id: 3,
      name: "MancoGordo",
      email: "Estiador@aenossa.com",
      adress: "Rua da Paiva",
    },
    {
      id: 4,
      name: "PP_segundo",
      email: "trabalhador@honesto.com",
      adress: "Rua do Choro",
    },
  ]);
  const columns = [
    { key: 1, title: "ID", dataIndex: "id" },
    { key: 2, title: "Name", dataIndex: "name" },
    { key: 3, title: "Email", dataIndex: "email" },
    { key: 4, title: "Adress", dataIndex: "adress" },
    {
      key: 5,
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
      email: "cotovelo@chaminé.com " + randomNumber,
      adress: "Bar do irmão " + randomNumber,
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
      <Button onClick={onAddPrisioner} shape={"round"} style={{marginLeft: "45%"}} type="primary">Add new prisioner</Button>
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
            return pre.map((prisioner) => {
              if (prisioner.id === editingPrisioner.id) {
                return editingPrisioner;
              } else {
                return prisioner;
              }
            });
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
            addonBefore="Email"
            value={editingPrisioner?.email}
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                return { ...pre, email: e.target.value };
              });
            }}
          />
          <Input
            addonBefore="Adress"
            value={editingPrisioner?.adress}
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                return { ...pre, adress: e.target.value };
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
