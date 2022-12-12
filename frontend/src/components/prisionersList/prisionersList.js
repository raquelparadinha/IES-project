import { Button, Modal, Table, Input, Space, Checkbox, DatePicker } from "antd";
import { useState, useEffect } from "react";
import {
  DeleteOutlined,
  EditOutlined,
  EyeOutlined,
} from "@ant-design/icons/lib/icons";
import axios from "axios";
import { useNavigate } from "react-router";
import dayjs from "dayjs";
import customParseFormat from "dayjs/plugin/customParseFormat";
dayjs.extend(customParseFormat);

const { RangePicker } = DatePicker;
const dateFormat = "MM/DD/YYYY";

function PrisionersList() {
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
    console.log("dataaa");
    return axios
      .get("http://localhost:5001/api/inmate")
      .then((response) => setDataSource(response.data));
  };

  useEffect(() => {
    setInterval(fetchData(), 300000); // The function will be called
  }, []);

  const navigate = useNavigate();
  const [isEditing, setisEditing] = useState(false);
  const [editingPrisioner, setEditingPrisioner] = useState(null);
  //console.log(dataSource);
  const columns = [
    // prisioner main traits
    { key: 1, title: "ID", dataIndex: "id" },
    { key: 2, title: "Name", dataIndex: "name" },
    { key: 3, title: "Birthdate", dataIndex: "birthDate" },
    { key: 4, title: "Sentece Start", dataIndex: "entryDate" },
    { key: 5, title: "Sentence End", dataIndex: "sentenceEnd" },
    { key: 6, title: "Workstation", dataIndex: "workstationId" },

    {
      key: 7,
      title: "Actions",
      render: (record) => {
        // console.log(record.id)
        return (
          <>
            <EyeOutlined
              onClick={() => {
                navigate("/prisioners/" + record.id);
              }}
              style={{ color: "blue", marginLeft: 12 }}
            />
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
      <Table
        columns={columns}
        dataSource={dataSource}
        pagination={{ defaultPageSize: 14 }}
      ></Table>
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
        open={isEditing}
        okText="Save"
        okType="primary"
        onCancel={() => {
          ResetEditing();
        }}
        onOk={() => {
          setDataSource((pre) => {
            if (true) {
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
          {/* <Input
            addonBefore="Sentence Start"
            value={editingPrisioner?.entryDate}
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                return { ...pre, sentence: e.target.value };
              });
            }}
          />

          <Input
            addonBefore="Sentence End"
            value={editingPrisioner?.sentenceEnd}
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                return { ...pre, sentenceEnd: e.target.value };
              });
            }}
          /> */}
          <RangePicker
            showTime
            defaultValue={[
              dayjs(editingPrisioner?.entryDate, dateFormat),
              dayjs(editingPrisioner?.sentenceEnd, dateFormat),
            ]}
            format={dateFormat}
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                console.log("E: ", e);
                if (e === null) {
                  return { ...pre };
                } else {
                  console.log(pre);
                  console.log(e[0].format(dateFormat));
                  console.log(e[1].format(dateFormat));
                  return {
                    ...pre,
                    entryDate: e[0].format(dateFormat),
                    sentenceEnd: e[1].format(dateFormat),
                  };
                }
              });
            }}
          />
          <Input
            addonBefore="Workstation"
            value={editingPrisioner?.workstationId}
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                return { ...pre, workstationId: e.target.value };
              });
            }}
          />
          <Checkbox
            checked={editingPrisioner?.solitary}
            addonBefore="Solitary"
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                return { ...pre, solitary: e.target.checked };
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
