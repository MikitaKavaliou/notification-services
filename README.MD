# NOTIFICATION-SERVICES PROJECT

## Description

Project contains 2 services communicating with each other using AMQP implementation provided by RabbitMQ.

## Quick Start

* Clone repository
* Run ```docker-compose up``` command to launch required container
* Launch microservices
* Download postman request collection https://www.getpostman.com/collections/af23f3466939864cba78
* Send a predefined request with the name ```Create new send-notification with type SMS```. Server responds with code 
  of 200 and newly created entity with the ```IN_PROGRESS``` status. When processing ends, status will be changed
  to ```FAILED``` because of SMS Sender config.
* Send a predefined request with the name ```Create new send-notification with type EMAIL```. Server responds with code
  of 200 and newly created entity with the ```IN_PROGRESS``` status. When processing ends, status will be changed
  to ```COMPLETED```.  
  