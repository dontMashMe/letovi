package com.example.letovi.Controllers;

import com.example.letovi.models.FlightSearchForm;
import com.example.letovi.models.StoredFlights;
import com.example.letovi.models.User;
import com.example.letovi.repositories.StoredFlightsRepository;
import com.example.letovi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final StoredFlightsRepository storedFlightsRepository;

    public IndexController(UserRepository userRepository, StoredFlightsRepository storedFlightsRepository) {
        this.userRepository = userRepository;
        this.storedFlightsRepository = storedFlightsRepository;
    }

    @GetMapping("/")
    public String getIndex(Model model, HttpServletRequest request) {
        model.addAttribute("flightSearch", new FlightSearchForm());
        List<User> potentialUser = userRepository.findByIpAddress(request.getRemoteAddr());
        if(potentialUser.size() == 0) {
            return "index";
        }
        else{
            List<StoredFlights> storedFlights = storedFlightsRepository.findStoredFlightsByUser(potentialUser.get(0));
            List<StoredFlights> filtered = new ArrayList<>(storedFlights.stream().collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(StoredFlights::getDepartureAirport)))));
            model.addAttribute("storedFlights", filtered);
        }

        return "index";
    }



}

