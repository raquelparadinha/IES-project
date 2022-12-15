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

| Method | Mapping     | Description                          | Return (example - type)      | Input |
| ------ | ----------- | ------------------------------------ | ---------------------------- | ----- |
| GET    |             | Returns all the areas                | [{Area1},{Area2},...] - list | None  |
| GET    | /{id}       | Returns a specific area if it exists | {Area} - dict                | Long  |
| GET    | /{id}/acess | Changes the access to a area         | {Area} - dict                | Long  |

## Guard /api/guard

| Method | Mapping       | Description                                                           | Return (example - type)                                  | Input       |
| ------ | ------------- | --------------------------------------------------------------------- | -------------------------------------------------------- | ----------- |
| GET    |               | Returns all the guards                                                | [{Guard1},{Guard2},...] - list                           | None        |
| GET    | /{id}         | Returns a specific guard if it exists                                 | {Guard} - dict                                           | Long        |
| GET    | /{id}/sidebar | Returns the access to this guard's area and his collegues at the area | ["Open"/"Closed", "Silvia", "Paulo",...] - list | Long                 |
| PUT    | /{id}         | Update guard                                                          | {Guard} - dict                                           | Long, Guard |
| POST   |               | Create guard                                                          | {Guard} - dict                                           | Guard       |

## Inmate /api/inmate

| Method | Mapping                      | Description                                                      | Return (example - type)                            | Input        |
| ------ | ---------------------------- | ---------------------------------------------------------------- | -------------------------------------------------- | ------------ |
| GET    |                              | Returns all the inmates                                          | [{Inmate1001},{Inmate1002},...] - list             | None         |
| GET    | /number                      | Retunrs the number of inmates                                    | 400 - Integer                                      | None         |
| GET    | /{id}                        | Returns a specific inmate if it exists                           | {Inmate} - dict                                    | Long         |
| GET    | /leaving                     | Return a sorted list of inmates based on sentence ending         | [{Inmate1001},{Inmate1002},...] - list             | None         |
| GET    | /leaving/{number}            | Return a sorted limited list of inmates based on sentence ending | [{Inmate1001},{Inmate1002},...] - list             | None         |
| GET    | /all/health                  | Returns **all** health logs                                      | [{HealthLog1},{HealthLog2,...}] - list             | None         |
| GET    | /{id}/health                 | Returns the last health of one inmate                            | [{HealthLog1},{HealthLog2,...}] - list             | Long         |
| GET    | /all/health/heartbeat/data   | Return values of inmates heart beat                              | [{"qty":2,"value":60},{"qty":1,"value":61}] - list | None         |
| GET    | /all/health/stress/data      | Return values of inmates stress                                  | [{"qty":2,"value":60},{"qty":1,"value":61}] - list | None         |
| GET    | /all/health/glicose/data     | Return values of inmates glicose                                 | [{"qty":2,"value":60},{"qty":1,"value":61}] - list | None         |
| GET    | /all/health/uricacid/data    | Return values of inmates uric acid                               | [{"qty":2,"value":60},{"qty":1,"value":61}] - list | None         |
| GET    | /all/health/cholesterol/data | Return values of inmates cholesterol                             | [{"qty":2,"value":60},{"qty":1,"value":61}] - list | None         |
| GET    | /all/health/toxicscreen/data | Return values of inmates toxic screen                            | [{"qty":2,"value":60},{"qty":1,"value":61}] - list | None         |
| GET    | /{id}/moves                  | Returns the areas the inmate has been too                        | [Canteen, Patio,..] - list                         | Long         |
| PUT    | /{id}                        | Update inmate                                                    | {Inmate} - dict                                    | Long, Inmate |
| POST   |                              | Create inmate                                                    | {Inmate} - dict                                    | Inmate       |

## Alert /api/alert

| Method | Mapping | Description                       | Return (example - type)                  | Input |
| ------ | ------- | --------------------------------- | ---------------------------------------- | ----- |
| GET    |         | Returns the last 30 alert         | [{Alert1},{Alert2},{Alert300,..}] - list | None  |
| GET    | /new    | Returns the last 5 the new alerts | [{Alert1},{Alert2}}] - list              | None  |

