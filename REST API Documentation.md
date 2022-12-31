# **REST API Documentation**

This file contains a detailed documentation of the API developed to supply the system's request/response mapping. For a simpler version check out API ENDPOINTS file. The API access is restricted by authentication / authorization and the user must be authenticated to make use of the most endpoints created. 

In order to better specify this API, the endpoints were divided into six controllers based on their parameters and responses. 

- AuthController (Authentication)
- AreaController (Areas)
- MapDataController (Prison Map)
- MoveSensorController (Move Sensors)
- GuardController (Guards)
- InmateController (Inmates)
- AlertController (Alerts)
- WorkstationController (Work Stations).

<br/>

## **AuthController : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/auth**

<br/>

## **POST** &nbsp; api/auth/signin

```
http://localhost:5001/api/auth/signin
```

### **Description**

Unique way to get authentication on the system. Submitting a email and password that matches on user in the system (guard/warden) the user can obtain access to the API endpoints. Returns the Bearer token necessary in authentication, among with user's roles/premissions.

### **Request Body**

```json
{
    "email": "pareidreds@aettua.pt",
    "password": "soulinda"
}
```

### **Response Body**

```json
{
    "id": 1,
    "email": "pareidreds@aettua.pt",
    "roles": [
        "ROLE_USER",
        "ROLE_ADMIN"
    ],
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXJlaWRyZWRzQGFldHR1YS5wdCIsImlhdCI6MTY3MTc2MzM5NSwiZXhwIjoxNjcxODQ5Nzk1fQ.NG4PPt96qcDtPWMYOaQub7Lovaj0vN9LbU2rkOAmo_Lg58PFdGYUi15X7bIK-Xcq1TlsVgKS1T2Oq_kW-E3HGQ",
    "tokenType": "Bearer"
}
```

<br/>

<br/>

## **AreaController : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area**

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area

```
http://localhost:5001/api/area
```

### **Description**

Returns all the areas in the system.

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
    },
]
```

Inmate's information is not totally expanded for abridgment reasons

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area/{area_id}/details

```
http://localhost:5001/api/area/{area_id}/deatils
```

### **Description**

Returns the the general data of an area if it is stored in the system. Mostly for presentation purposes.

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

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area/{area_id}/lock

```
http://localhost:5001/api/area/{area_id}/lock
```

### **Description**

Changes the access of an area to false. It also locks all the sensors of the area. Inmates will not be able to cross any of the sensors connecting to this area. Returns the changed area.

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
        "access": false,
        "currentInmateIds": []
    }
]
```

Inmate's information is not totally expanded for abridgment reasons

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/area/{area_id}/unlock

```
http://localhost:5001/api/area/{area_id}/unlock
```

### **Description**

Changes the access of an area to true. It also unlocks all the sensors of the area. Inmates will be able to cross all of the sensors connecting to this area. Returns the changed area.

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

Inmate's information is not totally expanded for abridgment reasons

<br/>

<br/>

## **MapDataController : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/map**

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/map

```
http://localhost:5001/api/map
```

### **Description**

Returns all information necessary to display the map on the client-side. Mostly for presentation purposes.

### **Authorization**

Bearer Token obtained after authentication.

### **Response Body**

```json
[
    {
        "id": 1,
        "name": "Paulo Pinto",
        "email": "ppnaopylance@buzzfeed.com",
        "password": "souinfeliz",
        "birthdate": "08/06/2002",
        "phone": 9192345678,
        "areaName": "showers",
        "roles": [
            "ROLE_USER"
        ]
    },
    {
        "id": 2,
        "name": "Licha Holdworth",
        "email": "lholdworth1@buzzfeed.com",
        "password": "fnf0vG",
        "birthdate": "2/13/1971",
        "phone": 4445307536,
        "areaName": "showers",
        "roles": [
            "ROLE_USER"
        ]
    },
]
```

<br/>

<br/>

## **MoveSensorController : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/sensor**

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/sensor/{sensor_id}/lock

```
http://localhost:5001/api/sensor/{sensor_id}/lock
```

### **Description**

Locks a sensor, dis-enabling the movement from the sensor's exit area to sensor's entry area. Returns the updated sensor.

### **Authorization**

Bearer Token obtained after authentication.

### **Response Body**

```json
{
    "id": 1,
    "entryAreaId": 1,
    "exitAreaId": 2,
    "active": false,
    "moveLogIds": []
}
```

<br/>

## **MoveSensorController : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/sensor**

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/sensor/{sensor_id}/unlock

```
http://localhost:5001/api/sensor/{sensor_id}/unlock
```

### **Description**

Unlocks a sensor, enabling the movement from the sensor's exit area to sensor's entry area. Returns the updated sensor.

### **Authorization**

Bearer Token obtained after authentication.

### **Response Body**

```json
{
    "id": 1,
    "entryAreaId": 1,
    "exitAreaId": 2,
    "active": true,
    "moveLogIds": []
}
```

<br/>

## **GuardController : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/guard**

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/guard

```
http://localhost:5001/api/guard
```

### **Description**

Returns all the guards in the system.

### **Authorization**

Bearer Token obtained after authentication.

### **Response Body**

```json
[
    {
        "id": 1,
        "name": "Paulo Pinto",
        "email": "ppnaopylance@buzzfeed.com",
        "password": "souinfeliz",
        "birthdate": "08/06/2002",
        "phone": 9192345678,
        "areaName": "showers",
        "roles": [
            "ROLE_USER"
        ]
    },
    {
        "id": 2,
        "name": "Licha Holdworth",
        "email": "lholdworth1@buzzfeed.com",
        "password": "fnf0vG",
        "birthdate": "2/13/1971",
        "phone": 4445307536,
        "areaName": "showers",
        "roles": [
            "ROLE_USER"
        ]
    },
]
```

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/guard/{guard_id}

```
http://localhost:5001/api/guard/{guard_id}
```

### **Description**

Returns a guard if it is stored in the system.

### **Authorization**

Bearer Token obtained after authentication.

### **Params**

| URL          |   Input Type   |        Description |
| :----------- | :------------: | -----------------: |
| **guard_id** | integer / long | Guard's Identifier |

### **Response Body**

```json
{
    "id": 1,
    "name": "Paulo Pinto",
    "email": "ppnaopylance@buzzfeed.com",
    "phone": 9192345678,
    "birthdate": "08/06/2002",
    "areaId": 9,
    "password": "souinfeliz",
    "roles": [
        "ROLE_USER"
    ]
}
```

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/guard/{guard_id}/details

```
http://localhost:5001/api/guard/{guard_id}/details
```

### **Description**

Returns the current access to this guard's area followed by all his colleagues at the area. Mostly for presentation purposes.

### **Authorization**

Bearer Token obtained after authentication.

### **Params**

| URL          |   Input Type   |        Description |
| :----------- | :------------: | -----------------: |
| **guard_id** | integer / long | Guard's Identifier |

### **Response Body**

```json
[
    "Open",
    "Licha Holdworth",
    "Arni Unthank"
]
```

<br/>

## **PUT** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/guard/{guard_id}

```
http://localhost:5001/api/guard/{guard_id}
```

### **Description**

Update a guard if it is stored in the system with new information. Returns the that guard after the update.

### **Authorization**

Bearer Token obtained after authentication.

### **Params**

| URL          |   Input Type   |        Description |
| :----------- | :------------: | -----------------: |
| **guard_id** | integer / long | Guard's Identifier |

### **Request Body**

```json
{
    "id": 1,
    "name": "Paulo Pinto",
    "email": "changeOnEmail@gmail.com",
    "phone": 1234567891,
    "birthdate": "01/01/2001",
    "areaId": 1,
    "password": "soufeliz",
    "roles": [
        "ROLE_USER"
    ]
}
```

### **Response Body**

```json
{
    "id": 1,
    "name": "Paulo Pinto",
    "email": "changeOnEmail@gmail.com",
    "phone": 1234567891,
    "birthdate": "01/01/2001",
    "areaId": 1,
    "password": "soufeliz",
    "roles": [
        "ROLE_USER"
    ]
}
```

<br/>

## **POST** &nbsp;&nbsp; api/guard

```
http://localhost:5001/api/guard
```

### **Description**

Insert a new guard in the system if the guard is valid. Returns the that guard after saving. The pre-defined *id* reference for new guards is 0, but it will be changed by the system

### **Authorization**

Bearer Token obtained after authentication.

### **Request Body**

```json
{
    "id": 0,
    "name": "Tiago Sora",
    "email": "tiagosoraemail@gmail.com",
    "phone": 1234567891,
    "birthdate": "01/01/2001",
    "areaId": 1,
    "password": "veryhappypass"
}
```

### **Response Body**

```json
{
    "id": 1100,
    "name": "Tiago Sora",
    "email": "tiagosoraemail@gmail.com",
    "phone": 1234567891,
    "birthdate": "01/01/2001",
    "areaId": 1,
    "password": "veryhappypass",
    "roles": [
        "ROLE_USER"
    ]
}
```

<br/><br/>

## **InmateController : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/inmate**

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/inmate

```
http://localhost:5001/api/inmate
```

### **Description**

Returns all the inmates stored in the system.

### **Authorization**

Bearer Token obtained after authentication.

### **Response Body**

```json
[
    {
        "id": 1001,
        "name": "Miguel Manco",
        "birthDate": "03/19/2002",
        "entryDate": "03/20/2002",
        "sentenceEnd": "03/20/2040",
        "areaId": 8,
        "danger": 0,
        "solitary": false,
        "healthLogId": 0,
        "moveLogIds": [],
        "workLogIds": []
    },
    {
        "id": 1002,
        "name": "Daffy Ryman",
        "birthDate": "08/25/1992",
        "entryDate": "12/03/2013",
        "sentenceEnd": "02/20/2026",
        "areaId": 9,
        "danger": 0,
        "solitary": false,
        "healthLogId": 0,
        "moveLogIds": [],
        "workLogIds": []
    },
]
```

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/inmate/number

```
http://localhost:5001/api/inmate/number
```

### **Description**

Returns the number of inmates stored in the system.

### **Authorization**

Bearer Token obtained after authentication.

### **Response Body**

```json
400
```

<br/>

## **GET** &nbsp;&nbsp; api/inmate/{inmate_id}

```
http://localhost:5001/api/inmate/{inmate_id}
```

### **Description**

Returns an inmate if it is stored in the system.

### **Authorization**

Bearer Token obtained after authentication

### **Params**

| URL           |   Input Type   |         Description |
| :------------ | :------------: | ------------------: |
| **inmate_id** | integer / long | Inmate's Identifier |

### **Response Body**

```json
{
    "id": 1001,
    "name": "Miguel Manco",
    "birthDate": "03/19/2002",
    "entryDate": "03/20/2002",
    "sentenceEnd": "03/20/2040",
    "areaId": 8,
    "danger": 0,
    "solitary": false,
    "healthLogId": 0,
    "moveLogIds": [],
    "workLogIds": []
}
```

<br/>

## **GET** &nbsp;&nbsp; api/inmate/all/health

```
http://localhost:5001/api/inmate/all/health
```

### **Description**

Returns all inmate's last health log if they have at least one health log.

### **Authorization**

Bearer Token obtained after authentication

### **Response Body**

```json
[
    {
        "id": 1,
        "inmateId": 1099,
        "timestamp": "2022-12-22T22:36:06.903+00:00",
        "heartBeat": 115,
        "stress": 72,
        "glicose": 8,
        "uricAcid": 7,
        "cholesterol": 189,
        "toxicScreen": 0
    },
    {
        "id": 2,
        "inmateId": 1029,
        "timestamp": "2022-12-22T22:36:17.123+00:00",
        "heartBeat": 98,
        "stress": 93,
        "glicose": 106,
        "uricAcid": 5,
        "cholesterol": 198,
        "toxicScreen": 0
    },
]
```

<br/>

## **GET** &nbsp;&nbsp; api/inmate/{inmate_id}/health

```
http://localhost:5001/api/inmate/{inmate_id}/health
```

### **Description**

Returns all the health logs of an inmate.

### **Authorization**

Bearer Token obtained after authentication

### **Params**

| URL           |   Input Type   |         Description |
| :------------ | :------------: | ------------------: |
| **inmate_id** | integer / long | Inmate's Identifier |

### **Response Body**

```json
[
    {
        "id": 1,
        "inmateId": 1099,
        "timestamp": "2022-12-22T22:36:06.903+00:00",
        "heartBeat": 115,
        "stress": 72,
        "glicose": 8,
        "uricAcid": 7,
        "cholesterol": 189,
        "toxicScreen": 0
    },
    {
        "id": 13,
        "inmateId": 1099,
        "timestamp": "2022-12-22T22:36:24.338+00:00",
        "heartBeat": 54,
        "stress": 49,
        "glicose": 0,
        "uricAcid": 6,
        "cholesterol": 87,
        "toxicScreen": 0
    },
]
```

<br/>

## **GET** &nbsp;&nbsp; api/inmate/{inmate_id}/health/last

```
http://localhost:5001/api/inmate/{inmate_id}/health/last
```

### **Description**

Returns the last health log of an inmate.

### **Authorization**

Bearer Token obtained after authentication

### **Params**

| URL           |   Input Type   |         Description |
| :------------ | :------------: | ------------------: |
| **inmate_id** | integer / long | Inmate's Identifier |

### **Response Body**

```json
[
    {
        "id": 1,
        "inmateId": 1099,
        "timestamp": "2022-12-22T22:36:06.903+00:00",
        "heartBeat": 115,
        "stress": 72,
        "glicose": 8,
        "uricAcid": 7,
        "cholesterol": 189,
        "toxicScreen": 0
    }
]
```

<br/>

## **GET** &nbsp;&nbsp; api/inmate/all/health/heartbeat/data

```
http://localhost:5001/api/inmate/all/health/heartbeat/data
```

### **Description**

Returns the number of logged values of inmate's heart beats. "value" represents the value of heart beat and "qty" represents the quantity of times, that value was logged. Mostly for presentation purposes. 

### **Authorization**

Bearer Token obtained after authentication

### **Response Body**

```json
[
    {
        "qty": 2,
        "value": 87
    },
    {
        "qty": 3,
        "value": 94
    },
    {
        "qty": 1,
        "value": 95
    },
]
```

<br/>

## **GET** &nbsp;&nbsp; api/inmate/all/health/stress/data

```
http://localhost:5001/api/inmate/all/health/stress/data
```

### **Description**

Returns the number of logged values of inmate's stress level. "value" represents the value of stress level and "qty" represents the quantity of times, that value was logged. Mostly for presentation purposes. 

### **Authorization**

Bearer Token obtained after authentication

### **Response Body**

```json
[
    {
        "qty": 1,
        "value": 37
    },
    {
        "qty": 2,
        "value": 38
    },
    {
        "qty": 1,
        "value": 46
    },
]
```

<br/>

## **GET** &nbsp;&nbsp; api/inmate/all/health/glucose/data

```
http://localhost:5001/api/inmate/all/health/glucose/data
```

### **Description**

Returns the number of logged values of inmate's glucose level. "value" represents the value of glucose level and "qty" represents the quantity of times, that value was logged. Mostly for presentation purposes. 

### **Authorization**

Bearer Token obtained after authentication

### **Response Body**

```json
[
    {
        "qty": 1,
        "value": 86
    },
    {
        "qty": 1,
        "value": 90
    },
    {
        "qty": 2,
        "value": 94
    },
]
```

<br/>

## **GET** &nbsp;&nbsp; api/inmate/all/health/uricacid/data

```
http://localhost:5001/api/inmate/all/health/uricacid/data
```

### **Description**

Returns the number of logged values of inmate's uric acid. "value" represents the value of uric acid and "qty" represents the quantity of times, that value was logged. Mostly for presentation purposes. 

### **Authorization**

Bearer Token obtained after authentication

### **Response Body**

```json
[
    {
        "qty": 5,
        "value": 3
    },
    {
        "qty": 4,
        "value": 4
    },
    {
        "qty": 8,
        "value": 5
    },
    {
        "qty": 3,
        "value": 6
    },
]
```

<br/>

## **GET** &nbsp;&nbsp; api/inmate/all/health/cholesterol/data

```
http://localhost:5001/api/inmate/all/health/cholesterol/data
```

### **Description**

Returns the number of logged values of inmate's cholesterol level. "value" represents the value of cholesterol level and "qty" represents the quantity of times, that value was logged. Mostly for presentation purposes. 

### **Authorization**

Bearer Token obtained after authentication

### **Response Body**

```json
[
    {
        "qty": 1,
        "value": 174
    },
    {
        "qty": 2,
        "value": 183
    },
    {
        "qty": 2,
        "value": 189
    },
]
```

<br/>

## **GET** &nbsp;&nbsp; api/inmate/all/health/toxicscreen/data

```
http://localhost:5001/api/inmate/all/health/cholesterol/data
```

### **Description**

Returns the number of logged values of inmate's toxic screen (drug use). "value" represents the value of toxic screen and "qty" represents the quantity of times, that value was logged. Mostly for presentation purposes. 

### **Authorization**

Bearer Token obtained after authentication

### **Response Body**

```json
[
    {
        "qty": 24,
        "value": 0
    },
    {
        "qty": 4,
        "value": 1
    },
    {
        "qty": 1,
        "value": 2
    }
]
```

<br/>

## **GET** &nbsp;&nbsp; api/inmate/{inmate_id}/moves

```
http://localhost:5001/api/inmate/{inmate_id}/moves
```

### **Description**

Returns the areas an inmate has been to if that inmate is stored in the system.

### **Authorization**

Bearer Token obtained after authentication

### **Params**

| URL           |   Input Type   |         Description |
| :------------ | :------------: | ------------------: |
| **inmate_id** | integer / long | Inmate's Identifier |

### **Response Body**

```json
[
    "patio",
    "cellblock1",
    "cellblock2",
    "patio"
]
```

<br/>

## **PUT** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/inmate/{inmate_id}

```
http://localhost:5001/api/inmate/{inmate_id}
```

### **Description**

Update a inmate if it is stored in the system with new information. Returns the that inmate after the update.

### **Authorization**

Bearer Token obtained after authentication.

### **Params**

| URL           |   Input Type   |         Description |
| :------------ | :------------: | ------------------: |
| **inmate_id** | integer / long | Inmate's Identifier |

### **Request Body**

```json
{
    "id": 1001,
    "name": "Miguel Mancoso",
    "birthDate": "01/01/2002",
    "entryDate": "02/01/2002",
    "sentenceEnd": "01/01/2050",
    "areaId": 8,
    "danger": 10000,
    "solitary": true,
    "healthLogId": 0,
    "moveLogIds": [],
    "workLogIds": []
}
```

### **Response Body**

```json
{
    "id": 1001,
    "name": "Miguel Mancoso",
    "birthDate": "01/01/2002",
    "entryDate": "02/01/2002",
    "sentenceEnd": "01/01/2050",
    "areaId": 8,
    "danger": 10000,
    "solitary": true,
    "healthLogId": 0,
    "moveLogIds": [],
    "workLogIds": []
}
```

<br/>

## **POST** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/inmate

```
http://localhost:5001/api/inmate
```

### **Description**

Insert a new inmate in the system if the inmate is valid. Returns the that inmate after saving. The pre-defined *id* reference for new inmates is 0, but it will be changed by the system

### **Authorization**

Bearer Token obtained after authentication.

### **Request Body**

```json
{
    "id": 0,
    "name": "Tiago Sora",
    "birthDate": "12/04/2002",
    "entryDate": "13/04/2002",
    "sentenceEnd": "12/04/2050",
}
```

### **Response Body**

```json
{
    "id": 1401,
    "name": "Tiago Sora",
    "birthDate": "12/04/2002",
    "entryDate": "13/04/2002",
    "sentenceEnd": "12/04/2050",
}
```

<br/><br/>

## **AlertController : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/alert**

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/alert

```
http://localhost:5001/api/alert
```

### **Description**

Returns the 30 most recent alerts stored in the system. After returning the alerts, every alert will be updated, changing "seen" to true. Mostly for presentation purposes.

### **Authorization**

Bearer Token obtained after authentication.

### **Response Body**

```json
[
    {
        "InmateID": 1065,
        "Alert": {
            "id": 159,
            "type": "work",
            "information": "Inmate Olvan Peinton is not doing an expected job",
            "timestamp": "2022-12-29T18:18:18.402+00:00",
            "seen": true,
            "workLogId": 539
        }
    },

    {
        "InmateID": 1123,
        "Alert": {
            "id": 130,
            "type": "health",
            "information": "Inmate Laurie Crosland is not feeling well",
            "timestamp": "2022-12-29T18:14:04.668+00:00",
            "seen": true,
            "symptoms": [
                "Low Heart Beat"
            ],
            "healthLogId": 142
        }
    }
]
```

<br/>

## **GET** &nbsp;&nbsp; api/alert/{alert_id}/moves

```
http://localhost:5001/api/inmate/{inmate_id}/moves
```

### **Description**

Returns an alert if it is stored in the system.

### **Authorization**

Bearer Token obtained after authentication

### **Params**

| URL          |   Input Type   |        Description |
| :----------- | :------------: | -----------------: |
| **alert_id** | integer / long | Alert's Identifier |

### **Response Body**

```json
{
    "id": 10,
    "type": "health",
    "information": "Inmate Isabeau Ligerton is not feeling well",
    "timestamp": "2022-12-23T01:50:18.245+00:00",
    "seen": false,
    "symptoms": [
        "Minor Symptoms of Drug Use"
    ],
    "healthLogId": 17
}
```

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/alert/new

```
http://localhost:5001/api/alert
```

### **Description**

Returns the 5 most recent alerts stored in the system if their variable "seen" equals false. After returning the alerts, every alert will be updated, changing  "seen" to true. Mostly for presentation purposes.

### **Authorization**

Bearer Token obtained after authentication.

### **Response Body**

```json
[
    {
        "id": 44,
        "type": "health",
        "information": "Inmate Phil Bengle is not feeling well",
        "timestamp": "2022-12-23T01:50:48.640+00:00",
        "seen": true,
        "symptoms": [
            "Low Uric Acid"
        ],
        "healthLogId": 29
    },

    {
        "id": 15,
        "type": "work",
        "information": "Inmate Orv Burchett is not doing an expected job",
        "timestamp": "2022-12-23T01:50:32.089+00:00",
        "seen": true,
        "workLogId": 9
    },
]
```

Consecutive calls for this endpoint normally return and empty array, since there's no new alerts that haven't been seen. 

<br/>

<br/>

## **WorkstationController : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/workstation**

<br/>

## **GET** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; api/**workstation**

```
http://localhost:5001/api/workstation
```

### **Description**

Returns all the workstations stored in the system.

### **Authorization**

Bearer Token obtained after authentication.

### **Response Body**

```json
[
    {
        "id": 1,
        "name": "mail_sorting",
        "description": "Mail Sorting",
        "workLogIds": []
        "expectedQuota": 22
    }, {
        "id": 2,
        "name": "carpentry",
        "description": "Carpentry",
        "workLogIds": [],
        "expectedQuota": 25
    }, {
        "id": 3,
        "name": "waste_disposal",
        "description": "Waste Disposal",
        "workLogIds": [],
        "expectedQuota": 25
    }, {
        "id": 4,
        "name": "janitor",
        "description": "Janitor",
        "workLogIds": [],
        "expectedQuota": 30
    }, {
        "id": 5,
        "name": "gardening",
        "description": "Gardening",
        "workLogIds": [],
        "expectedQuota": 20
    }
]
```

<br/>

## **GET** &nbsp;&nbsp; api/workstation/{workstation_id}

```
http://localhost:5001/api/workstation/{workstation_id}
```

### **Description**

Returns a workstation if it is stored in the system.

### **Authorization**

Bearer Token obtained after authentication

### **Params**

| URL                |   Input Type   |              Description |
| :----------------- | :------------: | -----------------------: |
| **workstation_id** | integer / long | Workstation's Identifier |

### **Response Body**

```json
{
    "id": 1,
    "name": "mail_sorting",
    "description": "Mail Sorting",
    "workLogIds": [
        6,
        12
    ],
    "expectedQuota": 22
}
```

<br/>

## **GET** &nbsp;&nbsp; api/workstation/{workstation_id}/worklogs

```
http://localhost:5001/api/workstation/{workstation_id}/worklogs
```

### **Description**

Returns all the worklogs for a workstation if that workstation is stored in the system. Mostly for presentation purposes.

### **Authorization**

Bearer Token obtained after authentication

### **Params**

| URL                |   Input Type   |              Description |
| :----------------- | :------------: | -----------------------: |
| **workstation_id** | integer / long | Workstation's Identifier |

### **Response Body**

```json
[
    {
        "worklogId": 0,
        "quota": 47
    },
    {
        "worklogId": 1,
        "quota": 80
    }
]
```

"quota" represents the quantity produced by the inmate during that work.

<br/>

## **GET** &nbsp;&nbsp; api/workstation/{workstation_id}/details

```
http://localhost:5001/api/workstation/{workstation_id}/details
```

### **Description**

Returns the general data details of a workstation if that workstation is stored in the system. Mostly for presentation purposes.

### **Authorization**

Bearer Token obtained after authentication

### **Params**

| URL                |   Input Type   |              Description |
| :----------------- | :------------: | -----------------------: |
| **workstation_id** | integer / long | Workstation's Identifier |

### **Response Body**

```json
{
    "expectedQuota": 22,
    "numWorkLogs": 2,
    "worstWoker": "Merell Iddenden",
    "bestWorker": "Leah Barkworth",
    "averageQuota": 63
}
```

"quota" represents the quantity produced by the inmate during that work.
