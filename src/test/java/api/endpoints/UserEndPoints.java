package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UserEndPoints {


	public static Response createUser(UserPojo payload) {

		Response response= given()
				.contentType(ContentType.JSON)
				.accept("application/json")
				.header("api_key", "special-key")
				.body(payload)
				.when().post(Routes.post_url);
		return response;

	}

	public static Response getUser(String userName) {

		Response response= given()
				.pathParam("username", userName)
				.accept("application/json")
				.header("api_key", "special-key")
				.when()
				.get(Routes.get_url);
		return response;

	}

	public static Response updateUser(String userName, UserPojo payload) {

		Response response= given()
				.contentType(ContentType.JSON)
				.accept("application/json")
				.header("api_key", "special-key")
				.pathParam("username", userName)
				.body(payload)
				.when().put(Routes.update_url);
		return response;

	}

	public static Response deleteUser(String userName) {

		Response response= given()
				.header("api_key", "special-key")
				.pathParam("username", userName)
				.when()
				.get(Routes.delete_url);
		return response;

	}

}
