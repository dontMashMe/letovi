package com.example.letovi.models;

import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class FlightSearchForm {

    @NotNull
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    private String departureAirport;
    @NotNull
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    private String destinationAirport;

    @NotNull
    private int adult;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String departureDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String returnDate;

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }
}
