# Employee information REST web service

This repository contains a maven project which demonstrates java jersey web service. The web service maintains employee
information in an organisation. The employee information can be manipulated (Add, delete, fetch) by REST APIs.

## Contents
- Maven project structure
 - Handling build dependencies, plugins packages etc.
- REST API resources
	- HTTP request handling
	- Data Transfer Object (DTO)
	- Converting Java POJO to/from XML and JSON 
- Use of external libraries in maven projects, like logger, String utilities etc.

## API methods
Following API methods are supported,
- Get list of all employees
> GET http://{serverurl:port}/employees/all

- GET information of an employee whose id is sent in HTTP request
> GET http://{serverurl:port}/employees/{id}
``` json
{
    "orgName": "My Organisation",
    "employees": [
        {
            "id": 2,
            "name": "Employee 3",
            "department": "Research"
        },
        {
            "id": 1,
            "name": "Employee 4",
            "department": "Testing"
        }
	]
}
```
- Add one or more employee record by sending xml/json in POST request body.
> POST http://{serverurl:port}/employees/add

Client sends employee data to be added in xml or json structure and set HTTP header Content-type = application/xml or application/json
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<organization>
	<orgName>My Organisation</orgName>
    <employees>
        <employee>
            <name>Employee 1</name>
            <department>Research</department>
        </employee>
        <employee>
            <name>Employee 2</name>
            <department>Testing</department>
        </employee>
    </employees>
</organization>
```
- Delete a record by employee id
> DELETE http://{serverurl:port}/employees/{id}
