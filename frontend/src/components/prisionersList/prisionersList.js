import {
  Button,
  Modal,
  Table,
  Input,
  Space,
  Checkbox,
  DatePicker,
  Form,
  Card,
} from "antd";
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
  const [isAdding, setisAdding] = useState(false);
  const [editingPrisioner, setEditingPrisioner] = useState(null);
  //console.log(dataSource);
  const columns = [
    // prisioner main traits
    { key: 1, title: "ID", dataIndex: "id", className: "custom-row" },
    { key: 2, title: "Name", dataIndex: "name" },
    { key: 3, title: "Birthdate", dataIndex: "birthDate" },
    { key: 4, title: "Sentece Start", dataIndex: "entryDate" },
    { key: 5, title: "Sentence End", dataIndex: "sentenceEnd" },

    {
      key: 6,
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

  const onAddPrisioner = () => {
    setisAdding(true);
  };

  const ResetEditing = () => {
    setisEditing(false);
    setEditingPrisioner(null);
  };

  const ResetAdding = () => {
    setisAdding(false);
    form.resetFields();
  };

  function editPrisioner(Edited_prisioner) {
    console.log(typeof Edited_prisioner);
    console.log(Edited_prisioner.id);
    try {
      axios.put(
        "http://localhost:5001/api/inmate/" + Edited_prisioner.id,
        Edited_prisioner
      );
    } catch (error) {
      console.log("Deu pylance");
      return false;
    }
    return true;
  }

  const AddPrisioner = (new_prisioner_info) => {
    // Aqui para fazer os adds de novo prisioneiro, está estatico aqui
    ResetAdding();
    //console.log(new_prisioner_info.solitary);
    let new_prisioner;
    try {
      new_prisioner = {
        id: 0,
        name: new_prisioner_info.name,
        birthDate: new_prisioner_info.birthdate.format(dateFormat),
        entryDate: new_prisioner_info.sentence[0].format(dateFormat),
        sentenceEnd: new_prisioner_info.sentence[1].format(dateFormat),
        solitary: new_prisioner_info.solitary,
        healthLogId: 0,
        moveLogIds: [],
        workLogIds: [],
      };
      console.log(new_prisioner);
      try {
        axios.post("http://localhost:5001/api/inmate", new_prisioner);
      } catch (error) {
        console.log("Deu pylance");
        Modal.error({
          title: "Add Error",
          content: `Prisioner was not added to the database due to and error.`,
          okType: "danger",
        });
        return false;
      }
      setDataSource((pre) => {
        return [...pre, new_prisioner];
      });
      fetchData();
      Modal.success({
        title: "Add Successful",
        content: `Prisioner added with success.`,
        okType: "ghost",
      });
      return true;
    } catch (error) {
      console.log(error);
      Modal.error({
        title: "Add Error",
        content: `Prisioner was not added to the database due to and error.`,
        okType: "danger",
      });
    }
  };

  const [form] = Form.useForm();
  console.log(dataSource);
  return (
    <div style={{ textAlign: "center" }}>
      <Card
        title={<div style={{ color: "#12494c" }}>Prisioners</div>}
        style={{ backgroundColor: "#D6E4E5" }}
      >
        <Table
          columns={columns}
          dataSource={dataSource}
          pagination={{ defaultPageSize: 13 }}
          rowClassName={"custom-row"}
          style={{ color: "#8da6a8" }}
        ></Table>
        <Button
          onClick={() => onAddPrisioner()}
          shape={"round"}
          style={{ backgroundColor: "#497174" }}
          type="primary"
        >
          Add new prisioner
        </Button>
      </Card>
      <Modal
        title={<div style={{ color: "#12494c" }}>Edit Prisioner</div>}
        open={isEditing}
        okText="Save"
        okType="primary"
        onCancel={() => {
          ResetEditing();
        }}
        onOk={() => {
          setDataSource((pre) => {
            return pre.map((prisioner) => {
              if (prisioner.id === editingPrisioner.id) {
                const bool = editPrisioner(editingPrisioner);
                if (bool) {
                  Modal.success({
                    title: "Edit Successful",
                    content: `Prisioner changed with success.`,
                    okType: "ghost",
                  });
                  return editingPrisioner;
                } else {
                  Modal.error({
                    title: "Edit Error",
                    content: `An error happened while changing the prisioner.`,
                    okType: "danger",
                  });
                  return prisioner;
                }
              } else {
                return prisioner;
              }
            });
          });
          ResetEditing();
        }}
      >
        <Space direction="vertical" style={{ width: "100%", height: "100%" }}>
          <Input
            addonBefore={<div style={{ color: "#12494c" }}> Name</div>}
            value={editingPrisioner?.name}
            onChange={(e) => {
              setEditingPrisioner((pre) => {
                return { ...pre, name: e.target.value };
              });
            }}
          />
          {<div style={{ color: "#12494c" }}>Sentece: </div>}
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
          {<div style={{ color: "#12494c" }}>Solitary: </div>}
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
      <Modal
        title="Add Prisioner"
        open={isAdding}
        onCancel={() => ResetAdding()}
        onOk={form.submit}
      >
        <Form form={form} onFinish={AddPrisioner}>
          <Form.Item
            name="name"
            rules={[
              {
                required: true,
                message: "Please input the name!",
              },
            ]}
          >
            <Input addonBefore="Name" />
          </Form.Item>
          Birthdate:
          <Form.Item name="birthdate">
            <DatePicker
              format={dateFormat}
              placeholder={"Birthdate"}
            ></DatePicker>
          </Form.Item>
          Sentence:
          <Form.Item name="sentence">
            <RangePicker
              showTime
              format={dateFormat}
              placeholder={["Sentence Start", "Sentence End"]}
            />
          </Form.Item>
          Solitary:
          <Form.Item name="solitary" valuePropName="checked">
            <Checkbox name="Solitary" />
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
}

export default PrisionersList;
