package utilities;

import static io.restassured.RestAssured.*;

public class RestServices {
	public void postService(String FirstName, String LastName, String Phone, String Email, String Team, String UserName, String Password) {
		String body = "{\r\n" + 
				"    \"firstName\": \""+FirstName+"\",\r\n" + 
				"    \"lastName\": \""+LastName+"\",\r\n" + 
				"    \"email\": \""+Email+"\",\r\n" + 
				"    \"phone\": \""+Phone+"\",\r\n" + 
				"    \"isManager\": \"true\",\r\n" + 
				"    \"team\": \"TrueMan\",\r\n" + 
				"    \"role\": \""+Team+"\",\r\n" + 
				"    \"userName\": \""+UserName+"\",\r\n" + 
				"    \"password\": \""+Password+"\"\r\n" + 
				"}";
		given().header("Content-Type","application/json").body(body).when().post("http://35.239.251.99:4000/users").then().statusCode(200).log().all();
	}
}
