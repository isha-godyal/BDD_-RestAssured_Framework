# Feature is like Test Suite
Feature: Validating Place API's

# Scenario is like Test Case
Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
	Given Add Place Payload with "<name>"  "<language>" "<address>" 
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	
Examples:
	|name 	 | language |address		   |
	|AAhouse |  English |World cross center|
#	|BBhouse |  Spanish |Sea cross center  |

#resueable step we can use different service in double quotes
#  "AddPlaceAPI" "status" example 
	