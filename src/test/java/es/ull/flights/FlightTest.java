package es.ull.flights;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class defines different tests for the class Flight
 */
@DisplayName("Flight Test")
class FlightTest {
    /**
     * In order to run the different tests two different flights and two different passengers are initially created
     * @param flight first flight
     * @param flight2 second flight
     * @param passenger first passenger
     * @param passenger2 second passenger
     */
    private Flight flight;
    private Flight flight2;
    private Passenger passenger;
    private Passenger passenger2;

    @BeforeEach
    void setUp(){
        flight = new Flight("FR4849", 20);
        flight2 = new Flight ("BC4624", 1);
        passenger = new Passenger ("id1", "Francesco", "IT");
        passenger2 = new Passenger ("id2", "Anna", "ES");
    }

    /**
     * @brief Test to verify if it's possible to add passengers
     */
    @Test
    @DisplayName("Add Passenger Test")
    void testAddPassenger() {
        assertAll("Verify that the passenger is correctly assigned to a flight",
                () -> assertTrue(flight.addPassenger(passenger), "Passenger is assigned to the flight"),
                () -> assertEquals(1, flight.getNumberOfPassengers(), "Number of passengers"),
                () -> assertTrue(flight2.addPassenger(passenger), "Adding a passenger"),
                () -> assertThrows(RuntimeException.class, ()-> flight2.addPassenger(passenger2), "Not possible to add more passengers")
        );
    }

    /**
     *  @brief Test to verify if it's possible to get a flight's details
     */
    @Test
    @DisplayName("Get Flight Details")
    void testGetFlightDetails() {
        flight.addPassenger(passenger);
        assertAll("Verify the flight's details",
                () -> assertEquals("FR4849", flight.getFlightNumber(), "Flight's number"),
                () -> assertEquals(1, flight.getNumberOfPassengers(), "Number of passengers")
        );
    }

    /**
     *@brief Test to verify if it's possible to remove a passenger
     */
    @Test
    @DisplayName("Remove Passenger Test")
    void testRemovePassenger() {
        assertAll("Verify if it is possible to remove passenger",
                () -> assertTrue(flight2.addPassenger(passenger), "Added passenger to flight"),
                () -> assertTrue(flight2.removePassenger(passenger), "Removed passenger from the flight"),
                () -> assertFalse(flight2.removePassenger(passenger2), "The passenger is not on the flight and cannot be removed")
        );
    }
}