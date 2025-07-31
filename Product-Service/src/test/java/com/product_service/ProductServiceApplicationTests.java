package com.product_service;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//to run testing in random port to avoid conflict from our application which is runningh on the port 8080
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

	@LocalServerPort
    private Integer port;

	@BeforeEach
	void setup(){
		RestAssured.baseURI="http://localhost";
		RestAssured.port=this.port;
	}

	//there should be a static block is added to start the containers before starting out testing
	static {
		 mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {

		String requestBody = """
				   {
				       "name":"iphone 15",
				       "description":"It is a brand",
				       "price":250000
				   }
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name",Matchers.equalTo("iphone 15"))
				.body("description",Matchers.equalTo("It is a brand"))
				.body("price",Matchers.equalTo(250000));
	}

}
