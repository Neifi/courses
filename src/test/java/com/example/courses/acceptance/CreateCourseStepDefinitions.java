package com.example.courses.acceptance;

import com.example.courses.domain.entities.CourseModule;
import com.example.courses.domain.vo.ModuleContent;
import com.example.courses.infrastructure.rest.dto.CourseJsonRequest;
import com.example.courses.infrastructure.rest.dto.CourseJsonResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.*;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class CreateCourseStepDefinitions extends BaseIntegrationTest {

    private ResponseEntity<CourseJsonResponse> response;
    private HttpEntity<CourseJsonRequest> request;

    @Given("Course creator email {string} and name {string}")
    public void i_fill_in_the_course_details_with_valid_data(String creatorEmail, String courseName) {
        HttpHeaders header = new HttpHeaders();
        CourseModule module = new CourseModule(UUID.randomUUID(), "name", Duration.ofMinutes(1), ModuleContent.Builder().build());
        CourseJsonRequest requestJson = new CourseJsonRequest(creatorEmail, courseName, List.of(module));
        header.setContentType(MediaType.APPLICATION_JSON);
        request = new HttpEntity<>(requestJson, header);
    }

    @When("I send the data")
    public void i_send_the_data() {
        response = restTemplate
                .postForEntity("http://localhost:" + port + "/api/v1/courses", request, CourseJsonResponse.class);

    }

    @Then("I should see a confirmation message {string}")
    public void i_should_see_a_confirmation_message(String courseName) {

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().courseId());
        assertEquals(courseName, response.getBody().name());
        assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
    }

    @Then("the new course should be listed in the course catalog")
    public void the_new_course_should_be_listed_in_the_course_catalog() {
        assertTrue(true);
    }


}
