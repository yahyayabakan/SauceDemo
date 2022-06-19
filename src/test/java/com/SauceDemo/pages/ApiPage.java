package com.SauceDemo.pages;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;


public class ApiPage {
    Context context;

    public ApiPage(Context context){this.context = new Context();}

    public Context getContext() {
        return context;
    }

    public void getBookingIds(){
        context.setResponse(
                given()
                    .baseUri("https://restful-booker.herokuapp.com/")
                    .basePath("booking").
                when().get());
        context.getResponse().prettyPrint();
    }

    public List getListOfIds(){
        assertThat(context.getResponse().getStatusCode(), is(200));
        return context.getResponse().jsonPath().getList("bookingid");
    }

    public void getBookingById(){
        int id = given().get("https://restful-booker.herokuapp.com/booking").path("bookingid[0]");
        context.setResponse(
                given()
                        .baseUri("https://restful-booker.herokuapp.com/")
                        .basePath("booking")
                        .pathParam("id",id).
                        when().get("{id}"));
    }

    public void validateRetrievedInfo(){
        assertTrue(context.getResponse().statusCode()==200);
    }

    public String getValueOfKey(String key){
        return context.getResponse().jsonPath().getString(key);
    }

    public void createBooking(String firstName, String lastName, int totalPrice, boolean depositPaid,
                              String checkIn, String checkOut, String additionalNeeds){
        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", firstName);
        booking.put("lastname", lastName);
        booking.put("totalprice", totalPrice);
        booking.put("depositpaid", depositPaid);
        booking.put("additionalneeds", additionalNeeds);
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", checkIn);
        bookingDates.put("checkout", checkOut);
        booking.put("bookingdates", bookingDates);

        context.setResponse(
                given()
                .contentType(ContentType.JSON)
                .body(booking).
                when()
                .post("https://restful-booker.herokuapp.com/booking"));
        context.getResponse().prettyPrint();

        context.setCreatedBookingID(context.getResponse().jsonPath().getInt("bookingid"));
    }

    public void validateBookingCreation(String firstName, String lastName, int totalPrice, String depositPaid,
                                        String checkIn, String checkOut, String additionalNeeds){
        Response response =
                given()
                        .baseUri("https://restful-booker.herokuapp.com/")
                        .basePath("booking")
                        .pathParam("id",context.getCreatedBookingID()).
                        when().get("{id}");
        assertTrue(response.getStatusCode()==200);
        assertTrue(firstName.equals(response.jsonPath().getString("firstname")));
        assertTrue(lastName.equals(response.jsonPath().getString("lastname")));
        assertTrue(totalPrice == response.jsonPath().getInt("totalprice"));
        assertTrue(checkIn.equals(response.jsonPath().getString("bookingdates.checkin")));
        assertTrue(checkOut.equals(response.jsonPath().getString("bookingdates.checkout")));
        assertTrue(additionalNeeds.equals(response.jsonPath().getString("additionalneeds")));
    }

    public void updateBooking(int id, String firstName, String lastName, int totalPrice,
                              String depositPaid, String checkIn, String checkOut, String additionalNeeds){
        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", firstName);
        booking.put("lastname", lastName);
        booking.put("totalprice", totalPrice);
        if(depositPaid.equals("true")) booking.put("depositpaid", true);
        else booking.put("depositpaid", false);
        booking.put("additionalneeds", additionalNeeds);
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", checkIn);
        bookingDates.put("checkout", checkOut);
        booking.put("bookingdates", bookingDates);

        //context.setResponse(
        String token = getToken();
        System.out.println(token);

        Response response=        given()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("booking")
                .pathParam("id",id)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(booking)
                .auth().preemptive().basic("admin","password123").
                when().put("{id}");
        System.out.println(response.getStatusCode());
    }

    public String getToken() {
        Map<String, Object> credentials = new HashMap<>();
        credentials.put("username","admin");
        credentials.put("password","password123");
        String accessToken = given().contentType(ContentType.JSON).body(credentials).when().post("https://restful-booker.herokuapp.com/auth").path("token");
        return accessToken;
    }

    public void deleteBookingData(){
        context.setDeletedID(given().get("https://restful-booker.herokuapp.com/booking").jsonPath().getInt("bookingid[0]"));
        Response response = given().auth().preemptive().basic(
                "admin", "password123").delete(String.format(
                "https://restful-booker.herokuapp.com/booking/%s", context.getDeletedID()));
        response.prettyPrint();
    }

    public void validateDeletion(){
        assertThat(get(String.format(
                "https://restful-booker.herokuapp.com/booking/%s", context.getDeletedID())).getStatusCode(), is(404));
    }

    public void updateBookingPut(){
        //first create a new booking entity
        createBooking("Yahya", "Ayabakan", 111, true, "2022-02-01", "2022-02-03","Baby Chair");

        //We can use JSONObject in order to update booking entity too, other than java Map<String, String> structure
        JSONObject jsonObjectUpdate = new JSONObject();
        jsonObjectUpdate.put("firstname", "Naeem");
        jsonObjectUpdate.put("lastname", "Mal");
        jsonObjectUpdate.put("totalprice", 111);
        jsonObjectUpdate.put("depositpaid", false);
        jsonObjectUpdate.put("additionalneeds", "Baby Chair");
        JSONObject bookingDates2 = new JSONObject();
        bookingDates2.put("checkin", "2022-02-02");
        bookingDates2.put("checkout", "2022-02-04");
        jsonObjectUpdate.put("bookingdates", bookingDates2);

        context.setResponse(given().
                auth().preemptive().basic("admin", "password123").
                contentType(ContentType.JSON).body(jsonObjectUpdate.toString()).
                put( String.format("https://restful-booker.herokuapp.com/booking/%s", context.getCreatedBookingID())));
        context.getResponse().prettyPrint();
    }

    public void validateUpdate(){
        assertEquals(context.getResponse().getStatusCode(), 200, "Response is not 200.");
    }

    public void updateBookingPatch(){
        //first create a new booking for testing purpose
        createBooking("Yahya", "Ayabakan", 111, true, "2022-02-01", "2022-02-03","Baby Chair");

        //then update firstname and lastname fields with new values
        Map<String, String> update = new HashMap<>();
        update.put("firstname", "Yusuf");
        update.put("lastname", "Ayaba");

        //send patch request with created booking id
        context.setResponse(given().
                auth().preemptive().basic("admin", "password123").
                contentType(ContentType.JSON).body(update).patch(
                        String.format("https://restful-booker.herokuapp.com/booking/%s", context.getCreatedBookingID())));
        context.getResponse().prettyPrint();
    }

    public void getBookingIdViaInvalidId(){
        context.setResponse(given().get("https://restful-booker.herokuapp.com/booking/9999999"));
    }

    public void validateInvalidId(int error){
        assertThat(context.getResponse().getStatusCode(), is(error));
    }
}
