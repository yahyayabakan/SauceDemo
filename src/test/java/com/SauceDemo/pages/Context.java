package com.SauceDemo.pages;

import io.restassured.response.Response;

public class Context {
    private Response response = null;
    private int createdBookingID = 0;
    private int deletedID = 0;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public int getCreatedBookingID() {
        return createdBookingID;
    }

    public void setCreatedBookingID(int createdBookingID) {
        this.createdBookingID = createdBookingID;
    }

    public int getDeletedID() {
        return deletedID;
    }

    public void setDeletedID(int deletedID) {
        this.deletedID = deletedID;
    }
}
