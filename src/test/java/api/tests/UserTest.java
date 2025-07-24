package api.tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import io.restassured.response.Response;

public class UserTest {
	Faker faker;
	UserPojo userPayload;
	
	public Logger logger;

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new UserPojo();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setUserStatus(0);
		logger= LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void testPostUserByName() {
		
		logger.info("**************** Creating a user **********************");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("*************** User is created ***********************");
	}

	@Test(priority = 2)
	public void getUser() {
		logger.info("*************** Retreving a user with username ***********************");
		Response response = UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("**************** User is retrived **********************");
	}

	@Test(priority = 3)
	public void updateUserByName() {
		logger.info("*************** Updating a user ***********************");
		// Update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());

		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("*************** User is updated ***********************");
		
		logger.info("***************** Validating a user after update *********************");
		// Checking data after Update
		Response responseAfterUpdate = UserEndPoints.getUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.statusCode(), 200);

	}

	@Test(priority = 4)
	public void deleteUserByName() {
		logger.info("************** Deleting a user ************************");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("**************** User is deleted **********************");
	}

}
