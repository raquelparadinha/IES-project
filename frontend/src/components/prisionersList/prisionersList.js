import { Button, Table } from "antd";
import { useState } from "react";
import { DeleteOutlined, EditOutlined } from "@ant-design/icons/lib/icons";

function PrisionersList() {
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
            <EditOutlined style={{ color: "green", marginLeft: 12 }} />
            <DeleteOutlined style={{ color: "red", marginLeft: 12 }} />
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

  return (
    <div>
      <Table columns={columns} dataSource={dataSource}></Table>
      <Button onClick={onAddPrisioner}>Add new prisioner</Button>
    </div>
  );
}

export default PrisionersList;
