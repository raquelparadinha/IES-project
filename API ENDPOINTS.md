# API ENDPOINTS

- Area Controller

  - Mapping("/api/area")

- Guard Controller

  - Mapping("/api/guard")

- Inmate Controller

  - Mapping("/api/inmate")

## Area

| Method | Mapping        | Description                          | Return                       |
| ------ | -------------- | ------------------------------------ | ---------------------------- |
| GET    | /api/area      | Returns all the areas                | [{Area1},{Area2},...] - list |
| GET    | /api/area/{id} | Returns a specific area if it exists | {Area} - dict                |

## Guard

| Method | Mapping                    | Description                                                  | Return                         |
| ------ | -------------------------- | ------------------------------------------------------------ | ------------------------------ |
| GET    | /api/guard                 | Returns all the guards                                       | [{Guard1},{Guard2},...] - list |
| GET    | /api/guard/{id}            | Returns a specific guard if it exists                        | {Guard} - dict                 |
| GET    | /api/guard/{id}/colleagues | Returns **the name** of every guard in the same area as this guard | ["Silvia", "Paulo"] - list     |
| PUT    | /api/guard/{id}            | Update guard                                                 | {Guard} - dict                 |
| POST   | /api/guard                 | Create guard                                                 | {Guard} - dict                 |

## Inmate

| Method | Mapping                             | Description                                                  | Return                                      |
| ------ | ----------------------------------- | ------------------------------------------------------------ | ------------------------------------------- |
| GET    | /api/inmate                         | Returns all the inmates                                      | [{Inmate1001},{Inmate1002},...] - list      |
| GET    | /api/inmate/{id}                    | Returns a specific inmate if it exists                       | {Inmate} - dict                             |
| GET    | /api/inmate/leaving                 | Return a sorted list of inmates based on sentence ending     | [{Inmate1001},{Inmate1002},...] - list      |
| GET    | /api/inmate/leaving/{number}        | Return a sorted limited list of inmates based on sentence ending | [{Inmate1001},{Inmate1002},...] - list      |
| GET    | /api/inmate/health                  | Returns **all** health logs                                  | [{HealthLog1},{HealthLog2,...}] - list      |
| GET    | /api/inmate/health/heartbeat/data   | Return values of inmates heart beat                          | {"64":1, "65":4, "66":3, "67":1,...} - dict |
| GET    | /api/inmate/health/stress/data      | Return values of inmates stress                              | {"64":1, "65":2, "66":1, "67":2,...} - dict |
| GET    | /api/inmate/health/glicose/data     | Return values of inmates glicose                             | {"0":1, "2":1, "5":1, "7":2,...} - dict     |
| GET    | /api/inmate/health/uricacid/data    | Return values of inmates uric acid                           | {"4":27,"5":32,"6":35} - dict               |
| GET    | /api/inmate/health/toxicscreen/data | Return values of inmates toxic screen                        | {"0":24,"1":36,"2":34} - dict               |
| PUT    | /api/inmate/{id}                    | Update inmate                                                | {Inmate} - dict                             |
| POST   | /api/inmate                         | Create inmate                                                | {Inmate} - dict                             |

