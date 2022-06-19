package com.SauceDemo.stepDefinitions;

import com.SauceDemo.pages.ApiPage;
import com.SauceDemo.pages.Context;
import com.beust.ah.A;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.*;

public class BookingApi {
    Context context;
    ApiPage apiPage = new ApiPage(context);

    public BookingApi(Context context){this.context = context;}

    @When("I send GET request to see all booking")
    public void I_send_get_request_to_see_all_booking() {
        apiPage.getBookingIds();
    }

    @When("I send GET request to see a specific invalid booking id")
    public void i_send_get_request_to_see_a_specific_invalid_booking_id() {
        apiPage.getBookingIdViaInvalidId();
    }
    @Then("I retrieve {int} not found error")
    public void i_retrieve_not_found_error(int error) {
        apiPage.validateInvalidId(error);
    }

    @When("I send GET request to see a specific booking id")
    public void i_send_get_request_to_see_a_specific_booking_id() {
        apiPage.getBookingById();
    }
    @Then("I retrieve booking information successfully")
    public void i_retrieve_booking_information_successfully() {
        apiPage.validateRetrievedInfo();
    }
    @Then("I must get back a valid status code {int}")
    public void I_must_get_back_a_valid_status_code(int statusCode) {
        assertEquals(statusCode, apiPage.getContext().getResponse().getStatusCode());
    }

    @Then("I must get a list of all booking ID's")
    public void I_must_get_a_list_of_all_booking_id_s() {
        assertFalse(apiPage.getListOfIds().isEmpty());
    }
    @Then("I must get {string} as {string}")
    public void I_must_get_as(String key, String value) {
        assertTrue(apiPage.getValueOfKey(key).equals(value));
    }

    @When("I create new booking by providing firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut, additionalNeeds")
    public void i_create_new_booking_by_providing_first_name_last_name_total_price_deposit_paid_check_in_check_out_additional_needs() {
        apiPage.createBooking("Yahya", "Ayabakan", 111, true, "2018-01-01","2019-01-01", "Breakfast");
    }
    @Then("I verify that booking is created successfully")
    public void i_verify_that_booking_is_created_successfully() {
        apiPage.validateBookingCreation("Yahya", "Ayabakan", 111, "true", "2018-01-01","2019-01-01", "Breakfast");
    }
    @When("I update booking of id {int} by providing firstName {string}, lastName {string}, totalPrice {int}, " +
            "depositPaid {string}, checkin {string},  checkout {string}, additionalneeds {string}")
    public void i_update_booking_of_id_by_providing_first_name_last_name_total_price_deposit_paid_checkin_checkout_additionalneeds
            (int id, String firstName, String lastName, int totalPrice, String depositPaid, String checkIn, String checkOut, String additionalNeeds) {
        apiPage.updateBooking(id,firstName,lastName,totalPrice,depositPaid,checkIn,checkOut,additionalNeeds);
    }

    @When("I delete booking data")
    public void i_delete_booking_data() {
        apiPage.deleteBookingData();
    }
    @Then("I verify that same booking data was deleted by getting data by Id")
    public void i_verify_that_same_booking_data_was_deleted_by_getting_data_by_id() {
        apiPage.validateDeletion();
    }

    @When("I update booking by providing firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut, additionalNeeds")
    public void i_update_booking_by_providing_first_name_last_name_total_price_deposit_paid_check_in_check_out_additional_needs() {
        apiPage.updateBookingPut();
    }

    @Then("I verify that booking is updated correctly")
    public void i_verify_that_booking_is_updated_correctly() {
        apiPage.validateUpdate();
    }

    @When("I update booking by providing firstName, lastName")
    public void i_update_booking_by_providing_first_name_last_name() {
        apiPage.updateBookingPatch();
    }

}
