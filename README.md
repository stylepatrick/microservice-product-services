# microservice-product-service

This repository is seperated into docker-compose and kubernetes folders.
The two seperated projects do the same, but there are different technologies used depending if docker or kubernetes is used. 

Following technologies are used in the Project:

	Spring Boot 
 	Sring Cloud
	Spring Stream
 	MongoDb
	MySql 
 	Keycloak
	Relilient4J
	Zipkin
	Swagger
   

The docker-compose folder contains additionally following technologies:

 	Eureka
	Config-Server	
	Spring Gateway
 

The kubernetes folder contains following technologie, where some of the docker-compose are removed (Eureka, Config-Server, Gateway):

 	EFK
	Promotheus
	Grafana
	HELM

The examples are based on the book "Microservices-with-Spring-Boot-and-Spring-Cloud-Third-Edition"


