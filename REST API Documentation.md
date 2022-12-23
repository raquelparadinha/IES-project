# **REST API Documentation**

This file contains detailed documentation on the API developed to supply the system's request/response mapping.

<br/>

## **AreaController : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area**

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area

```
http://localhost:5001/api/area/{area_id}
```

### **Description**

Returns a specific area if it exists

### **Authorization**

Bearer Token

### **Params**

| URL         | Input Type     | Description       |
| :---------- | :------------: | ----------------: |
| **area_id** | integer / long | Area's Identifier |

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
    //...
]
```

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area