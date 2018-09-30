package com.zomato.test;

import java.util.Map;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestConfig {

		public static RequestSpecification requestSpecSetup() {
			
			

		RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("https://developers.zomato.com")
				.setBasePath("/api/v2.1").addHeader("Accept", "application/json")
				.addHeader("user-key", "46a2d15905fc94e8a96b510caa881f8b").build();
				return requestSpec;

	}
		
		public static RequestSpecification invalidrequestSpecSetup() {
			
			

			RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("https://developers.zomato.com")
					.setBasePath("/api/v2.1").addHeader("Accept", "application/json")
					.addHeader("user-key", "46a2d15905fc996b510caa881f8b").build();
					return requestSpec;

		}

	public static ResponseSpecification responseSpecSetup() {

		ResponseSpecification responseSpec = new ResponseSpecBuilder().expectContentType("application/json").build();
		RestAssured.responseSpecification = responseSpec;

		return responseSpec;
	}

}
