## Bicycle Rental Application

---

API structure:

* `GET /api/stations/{id}`
* `POST /api/stations/`
* `PATCH /api/stations/{id}`
* `DELETE /api/stations/{id}`
* `GET /api/stations/summary`

### How to use

#### 1. Clone repository

#### 2. Run application

* OpenJDK 11
* Maven 3+
* Spring Boot 2+
* H2 in-memory

Application starting on `http://localhost:8080`, has init data and is ready to use.

#### 3. Send `GET` request

#### Example

`GET /api/stations/1`

Response:
`200 OK`

```json
{
  "name": "Station1",
  "bicycles": [
    {
      "id": 1,
      "modelName": "model1",
      "bicycleStatus": "FREE"
    },
    {
      "id": 2,
      "modelName": "model2",
      "bicycleStatus": "FREE"
    }
  ],
  "bicycleStands": [
    {
      "id": 1,
      "name": "Stand1",
      "standStatus": "FREE"
    },
    {
      "id": 4,
      "name": "Stand4",
      "standStatus": "OCCUPIED"
    }
  ]
}
```

#### 4. Send `POST` request to create new bicycle station

#### Example

`POST /api/stations`

```json
{
  "name": "nazwa stacji",
  "bicycles": [
    {
      "modelName": "nazwa modelu",
      "bicycleStatus": "FREE"
    }
  ],
  "bicycleStands": [
    {
      "name": "nazwa stanowiska",
      "standStatus": "OCCUPIED"
    }
  ]
}
```

Response:
`201 CREATED`

```json
{
  "name": "nazwa stacji",
  "bicycles": [
    {
      "id": 6,
      "modelName": "nazwa modelu",
      "bicycleStatus": "FREE"
    }
  ],
  "bicycleStands": [
    {
      "id": 6,
      "name": "nazwa stanowiska",
      "standStatus": "OCCUPIED"
    }
  ]
}
```

#### 5. Delete bicycle station

#### Example

`DELETE /api/stations/1`

Response:
`204 No Content`

#### 6. Edit existing bicycle station

#### Example

`PATCH /api/stations/1`

```json5
{
  "name": "nowa nazwa stacji"
}
```

Response:
`200 OK`

```json5
{
  "name": "nowa nazwa stacji",
  "bicycles": [
    {
      "id": 2,
      "modelName": "model2",
      "bicycleStatus": "FREE"
    },
    {
      "id": 1,
      "modelName": "model1",
      "bicycleStatus": "FREE"
    }
  ],
  "bicycleStands": [
    {
      "id": 1,
      "name": "Stand1",
      "standStatus": "FREE"
    },
    {
      "id": 4,
      "name": "Stand4",
      "standStatus": "OCCUPIED"
    }
  ]
}
```

#### 7. Check summary report about bicycle stations

#### Example

`GET /api/stations/summary`

Response:
`200 OK`

```json5
{
  "freeStands": 2,
  "occupiedStands": 2,
  "freeBicycles": 3
}
```

---

## Notes

###### Daily rate limit: 50 requests

###### Using [accuweather API](https://developer.accuweather.com/)