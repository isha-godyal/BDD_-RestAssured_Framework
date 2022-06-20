# Feature is like Test Suite
Feature: Validating Place API's

# Scenario is like Test Case
Scenario: Verify if Place is being Succesfully added using AddPlaceAPI
	Given Add Place Payload 
	When user calls AddPlaceAPI with POST http request
	Then the API call got success with status code 200
	And status in response body is OK