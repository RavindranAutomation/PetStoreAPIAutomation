package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import api.utilities.Dataproviders;
import io.restassured.response.Response;

public class DataDrivenUserTest {

	@Test(priority = 1, dataProvider = "Userdata", dataProviderClass = Dataproviders.class)
	public void testPostUser(String id, String userName, String firstname, String lastName, String email,
			String password, String phone) {

		UserPojo userPayload = new UserPojo();

		userPayload.setId(Integer.parseInt(id));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstname);
		userPayload.setLastName(lastName);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		userPayload.setUserStatus(0);

		Response response = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.statusCode(), 200);

	}

	@Test(priority = 2, dataProvider = "Usernames", dataProviderClass = Dataproviders.class)
	public void testDeleteUser(String userName) {
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.statusCode(), 200);


	}

}
