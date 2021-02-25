# garage-api

# Installation

## Clone the repository
To clone the repository, run
```
git clone https://github.com/tonioostblok/garage-api
```
## Run the docker mysql container
To run the docker mysql container, open your terminal and go to the .docker folder. After that run the following command and you are set:
```
docker-compose up -d
```
## Run the application
To run the application, just open the project in IntelliJ and run the GarageApplication.java file.

# Endpoint documentation

## Auth
**You send:**  Your  login credentials.
**You get:** An `JWT Bearer token` with wich you can make further actions.

**Request:**
```json
POST /auth
Accept: application/json
Content-Type: application/json

{
    "username": "test@gmail.com",
    "password": "password" 
}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
   "jwt": "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json

{
    "message": "The username or password is not correct.",
}
``` 
## Me
**You send:**  Your JWT token inside the Authorization header.

**You get:** Your `User object`
**Request:**
```json
GET /me
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id":1,
    "firstname":"Foo",
    "lastname":"Bar",
    "email":"foo@bar.com",
    "password":"random_hash",
    "roles": [
        {
        "name":"ROLE_ADMIN",
        "privileges": [
            {
              "name":"READ_PRIVILEGE"
            },
            {
              "name":"WRITE_PRIVILEGE"
            },
        ]
      }
    ]
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
{
  "message":"Something went wrong while trying to fetch the user."
}
``` 

## Car endpoints
```json
GET /car/{id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "brand": "Opel",
    "type": "Corsa",
    "license_plate": "NL-A3-AW-34",
    "mot": "2021-01-06",
    "files": [
        {
          "id": 1,
          "name": "filename",
          "url": "1612550786791.png"
        },
        {
          "id": 2,
          "name": "file name 2",
          "url": "1612550786791.pdf"
        }
    ]
}
```
**Failed Response(s):**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 
```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message": "The car that you have tried to fetch does not exist."
}
```

```json
POST /car
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}

{
    "brand": "Opel",
    "type": "Corsa",
    "license_plate": "NL-A3-AW-34",
    "mot": "2021-01-06",
}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "brand": "Opel",
    "type": "Corsa",
    "license_plate": "NL-A3-AW-34",
    "mot": "2021-01-06",
}
```
**Failed Response(s):**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message": "Something went wrong whilst trying to add a car."
}
```

```json
PUT /car
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}

{
    "id": 1,
    "brand": "Opel",
    "type": "Corsa",
    "license_plate": "NL-A3-AW-34",
    "mot": "2021-01-06",
}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "brand": "Opel",
    "type": "Corsa",
    "license_plate": "NL-A3-AW-34",
    "mot": "2021-01-06",
}
```
**Failed Response(s):**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message": "Something went wrong whilst trying to update a car."
}
```

```json
DEL /car/{id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 204 No Content
Content-Type: application/json
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message": "Something went wrong whilst trying to delete a car."
}
```

## Customer endpoints
```json
GET /customer/{id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "firstname": "Foo",
    "lastname": "Bar",
    "phonenumber": "0612345678",
    "email": "foo@bar.com",
    "address": "Foobar street 4",
    "zipcode": "1234FB",
    "cars": [
        {
          "id": 1,
          "brand": "Opel",
          "type": "Corsa",
          "license_plate": "NL-FB-12-AB",
          "mot": "2021-01-06",
          "files": [
            {
              "id": 1,
              "name": "foobar",
              "url": "foobar.png"
            }
          ]     
        },
    ]
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"The customer that you have tried to fetch does not exist."
}
``` 

```json
POST /customer
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}

{
    "firstname": "Foo",
    "lastname": "Bar",
    "phonenumber": "0612345678",
    "email": "foo@bar.com",
    "address": "Foobar street 4",
    "zipcode": "1234FB",
    "cars":[1]
}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "firstname": "Foo",
    "lastname": "Bar",
    "phonenumber": "0612345678",
    "email": "foo@bar.com",
    "address": "Foobar street 4",
    "zipcode": "1234FB",
    "cars": [
        {
        "id": 1,
        "brand": "Opel",
        "type": "Corsa",
        "license_plate": "NL-FB-12-AB",
        "mot": "2021-01-06",
        "files": [
            {
                "id": 1,
                "name": "foobar",
                "url": "foobar.png"
            }
        ]
        },
    ]
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to add a customer."
}
``` 

```json
PUT /customer
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}

{
    "id": 1,
    "firstname": "Foo",
    "lastname": "Bar",
    "phonenumber": "0612345678",
    "email": "foo@bar.com",
    "address": "Foobar street 4",
    "zipcode": "1234FB",
}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "firstname": "Foo",
    "lastname": "Bar",
    "phonenumber": "0612345678",
    "email": "foo@bar.com",
    "address": "Foobar street 4",
    "zipcode": "1234FB",
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to update a customer."
}
``` 

```json
DEL /customer/{id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 204 No Content
Content-Type: application/json
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to delete a customer."
}
``` 

```json
POST /customer/{customer_id}/car/{car_id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 200
Content-Type: application/json
{
    "id": 1,
    "firstname": "Foo",
    "lastname": "Bar",
    "phonenumber": "0612345678",
    "email": "foo@bar.com",
    "address": "Foobar street 4",
    "zipcode": "1234FB",
    "cars": [
        {
            "id": 1,
            "brand": "Opel",
            "type": "Corsa",
            "license_plate": "NL-FB-12-AB",
            "mot": "2021-01-06",
            "files": [
                {
                "id": 1,
                "name": "foobar",
                "url": "foobar.png"
                }
            ]
        },
    ]
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to add a car to a customer."
}
``` 

```json
DEL /customer/{customer_id}/car/{car_id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 204 No Content
Content-Type: application/json
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to delete a car to a customer."
}
``` 

## Part endpoints
```json
GET /part/{id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "name": "Brakes",
    "price": 10.50,
    "quantity": 2,
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"The part that you have tried to fetch does not exist."
}
``` 

```json
POST /part
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}

{
    "name": "Brakes",
    "price": 10.50,
    "quantity": 2,
}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "name": "Brakes",
    "price": 10.50,
    "quantity": 2,
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to add a part."
}
``` 

```json
PUT /part
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}

{
    "id": 1,
    "name": "Sturdy brakes",
    "price": 12.50,
    "quantity": 1,
}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "name": "Sturdy brakes",
    "price": 12.50,
    "quantity": 1,
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to update a part."
}
``` 

```json
DEL /part/{id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 204 No Content
Content-Type: application/json
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to delete a part."
}
``` 

## Action endpoints
```json
GET /action/{id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "title": "Replacing the brakes",
    "content": "Replacing the brakes takes long practice.",
    "price": 10.50,
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"The action that you have tried to fetch does not exist."
}
``` 

```json
POST /action
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}

{
    "title": "Replacing the brakes",
    "content": "Replacing the brakes takes long practice.",
    "price": 10.50
}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "title": "Replacing the brakes",
    "content": "Replacing the brakes takes long practice.",
    "price": 10.50
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to add an action."
}
``` 

```json
PUT /action
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}

{
    "id": 1,
    "title": "Replacing the sturdy brakes",
    "content": "Replacing the sturdy brakes takes long practice.",
    "price": 12.50
}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "title": "Replacing the sturdy brakes",
    "content": "Replacing the sturdy brakes takes long practice.",
    "price": 12.50
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to update an action."
}
``` 

```json
DEL /action/{id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 204 No Content
Content-Type: application/json
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to delete an action."
}
``` 

## Repairs endpoints
```json
GET /repair/{id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "name": "Repair for mr FooBar",
    "description": "This is the description",
    "scheduled_at": "2021-02-01T00:00:00.000+00:00",
    "status": "open",
    "car": {
        "id": 1,
        "brand": "Opel",
        "type": "Corsa",
        "license_plate": "NL-A3-AW-34",
        "mot": "2021-01-06",
        "files": [
            {
            "id": 1,
            "name": "foobar",
            "url": "123124.png"
            }, ... and more
        ]
    },
    "parts": [
        {
        "id": 1,
        "name": "Sturdy brakes",
        "price": 12.50,
        "quantity": 1,
        }, ...and more
    ],
    "actions": [
        {
        "id": 1,
        "title": "Replacing the brakes",
        "content": "Replacing the brakes takes long practice.",
        "price": 10.50,
        }  ... and more
    ]
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"The repair that you have tried to fetch does not exist."
}
``` 

```json
GET /repair (returns all repairs)
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

[
  {
    "id": 1,
    "name": "Repair for mr FooBar",
    "description": "This is the description",
    "scheduled_at": "2021-02-01T00:00:00.000+00:00",
    "status": "open",
    "car": {
        "id": 1,
        "brand": "Opel",
        "type": "Corsa",
        "license_plate": "NL-A3-AW-34",
        "mot": "2021-01-06",
        "files": [
            {
            "id": 1,
            "name": "foobar",
            "url": "123124.png"
            }, ... and more
        ]
    },
    "parts": [
        {
        "id": 1,
        "name": "Sturdy brakes",
        "price": 12.50,
        "quantity": 1,
        }, ...and more
    ],
    "actions": [
        {
        "id": 1,
        "title": "Replacing the brakes",
        "content": "Replacing the brakes takes long practice.",
        "price": 10.50,
        }  ... and more
    ]
  }, ... and more
]
```

**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 

```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to fetch all repairs."
}
``` 

```json
POST /repair
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}

{
    "id": 1,
    "name": "Repair for mr FooBar",
    "description": "This is the description",
    "scheduled_at": "2021-02-01T00:00:00.000+00:00",
    "status": "open",
    "car": 1,
    "parts": [1,2,3],
    "actions": [1,3,4]
}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "name": "Repair for mr FooBar",
    "description": "This is the description",
    "scheduled_at": "2021-02-01T00:00:00.000+00:00",
    "status": "open",
    "car": {
        "id": 1,
        "brand": "Opel",
        "type": "Corsa",
        "license_plate": "NL-A3-AW-34",
        "mot": "2021-01-06",
        "files": [
            {
              "id": 1,
              "name": "foobar",
              "url": "123124.png"
            },
        ]
    },
    "parts": [
        {
          "id": 1,
          "name": "Sturdy brakes",
          "price": 12.50,
          "quantity": 1,
        },
        {
          "id": 2,
          "name": "Sturdy brakes 2",
          "price": 12.50,
          "quantity": 1,
        },
        {
          "id": 3,
          "name": "Sturdy brakes 3",
          "price": 12.50,
          "quantity": 1,
        },
    ],
    "actions": [
        {
          "id": 1,
          "title": "Replacing the brakes",
          "content": "Replacing the brakes takes long practice.",
          "price": 10.50,
        },
        {
          "id": 3,
          "title": "Replacing the brakes",
          "content": "Replacing the brakes takes long practice.",
          "price": 10.50,
        },
        {
          "id": 4,
          "title": "Replacing the brakes",
          "content": "Replacing the brakes takes long practice.",
          "price": 10.50,
        },
    ]
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 
```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to add a repair."
}
``` 
```json
PUT /repair
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}


{
    "id": 1,
    "name": "Repair for mr FooBar",
    "description": "This is the description",
    "scheduled_at": "2021-02-01T00:00:00.000+00:00",
    "status": "open",
    "car": 1,
    "parts": [1,2,3],
    "actions": [1,3,4]
}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "name": "Repair for mr FooBar",
    "description": "This is the description",
    "scheduled_at": "2021-02-01T00:00:00.000+00:00",
    "status": "open",
    "car": {
        "id": 1,
        "brand": "Opel",
        "type": "Corsa",
        "license_plate": "NL-A3-AW-34",
        "mot": "2021-01-06",
        "files": [
            {
              "id": 1,
              "name": "foobar",
              "url": "123124.png"
            },
        ]
    },
    "parts": [
        {
          "id": 1,
          "name": "Sturdy brakes",
          "price": 12.50,
          "quantity": 1,
        },
        {
          "id": 2,
          "name": "Sturdy brakes 2",
          "price": 12.50,
          "quantity": 1,
        },
        {
          "id": 3,
          "name": "Sturdy brakes 3",
          "price": 12.50,
          "quantity": 1,
        },
    ],
    "actions": [
        {
          "id": 1,
          "title": "Replacing the brakes",
          "content": "Replacing the brakes takes long practice.",
          "price": 10.50,
        },
        {
          "id": 3,
          "title": "Replacing the brakes",
          "content": "Replacing the brakes takes long practice.",
          "price": 10.50,
        },
        {
          "id": 4,
          "title": "Replacing the brakes",
          "content": "Replacing the brakes takes long practice.",
          "price": 10.50,
        },
    ]
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 
```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"The repair that you have tried to update does not exist."
}
``` 
```json
DEL /repair/{id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 204 No Content
Content-Type: application/json
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 
```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"The repair that you have tried to delete does not exist."
}
``` 
## File endpoints
```json
Fetches file
GET /file/{id}
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: multipart/form-data
File will be returned
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 
```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to fetch the file."
}
``` 
```json
Adds file to a car.
POST /file/{car_id}
Accept: multipart/form-data
Content-Type: multipart/form-data
Authorization: Bearer {token}

Name: Car papers
File: carpapers.png (this is the uploaded file)
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
```
```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to add a file to a car."
}
``` 
```json
Removes file from car
DEL /file/{id}/{car_id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer {token}
```
**Successful Response:**
```json
HTTP/1.1 204 No Content
Content-Type: application/json
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json
``` 
```json
HTTP/1.1 400 Bad request
Content-Type: application/json
{
  "message":"Something went wrong whilst trying to delete a file from a car."
}
``` 
