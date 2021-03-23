package com.example.letovi.Controllers;

import com.example.letovi.models.StoredFlights;
import com.example.letovi.repositories.StoredFlightsRepository;
import com.example.letovi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/flights/recent")
public class RecentFlightsController {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final StoredFlightsRepository storedFlightsRepository;

    public RecentFlightsController(UserRepository userRepository, StoredFlightsRepository storedFlightsRepository) {
        this.userRepository = userRepository;
        this.storedFlightsRepository = storedFlightsRepository;
    }

    @GetMapping
    public String getIndex(@RequestParam String dep, String dest, String date, Model model){
        System.out.println(dep + ":" + dest + ":" +date);
        List<StoredFlights> storedFlightsList = storedFlightsRepository.findStoredFlightsByDepartureAirportAndDestinationAirportAndDepartureDateContaining(dep, dest, date);
        model.addAttribute("storedFlights", storedFlightsList);
        return "flightsRecent";
    }
}
