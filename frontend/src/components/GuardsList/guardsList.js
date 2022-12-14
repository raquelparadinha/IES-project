import {
  Button,
  Modal,
  Table,
  Input,
  Space,
  DatePicker,
  Form,
  Card,
} from "antd";
import { useState, useEffect, useRef } from "react";
import {
  DeleteOutlined,
  EditOutlined,
  EyeOutlined,
  SearchOutlined,
} from "@ant-design/icons/lib/icons";
import axios from "axios";
import { useNavigate } from "react-router";
import dayjs from "dayjs";
import customParseFormat from "dayjs/plugin/customParseFormat";
import Highlighter from "react-highlight-words";
dayjs.extend(customParseFormat);

const dateFormat = "MM/DD/YYYY";

function GuardsList() {
  const [searchText, setSearchText] = useState("");
  const [searchedColumn, setSearchedColumn] = useState("");
  const searchInput = useRef(null);
  const handleSearch = (selectedKeys, confirm, dataIndex) => {
    confirm();
    setSearchText(selectedKeys[0]);
    setSearchedColumn(dataIndex);
  };
  const handleReset = (clearFilters) => {
    clearFilters();
    setSearchText("");
  };
  const getColumnSearchProps = (dataIndex) => ({
    filterDropdown: ({
      setSelectedKeys,
      selectedKeys,
      confirm,
      clearFilters,
      close,
    }) => (
      <div
        style={{
          padding: 8,
        }}
        onKeyDown={(e) => e.stopPropagation()}
      >
        <Input
          ref={searchInput}
          placeholder={`Search ${dataIndex}`}
          value={selectedKeys[0]}
          onChange={(e) =>
            setSelectedKeys(e.target.value ? [e.target.value] : [])
          }
          onPressEnter={() => handleSearch(selectedKeys, confirm, dataIndex)}
          style={{
            marginBottom: 8,
            display: "block",
          }}
        />
        <Space>
          <Button
            type="primary"
            onClick={() => handleSearch(selectedKeys, confirm, dataIndex)}
            icon={<SearchOutlined />}
            size="small"
            style={{
              width: 90,
            }}
          >
            Search
          </Button>
          <Button
            onClick={() => clearFilters && handleReset(clearFilters)}
            size="small"
            style={{
              width: 90,
            }}
          >
            Reset
          </Button>
          <Button
            type="link"
            size="small"
            onClick={() => {
              confirm({
                closeDropdown: false,
              });
              setSearchText(selectedKeys[0]);
              setSearchedColumn(dataIndex);
            }}
          >
            Filter
          </Button>
          <Button
            type="link"
            size="small"
            onClick={() => {
              close();
            }}
          >
            close
          </Button>
        </Space>
      </div>
    ),
    filterIcon: (filtered) => (
      <SearchOutlined
        style={{
          color: filtered ? "#1890ff" : undefined,
        }}
      />
    ),
    onFilter: (value, record) =>
      record[dataIndex].toString().toLowerCase().includes(value.toLowerCase()),
    onFilterDropdownOpenChange: (visible) => {
      if (visible) {
        setTimeout(() => searchInput.current?.select(), 100);
      }
    },
    render: (text) =>
      searchedColumn === dataIndex ? (
        <Highlighter
          highlightStyle={{
            backgroundColor: "#ffc069",
            padding: 0,
          }}
          searchWords={[searchText]}
          autoEscape
          textToHighlight={text ? text.toString() : ""}
        />
      ) : (
        text
      ),
  });

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
  const [isAdding, setisAdding] = useState(false);
  const [editingGuard, setEditingGuard] = useState(null);

  const columns = [
    // guard main traits
    { key: 1, title: "ID", dataIndex: "id", ...getColumnSearchProps("id") },
    {
      key: 2,
      title: "Name",
      dataIndex: "name",
      ...getColumnSearchProps("name"),
    },
    {
      key: 3,
      title: "Email",
      dataIndex: "email",
      ...getColumnSearchProps("email"),
    },
    {
      key: 4,
      title: "Phone",
      dataIndex: "phone",
      ...getColumnSearchProps("phone"),
    },
    {
      key: 5,
      title: "Birthdate",
      dataIndex: "birthdate",
      ...getColumnSearchProps("birthdate"),
    },
    {
      key: 6,
      title: "AreaID",
      dataIndex: "areaId",
      ...getColumnSearchProps("areaId"),
    },

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

  const onAddGuard = () => {
    setisAdding(true);
  };

  const ResetEditing = () => {
    setisEditing(false);
    setEditingGuard(null);
  };

  const ResetAdding = () => {
    setisAdding(false);
  };

  function editGuard(Edited_guard) {
    try {
      axios.put(
        "http://localhost:5001/api/guard/" + Edited_guard.id,
        Edited_guard
      );
    } catch (error) {
      console.log("Deu pylance");
      return false;
    }
    return true;
  }

  const AddGuard = (new_guard_info) => {
    // Aqui para fazer os adds de novo guarda, está estatico aqui
    ResetAdding();
    console.log(new_guard_info);
    let new_guard;
    try {
      new_guard = {
        id: 0,
        name: new_guard_info.name,
        birthdate: new_guard_info.birthdate.format(dateFormat),
        email: new_guard_info.email,
        phone: new_guard_info.phone,
        areaId: new_guard_info.areaId,
        password: new_guard_info.name + new_guard_info.id,
        roles: [],
      };
      console.log(new_guard);
      try {
        axios.post("http://localhost:5001/api/guard", new_guard);
      } catch (error) {
        console.log("Deu pylance");
        Modal.error({
          title: "Add Error",
          content: `Guard was not added to the database due to and error.`,
          okType: "ghost",
        });
        return false;
      }
      setDataSource((pre) => {
        return [...pre, new_guard];
      });
      fetchData();
      Modal.success({
        title: "Add Successful",
        content: `Guard added with success.`,
        okType: "ghost",
      });
      return true;
    } catch (error) {
      Modal.error({
        title: "Add Error",
        content: `Guard was not added to the database due to and error.`,
        okType: "ghost",
      });
    }
  };

  const [form] = Form.useForm();
  console.log(dataSource);
  return (
    <div style={{ textAlign: "center" }}>
      <Card
        title={<div style={{ color: "#12494c" }}>Guards</div>}
        style={{ backgroundColor: "#D6E4E5" }}
      >
        <Table
          columns={columns}
          dataSource={dataSource}
          pagination={{ defaultPageSize: 13 }}
          rowClassName={"custom-row"}
        ></Table>
        <Button
          onClick={onAddGuard}
          shape={"round"}
          style={{ marginLeft: "1%", backgroundColor: "#497174" }}
          type="primary"
        >
          Add new guard
        </Button>
      </Card>
      <Modal
        title="Edit Guard"
        open={isEditing}
        okText="Save"
        okType="primary"
        onCancel={() => {
          ResetEditing();
        }}
        onOk={() => {
          setDataSource((pre) => {
            return pre.map((guard) => {
              if (guard.id === editingGuard.id) {
                const bool = editGuard(editingGuard);
                if (bool) {
                  Modal.success({
                    title: "Edit Successful",
                    content: `Prisioner changed with success.`,
                    okType: "ghost",
                  });
                  return editingGuard;
                } else {
                  Modal.error({
                    title: "Edit Error",
                    content: `An error happened while changing the guard.`,
                    okType: "danger",
                  });
                  return guard;
                }
              } else {
                return guard;
              }
            });
          });
          ResetEditing();
        }}
      >
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
      <Modal
        title="Add Guard"
        open={isAdding}
        onCancel={() => ResetAdding()}
        onOk={form.submit}
      >
        <Form form={form} onFinish={AddGuard}>
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
          <Form.Item
            name="email"
            rules={[
              {
                required: true,
                message: "Please input Email!",
              },
            ]}
          >
            <Input addonBefore="Email" />
          </Form.Item>
          <Form.Item
            name="phone"
            rules={[
              {
                required: true,
                message: "Please input Phone!",
              },
            ]}
          >
            <Input addonBefore="Phone" />
          </Form.Item>
          <Form.Item
            name="areaId"
            rules={[
              {
                required: true,
                message: "Please input Area ID!",
              },
            ]}
          >
            <Input addonBefore="Area ID" />
          </Form.Item>
          Birthdate:
          <Form.Item name="birthdate">
            <DatePicker
              format={dateFormat}
              placeholder={"Birthdate"}
            ></DatePicker>
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
}

export default GuardsList;
