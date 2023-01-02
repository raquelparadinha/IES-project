# API ENDPOINTS

- Area Controller

  - Mapping("/api/area")

- Guard Controller

  - Mapping("/api/guard")

- Inmate Controller

  - Mapping("/api/inmate")

- Alert Controller
  - Mapping("/api/alert")

## Area /api/area

| Method | Mapping            | Description                                                          | Return (example - type)                          | Input |
| ------ | ------------------ | -------------------------------------------------------------------- | ------------------------------------------------ | ----- |
| GET    |                    | Returns all the areas                                                | [{Area1},{Area2},...] - list                     | None  |
| GET    | /{area_id}         | Returns a specific area if it exists                                 | {Area} - dict                                    | Long  |
| GET    | /{area_id}/access  | Changes the the access to an area                                    | {Area} - dict                                    | Long  |
| GET    | /{area_id}/inmates | Returns the list of inmates for an area based order by inmate danger | [{Inmate1001},{Inmate1002},...] - list           | Long  |
| GET    | /{area_id}/details | Returns the details of an area                                       | [{access,name,id,currentDangerLevel,...}] - list | Long  |

## Guard /api/guard

| Method | Mapping             | Description                                                            | Return (example - type)                         | Input       |
| ------ | ------------------- | ---------------------------------------------------------------------- | ----------------------------------------------- | ----------- |
| GET    |                     | Returns all the guards                                                 | [{Guard1},{Guard2},...] - list                  | None        |
| GET    | /{guard_id}         | Returns a specific guard if it exists                                  | {Guard} - dict                                  | Long        |
| GET    | /{guard_id}/details | Returns the access to this guard's area and his colleagues at the area | ["Open"/"Closed", "Silvia", "Paulo",...] - list | Long        |
| PUT    | /{guard_id}         | Update guard                                                           | {Guard} - dict                                  | Long, Guard |
| POST   |                     | Create guard                                                           | {Guard} - dict                                  | Guard       |

## Inmate /api/inmate

| Method | Mapping                      | Description                               | Return (example - type)                            | Input        |
| ------ | ---------------------------- | ----------------------------------------- | -------------------------------------------------- | ------------ |
| GET    |                              | Returns all the inmates                   | [{Inmate1001},{Inmate1002},...] - list             | None         |
| GET    | /number                      | Retunrs the number of inmates             | 400 - Integer                                      | None         |
| GET    | /{inmate_id}                 | Returns a specific inmate if it exists    | {Inmate} - dict                                    | Long         |
| GET    | /all/health                  | Returns **all** health logs               | [{HealthLog1},{HealthLog2,...}] - list             | None         |
| GET    | /{inmate_id}/health          | Returns the **last** health of one inmate | [{HealthLog1},{HealthLog2,...}] - list             | Long         |
| GET    | /all/health/heartbeat/data   | Return values of inmates heart beat       | [{"qty":2,"value":60},{"qty":1,"value":61}] - list | None         |
| GET    | /all/health/stress/data      | Return values of inmates stress           | [{"qty":2,"value":60},{"qty":1,"value":61}] - list | None         |
| GET    | /all/health/glicose/data     | Return values of inmates glicose          | [{"qty":2,"value":60},{"qty":1,"value":61}] - list | None         |
| GET    | /all/health/uricacid/data    | Return values of inmates uric acid        | [{"qty":2,"value":60},{"qty":1,"value":61}] - list | None         |
| GET    | /all/health/cholesterol/data | Return values of inmates cholesterol      | [{"qty":2,"value":60},{"qty":1,"value":61}] - list | None         |
| GET    | /all/health/toxicscreen/data | Return values of inmates toxic screen     | [{"qty":2,"value":60},{"qty":1,"value":61}] - list | None         |
| GET    | /{inmate_id}/moves           | Returns the areas the inmate has been to  | [Canteen, Patio,..] - list                         | Long         |
| PUT    | /{inmate_id}                 | Update inmate                             | {Inmate} - dict                                    | Long, Inmate |
| POST   |                              | Create inmate                             | {Inmate} - dict                                    | Inmate       |

## Alert /api/alert

| Method | Mapping     | Description                           | Return (example - type)                  | Input |
| ------ | ----------- | ------------------------------------- | ---------------------------------------- | ----- |
| GET    |             | Returns the last 30 alert             | [{Alert1},{Alert2},{Alert300,..}] - list | None  |
| GET    | /{alert_id} | Returns a specific alert if it exists | {Alert} - dict                           | Long  |
| GET    | /new        | Returns the last 5 the new alerts     | [{Alert1},{Alert2}}] - list              | None  |

<!--
| GET    | /riot       | Returns the last 30 riot alert        | [{Alert1},{Alert2},{Alert300,..}] - list | None  |
| GET    | /work       | Returns the last 30 work alert        | [{Alert1},{Alert2},{Alert300,..}] - list | None  |
| GET    | /health     | Returns the last 30 health alert      | [{Alert1},{Alert2},{Alert300,..}] - list | None  | -->

## Work Station /api/workstation

| Method | Mapping                    | Description                                         | Return (example - type)                  | Input |
| ------ | -------------------------- | --------------------------------------------------- | ---------------------------------------- | ----- |
| GET    |                            | Returns all workstations                            | [{WS1},{WS2},{WS3,..}] - list            | None  |
| GET    | /{id}                      | Returns a workstation                               | {WS1} - dict                             | Long  |
| GET    | /{id}/details              | Returns the details of an workstation               | {expectedQuota, numWorkLogs, ...} - dict | Long  |
| GET    | /{workstation_id}/worklogs | Returns the quotas of the works at this workstation | [{x,y},{x,y},{x,y}] - list               | Long  |

<!--
| GET    | /worklog/{worklog_id}/inmate | Return the general info of an worker                | {workLogId,inmateName} - dict            | Long  | -->

## Map Data /api/map

| Method | Mapping | Description                       | Return (example - type)                                          | Input |
| ------ | ------- | --------------------------------- | ---------------------------------------------------------------- | ----- |
| GET    |         | Returns the map rendering details | {areas: {areaname: {details}}, sensors {sensorid: color}} - dict | None  |
