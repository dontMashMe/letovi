package com.example.letovi.models;


/*
 * This class is used as a
 *
 * */

import com.amadeus.Amadeus;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.shopping.FlightOffers;


public class Flight extends FlightOffers {


    private FlightOfferSearch.Itinerary[] itinerary;
    private int numberOfBookableSeats;
    private FlightOfferSearch.SearchPrice searchPrice;
    private boolean oneWay;


    public Flight(Amadeus client, FlightOfferSearch.Itinerary[] itinerary, int numberOfBookableSeats, FlightOfferSearch.SearchPrice searchPrice, boolean oneWay) {
        super(client);
        this.itinerary = itinerary;
        this.numberOfBookableSeats = numberOfBookableSeats;
        this.searchPrice = searchPrice;
        this.oneWay = oneWay;
    }

    public int getNumberOfStopovers() {
        int counter = 0;
        for (var a : this.itinerary) {
            counter += a.getSegments().length;
        }
        return this.oneWay ? counter - 1 : counter / 2 - 1;
        //if is one way, itinerary array is half the size.
    }

    public double getPrice(){
        return this.getSearchPrice().getTotal();
    }
    public String getCurrency(){
        return this.getSearchPrice().getCurrency();
    }

    public String getDepartureAirport(){
        return this.getItinerary()[0].getSegments()[0].getDeparture().getIataCode();
        //itinerary[0]- segment[0] is always the initial departure airport.
    }
    public String getDestinationAirport(){
        return this.getItinerary()[0].getSegments()[getItinerary()[0].getSegments().length-1].getArrival().getIataCode();
        //itinerary[0] - segment[length-1] is always the destination airport.
    }
    public String getDepartureDate(){
        var at = this.getItinerary()[0].getSegments()[0].getDeparture().getAt();

        String[] atTokens = at.split("T");
        return atTokens[0] + ", " + atTokens[1].substring(0, atTokens[1].length() - 3);
    }
    public String getArrivalDate(){
        var at = getItinerary()[0].getSegments()[getItinerary()[0].getSegments().length-1].getArrival().getAt();
        String[] atTokens = at.split("T");
        return atTokens[0] + ", " + atTokens[1].substring(0, atTokens[1].length() - 3);
    }

    public int getNumberOfBookableSeats() {
        return numberOfBookableSeats;
    }

    public void setNumberOfBookableSeats(int numberOfBookableSeats) {
        this.numberOfBookableSeats = numberOfBookableSeats;
    }

    public FlightOfferSearch.SearchPrice getSearchPrice() {
        return searchPrice;
    }

    public void setSearchPrice(FlightOfferSearch.SearchPrice searchPrice) {
        this.searchPrice = searchPrice;
    }


    public FlightOfferSearch.Itinerary[] getItinerary() {
        return itinerary;
    }

    public void setItinerary(FlightOfferSearch.Itinerary[] itinerary) {
        this.itinerary = itinerary;
    }

    public boolean isOneWay() {
        return oneWay;
    }

    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }
}
