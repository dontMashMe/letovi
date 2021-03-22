package com.example.letovi.Controllers;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.example.letovi.models.FlightSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private Amadeus amadeus;

    public FlightOfferSearch[] flightOfferSearches() throws ResponseException {
        return  amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", "LHR")
                        .and("destinationLocationCode", "CDG")
                        .and("departureDate", "2021-04-01")
                        .and("adults", 2)
                        .and("max", 10));
    }

    public FlightOfferSearch[] flightOfferSearchesWithParameter(FlightSearchForm flightSearchForm) throws ResponseException{
        if(flightSearchForm.getDepartureDate().isEmpty()){
            return  amadeus.shopping.flightOffersSearch.get(
                    Params.with("originLocationCode", flightSearchForm.getDepartureAirport())
                            .and("destinationLocationCode", flightSearchForm.getDestinationAirport())
                            .and("departureDate", flightSearchForm.getDepartureDate())
                            .and("adults", flightSearchForm.getAdult())
                            .and("max", 10));
        }
        return  amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", flightSearchForm.getDepartureAirport())
                        .and("destinationLocationCode", flightSearchForm.getDestinationAirport())
                        .and("departureDate", flightSearchForm.getDepartureDate())
                        .and("returnDate", flightSearchForm.getReturnDate())
                        .and("adults", flightSearchForm.getAdult())
                        .and("max", 10));
    }


}
