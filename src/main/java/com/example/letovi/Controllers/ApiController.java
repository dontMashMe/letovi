package com.example.letovi.Controllers;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.example.letovi.models.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    public FlightOfferSearch[] flightOfferSearchesWithParameter(String originLocationCode, String destionationLocationCode) throws ResponseException{
        return  amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", originLocationCode)
                        .and("destinationLocationCode", destionationLocationCode)
                        .and("departureDate", "2021-04-01")
                        .and("returnDate", "2021-04-08")
                        .and("adults", 2)
                        .and("max", 3));
    }


}
