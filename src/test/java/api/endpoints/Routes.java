package api.endpoints;

/*1. Create User(POST)-https://petstore.swagger.io/v2/user
  2. Get user(GET)-https://petstore.swagger.io/v2/user/{username}
  3. Update user(GET)-https://petstore.swagger.io/v2/user/{username}
  4. Delete user(GET)-https://petstore.swagger.io/v2/user/{username}
*/
public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User module
	
	public static String post_url= base_url+"/user";
	public static String get_url= base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";

	//Pet module
	
	
	//Store module
}
