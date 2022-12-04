# notas do architeto (dnv por causa dos haters)

## base de dados
* warden(s)
* guards
* inmates
* areas
* sensors
* workstations

* logs (capped collections, since they delete old logs and preserve insertion order)
  * move log by inmate
  * move log by sensor
  * work log by inmate
  * health log by inmate

## 