# create spring boot application with dynamodb

Spring boot
java 1.8
Dynamodb localset up
aws cli

Create dynamodb configuration and mapper for save operation.


Table:
create table using local aws cli

aws dynamodb create-table --table-name ProductCatalog --attribute-definitions AttributeName=id,AttributeType=S AttributeName=title,AttributeType=S --key-schema AttributeName=id,KeyType=HASH AttributeName=title,KeyType=RANGE --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5


query 
aws dynamodb query --table-name ProductCatalog --key-condition-expression "title = :name" --expression-attribute-values  '{":name":{"S":"mytitle"}}'

postman urls :

for create:

http://localhost:8080/dynamodb/create

request body:

{
"title":"Delloite",
"isbn":"123"
}
