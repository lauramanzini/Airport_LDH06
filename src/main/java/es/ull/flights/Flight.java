/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package es.ull.flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.passengers.Passenger;

/**
 * This class defines the attributes of a flight
 */

public class Flight {

    /**
     * The class is described by the following parameters:
     * @param flightNumber is the number of a flight
     * @param seats is the number of seats available on the flight
     * @param passengers is a list of the passengers that booked a flight
     * For more information regarding the details of the passengers consult the class \class Passengers
     */

    private String flightNumber;
    private int seats;
    private Set<Passenger> passengers = new HashSet<>();

    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$";
    private static Pattern pattern = Pattern.compile(flightNumberRegex);

    /**
     * @brief Flight information
     * @param flightNumber flight number
     * @param seats seat number
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /**
     * @brief Get the number of the flight
     * @return flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @brief Get number of passengers on board of the flight
     * @return number of passengers
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /**
     * @brief Adding passengers to a flight
     * @param passenger passenger booking a flight
     * @return verify if passenger successfully booked a flight
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /**
     * @brief Remove a passenger from a flight
     * @param passenger passenger who has booked the flight
     * @return verify if passenger was successfully removed from the flight
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}
