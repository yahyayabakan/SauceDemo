@api
Feature: Booking Functionality

  Scenario: Verify user should be able to access all booking ID's
    When I send GET request to see all booking
    Then I must get a list of all booking ID's

  Scenario: Verify user should be able to access booking via specific booking id
      When I send GET request to see a specific booking id
      Then I retrieve booking information successfully

  Scenario: Verify user should not be able to retrieve information via invalid id
    When I send GET request to see a specific invalid booking id
    Then I retrieve 404 not found error

  Scenario: Create new booking and verify that booking is done
    When I create new booking by providing firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut, additionalNeeds
    Then I verify that booking is created successfully

  Scenario: Update booking totally and verify booking is updated
    When I update booking by providing firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut, additionalNeeds
    Then I verify that booking is updated correctly

  Scenario: Update booking partially and verify booking is updated
    When I update booking by providing firstName, lastName
    Then I verify that booking is updated correctly

  Scenario: Delete the booking data and verify its delete from database
    When I delete booking data
    Then I verify that same booking data was deleted by getting data by Id