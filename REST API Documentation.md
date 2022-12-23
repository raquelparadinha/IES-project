# **REST API Documentation**

This file contains detailed documentation on the API developed to supply the system's request/response mapping.

<br/>

## **AreaController : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area**

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area

```
http://localhost:5001/api/area
```

### **Description**

Returns a all the areas in the system.

### **Authorization**

Bearer Token obtained after authentication.

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
    {
        "id": 2,
        "name": "visitorwing",
        "capacity": 20,
        "access": false,
        "currentInmateIds": []
    },
]
```

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area/{area_id}

```
http://localhost:5001/api/area/{area_id}
```

### **Description**

Returns an area if it stored inside the system.

### **Authorization**

Bearer Token obtained after authentication.

### **Params**

| URL         |   Input Type   |       Description |
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
    }
]
```

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area/{area_id}/access

```
http://localhost:5001/api/area/{area_id}/access
```

### **Description**

Changes the the access to an area if it stored in the system and returns that specify area updated.

### **Authorization**

Bearer Token obtained after authentication

### **Params**

| URL         |   Input Type   |       Description |
| :---------- | :------------: | ----------------: |
| **area_id** | integer / long | Area's Identifier |

### **Response Body**

```json
[
    {
        "id": 1,
        "name": "entrance",
        "capacity": 20,
        "access": true,
        "currentInmateIds": []
    }
]
```

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area/{area_id}/inmates

```
http://localhost:5001/api/area/{area_id}/access
```

### **Description**

Returns the list of inmates inside an area if it is stored in the system, ordered by their danger level.

### **Authorization**

Bearer Token obtained after authentication

### **Params**

| URL         |   Input Type   |       Description |
| :---------- | :------------: | ----------------: |
| **area_id** | integer / long | Area's Identifier |

### **Response Body**

```json
[
    {
        "id": 1053,
        "name": "Bailie Gendrich",
        "areaId": 4,
        "danger": 34,
    },
    {
        "id": 1076,
        "name": "Davidson Pykerman",
        "areaId": 4,
        "danger": 27,
    },
    {
        "id": 1126,
        "name": "Leanora Sekulla",
        "areaId": 4,
        "danger": 25,
    }
]
```

Inmate's information is not totally expanded for abridgment reasons

<br/>



## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area/{area_id}/details

```
http://localhost:5001/api/area/{area_id}/deatils
```

### **Description**

Returns the the general data of an area if it is stored in the system. Mostly for front-end displaying purposes.

### **Authorization**

Bearer Token obtained after authentication

### **Params**

| URL         |   Input Type   |       Description |
| :---------- | :------------: | ----------------: |
| **area_id** | integer / long | Area's Identifier |

### **Response Body**

```json
{
    "access": true,
    "name": "jobwing",
    "id": 4,
    "currentDangerLevel": 28.666,
    "connections": [
        "patio"
    ],
    "capacity": 30,
    "guards": [
        "Cornelia Lovel"
    ]
}
```

<br/>

<br/>