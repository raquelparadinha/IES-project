# **REST API Documentation**

This file contains detailed documentation on the API developed to supply the system's request/response mapping.

---

<br/>

## **AreaController > {"/api/area"} <**

<br/><br/>

## **GET**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area

```http
http://localhost:5001/api/area/{area_id}
```

<br/>

### **Description**
Returns a specific area if it exists

<br/>

### **Authorization** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Bearer Token

<br/>

### **Params**

|             |                |                   |
| ----------- | -------------- | ----------------: |
| **area_id** | integer / long | Area's Identifier |

<br/>

### **Response Body**

```json
[
    {
        "id": 1,
        "name": "entrance",
        "capacity": 20,
        "access": false,
        "currentInmateIds": []
    }, 
    ...
]
```

<br/><br/>

## **GET**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area

```http
http://localhost:5001/api/area/{area_id}
```

<br/>

### **Description**

Returns a specific area if it exists

<br/>

### **Authorization** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Bearer Token

<br/>

### **Params**

|             |                |                   |
| ----------- | -------------- | ----------------: |
| **area_id** | integer / long | Area's Identifier |

<br/>

### **Response Body**

```json
[
    {
        "id": 1,
        "name": "entrance",
        "capacity": 20,
        "access": false,
        "currentInmateIds": []
    }, 
    ...
]
```

<br/>
