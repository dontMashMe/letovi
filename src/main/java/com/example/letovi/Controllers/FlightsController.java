package com.example.letovi.Controllers;

import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.example.letovi.models.Flight;
import com.example.letovi.models.FlightSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightsController {
    private final ApiController apiController;

    @Autowired
    private Amadeus amadeus;

    public FlightsController(ApiController apiController) {
        this.apiController = apiController;
    }


    @PostMapping
    public String processForm(@ModelAttribute FlightSearchForm flightSearchForm, BindingResult bindingResult, Model model) throws ResponseException {
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()){
            return "index";
        }
        List<Flight> flightList = convertFlightOffers(apiController.flightOfferSearchesWithParameter(flightSearchForm));
        model.addAttribute("flights", flightList);
        return "flights";
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
