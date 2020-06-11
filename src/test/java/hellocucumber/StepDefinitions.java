package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.RestServices;

import static org.junit.Assert.*;

public class StepDefinitions {
	@Given("Data is initialized")
	public void data_is_initialized() {
	    System.out.println("Data is Initialized");
	}

	@When("The Create User request is initiated")
	public void the_Create_User_request_is_initiated(String FirstName, String LastName, String Phone, String Email, String Team, String UserName, String Password) {
	    RestServices rs = new RestServices();
	    rs.postService(FirstName, LastName, Phone, Email, Team, UserName, Password);
	}

	@Then("The user should be created sucesfully")
	public void the_user_should_be_created_sucesfully() {
	    System.out.println("Validation is complete");
	}
}
