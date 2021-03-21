package com.example.letovi.Controllers;

import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.example.letovi.models.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    private final ApiController apiController;
    @Autowired
    private Amadeus amadeus;

    public IndexController(ApiController apiController) {
        this.apiController = apiController;
    }

    @GetMapping
    public String getIndex(Model model) throws ResponseException {
        List<Flight> flightList = convertFlightOffers(apiController.flightOfferSearches());
        model.addAttribute("flights", flightList);
        return "index";
    }

    public List<Flight> convertFlightOffers(FlightOfferSearch[] flightOfferSearches)
    {
        List<Flight> flightList = new ArrayList<>();
        for (FlightOfferSearch flightOfferSearch : flightOfferSearches) {
            flightList.add(new Flight(amadeus, flightOfferSearch.getItineraries(), flightOfferSearch.getNumberOfBookableSeats(), flightOfferSearch.getPrice(), flightOfferSearch.isOneWay()));
        }
        return flightList;
    }
}

/*



*/