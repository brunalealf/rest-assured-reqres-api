package test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User;
import pojo.UserProfile;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.emptyOrNullString;


class CreateMethodTest {
	String datePattern = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z";
	
	@BeforeAll
	public static void setup()
	{
		RestAssured.baseURI = "https://reqres.in/api";
	}
	
	@Test
	public void testStatusCode()
	{
		// Validar status 201
		User user = User.create();
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.body(user)
			.when()
				.post("users")
			.then()
				.statusCode(201)
				.body("name", is("Bruna"))
				.body("job", is("QA"))
				.body("id", matchesPattern("\\d+"))
				.body("createdAt", matchesPattern(this.datePattern))
			;
	}
	
	@Test 
	public void testInvalidURL()
	{
		// Testar URL inválida - Retornando 201 - o correto seria 404
		User user = User.create();
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.body(user)
			.when()
				.post("users/asd")
			.then()
				.statusCode(404)
				.body("name", is("Bruna"))
				.body("job", is("QA"))
				.body("id", matchesPattern("\\d+"))
				.body("createdAt", matchesPattern(this.datePattern))
			;
	}
	
	@Test
	public void testRequiredFields()
	{
		// Testar campos obrigatórios - Todos os campos  são opcionais
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.body("{}")
			.when()
				.post("users")
			.then()
				.statusCode(201)
				.body("id", matchesPattern("\\d+"))
				.body("createdAt", matchesPattern(this.datePattern))
				.body("name", is(emptyOrNullString()))
			;
	}
	
	@Test
	public void testNewFields()
	{
		// Testar inserção de campos novos - API deixa salvar e inclui o  campo no body de retorno
		UserProfile user = UserProfile.create();
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.body(user)
			.when()
				.post("users")
			.then()
				.statusCode(201)
				.body("name", is("Bruna"))
				.body("job", is("QA"))
				.body("id", matchesPattern("\\d+"))
				.body("createdAt", matchesPattern(this.datePattern))
				.body("age", is(28))
			;
	}
	
	@Test
	public void testWithoutBody()
	{
		// Testar quebra de contrato - sem json informado
		RestAssured
			.given()
				.contentType(ContentType.JSON)
			.when()
				.post("users")
			.then()
				.statusCode(201)
				.body("id", matchesPattern("\\d+"))
				.body("createdAt", matchesPattern(this.datePattern))
				.body("name", is(emptyOrNullString()))
		;
	}
}

