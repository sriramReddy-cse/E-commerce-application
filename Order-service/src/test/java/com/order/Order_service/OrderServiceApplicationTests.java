package com.order.Order_service;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

	@ServiceConnection
	@Container
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql");


	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		// Setting the base URI and port for RestAssured
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = this.port;
	}



	@Test
	void submitOrder() {
		String requestBody = """
				{
					"skuCode": "iphone-15",
					"quantity": 1,
					"price": 50000
				}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/order")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("skuCode", Matchers.equalTo("iphone-15"))
				.body("quantity", Matchers.equalTo(1))
				.body("price", Matchers.equalTo(50000));
	}

}
