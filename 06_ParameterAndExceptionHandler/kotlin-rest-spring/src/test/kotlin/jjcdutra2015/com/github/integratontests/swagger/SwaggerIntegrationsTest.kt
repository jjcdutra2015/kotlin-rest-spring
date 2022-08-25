package jjcdutra2015.com.github.integratontests.swagger

import io.restassured.RestAssured.given
import jjcdutra2015.com.github.integratontests.TestConfigs
import jjcdutra2015.com.github.integratontests.testcontaners.AbstractIntegrationTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationsTest : AbstractIntegrationTest() {

    @Test
    fun shouldDisplaySwaggerUiPage() {
        val content = given()
            .basePath("/swagger-ui/index.html")
            .port(TestConfigs.SERVER_PORT)
            .`when`()
            .get()
            .then()
            .statusCode(200)
            .extract()
            .body()
            .asString()

        assertTrue(content.contains("Swagger UI"))
    }
}
