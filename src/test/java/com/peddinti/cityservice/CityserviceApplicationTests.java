package com.peddinti.cityservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.peddinti.cityservice.common.ExtentManager;
import com.peddinti.cityservice.common.ServiceProperties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest
public class CityserviceApplicationTests {
	
	private static final String BASE_URL = "http://localhost:8080";
	
	private static final String CONNECTED_PATH = "/connected";
	
	private static final String ADD_CITIES = "/connected/add";
	
	@Autowired
	ServiceProperties serviceProperties;
	
	ExtentTest parent;
	
	ExtentReports extent;

	@BeforeClass
	public void init(ITestContext context) {
		extent = ExtentManager.getInstance("ConnectedRoutes");
	}
	@Test
	void getConnectedRoute() {
		Response response = RestAssured.given().contentType(ContentType.TEXT).queryParam("origin", "California")
				.queryParam("destination", "Maryland")
				.get(BASE_URL + CONNECTED_PATH).then().assertThat()
				.statusCode(200).and().contentType(ContentType.TEXT).extract().response();
		String res = response.getBody().asString();
		ExtentTest parent = extent.createTest("getConnectedRoute").assignCategory("Connected Routes");
		parent.log(Status.PASS, "Pass");
		parent.log(Status.INFO, res);
	}
	
	@Test
	void addCities() {
		Response response = RestAssured.given().contentType(ContentType.TEXT).queryParam("origin", "California")
				.queryParam("destination", "Maryland")
				.get(BASE_URL + ADD_CITIES).then().assertThat()
				.statusCode(200).and().contentType(ContentType.TEXT).extract().response();
		String res = response.getBody().asString();
		ExtentTest parent = extent.createTest("getConnectedRoute").assignCategory("Connected Routes");
		parent.log(Status.PASS, "Pass");
		parent.log(Status.INFO, res);
	}
	
	@AfterClass
	public void AfterTestSuite() {
		extent.flush();
	}
}
