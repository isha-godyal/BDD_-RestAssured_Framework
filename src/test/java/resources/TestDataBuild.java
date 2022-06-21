package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

// Moving Test Data code from step Defination to another file 
public class TestDataBuild {

	public AddPlace addPlacePayLoad() {
		// Create place object and set value which we can pass in request body of
		// addPlace call
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setAddress("29, side layout, cohen 09");
		place.setLanguage("French-IN");
		place.setPhone_number("(+91) 983 893 3937");
		place.setWebsite("https://rahulshettyacademy.com");
		place.setName("Frontline house");

		// create list to store types
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		// set types in place object using setTypes method
		place.setTypes(myList);

		// Created nested object location and set lat and long
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);

		// set location value in place using setLocation method
		place.setLocation(loc);

		return place;
	}

}
