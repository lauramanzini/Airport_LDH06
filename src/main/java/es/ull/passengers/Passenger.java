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
package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.flights.Flight;

/**
 * This class defines the attributes of a flight
 */

public class Passenger {
    /**
     * The class is described by the following parameters:
     * @param identifier is the identification number of the passenger
     * @param name is the name of the passenger
     * @param countryCode is the country's code of the passenger's origin
     * @param flight is the flight's number that the passenger attends
     * For more information regarding the details of the flight consult /class Flight
     */

    private String identifier;
    private String name;
    private String countryCode;
    private Flight flight;

    /**
     * @brief Passenger attends a flight
     * @param identifier passenger identifier
     * @param name passenger name
     * @param countryCode passenger country code
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * @brief Get passenger identifier
     * @return  passenger's identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @brief Get passenger name
     * @return  passenger's name
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Get passenger country code
     * @return passenger's country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @brief Get passenger flight's details
     * @return passenger's flight
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @brief Passenger books a flight
     * @param flight flight's details
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * @brief Defines a flight
     * @param flight flight's details
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @brief Defines as 'string' the parameters
     * @return passenger's details as 'string' information
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
