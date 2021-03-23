package com.example.letovi.repositories;

import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.example.letovi.Controllers.ApiController;
import com.example.letovi.models.Flight;
import com.example.letovi.models.FlightSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class FlightRepository {
    @Autowired
    private Amadeus amadeus;
    private final ApiController apiController;

    public FlightRepository(ApiController apiController) {
        this.apiController = apiController;
    }

    @Cacheable(value ="flightsCache") //doesn't seem to work.
    public List<Flight> getFlights(FlightSearchForm flightSearchForm) throws ResponseException {

        return convertFlightOffers(apiController.flightOfferSearchesWithParameter(flightSearchForm));
    }

    public List<Flight> convertFlightOffers(FlightOfferSearch[] flightOfferSearches) {
        List<Flight> flightList = new ArrayList<>();
        for (FlightOfferSearch flightOfferSearch : flightOfferSearches) {
            flightList.add(new Flight(amadeus, flightOfferSearch.getItineraries(), flightOfferSearch.getNumberOfBookableSeats(), flightOfferSearch.getPrice(), flightOfferSearch.isOneWay()));
        }
        return flightList;
    }
}
