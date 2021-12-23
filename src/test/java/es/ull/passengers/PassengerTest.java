package es.ull.passengers;

import es.ull.flights.Flight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines different tests for the class Passenger
 */

class PassengerTest {
    /**
     * In order to run the different tests a passenger and a flight are initially created
     * To prove that a flight without seats cannot be joined by any passenger an addition flight is created
     * @param passenger passenger
     * @param flight flight
     * @param emptyFlight empty flight
     */
    private Passenger passenger;
    private Flight flight;
    private Flight emptyFlight;

    @BeforeEach
    void setUp() {
        passenger = new Passenger("id1", "Francesco", "IT");
        flight = new Flight("FR4849", 12);
        emptyFlight = new Flight("LB2457", 0);
    }

    /**
     *@brief Test to verify if it's possible to get a passenger's country code
     */
    @Test
    @DisplayName("Get Passenger's Country Code")
    void testGetCountryCode() {
        assertEquals("IT", passenger.getCountryCode(), "Passenger's country code");
    }

    /**
     *@brief Test to verify if it's possible to get a passenger's identifier
     */
    @Test
    @DisplayName("Get Passenger's Identifier ")
    void testGetIdentifier() {
        assertEquals("id1", passenger.getIdentifier(), "Passenger's identifier");
    }

    /**
     *@brief Test to verify if it's possible to get a passenger's name
     */
    @Test
    @DisplayName("Get Passenger's Name")
    void testGetName() {
        assertEquals("Francesco", passenger.getName(), "Passenger's name");
    }

    /**
     *@brief Test to verify if it's possible to convert a passenger's parameters to string
     */
    @Test
    @DisplayName("Passenger's toString")
    void testToString() {
        assertEquals("Passenger Francesco with identifier: id1 from IT" , passenger.toString());
    }

    /**
     *@brief Test to verify if it's possible for a passenger to join a flight
     */
    @Test
    @DisplayName("Passenger joining a flight")
    void testJoinFlight() {
        assertAll("Verify is passenger is assigned correctly to a flight",
                () -> assertDoesNotThrow(()-> passenger.joinFlight(flight), "We can join a flight"),
                () -> assertEquals(flight, passenger.getFlight(), "The flight is set correctly"),
                () -> assertThrows(RuntimeException.class, () -> passenger.joinFlight(emptyFlight), "We cannot join a flight")
        );
    }
}