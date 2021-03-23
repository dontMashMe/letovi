package com.example.letovi.Controllers;

import com.amadeus.exceptions.ResponseException;
import com.example.letovi.models.Flight;
import com.example.letovi.models.FlightSearchForm;
import com.example.letovi.models.StoredFlights;
import com.example.letovi.models.User;
import com.example.letovi.repositories.FlightRepository;
import com.example.letovi.repositories.StoredFlightsRepository;
import com.example.letovi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightsController {

    @Autowired
    private final FlightRepository flightRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final StoredFlightsRepository storedFlightsRepository;


    public FlightsController(UserRepository userRepository, FlightRepository flightRepository, StoredFlightsRepository storedFlightsRepository) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.storedFlightsRepository = storedFlightsRepository;
    }

    /*
    * First time a user searches something, his IP address and search params are saved in an in-memory database.
    * When he makes a second search, we check if his search params are equal.
    * if they are, load the data from the database.
    *
    * *NOTE*
    * on localhost the ip address alternates between 0:0:0:0:0:0:0:1 and 127.0.0.1 so results may wary.
    * on a remote server it should work.
    *
    * the database should have a flushing mechanism as to not keep stale data.
    *
    *
    * */

    @PostMapping
    public String processForm(@ModelAttribute FlightSearchForm flightSearchForm, BindingResult bindingResult, Model model, HttpServletRequest request) throws ResponseException {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        List<User> potentialUser = userRepository.findByIpAddress(request.getRemoteAddr());

        if(potentialUser.size() == 0){
            List<Flight> flightList = flightRepository.getFlights(flightSearchForm);
            saveRecentFlightSearches(request.getRemoteAddr(), flightList);
            model.addAttribute("flights", flightList);
            return "flights";
        }
        else{
            List<StoredFlights> storedFlights = storedFlightsRepository.findStoredFlightsByUser(potentialUser.get(0));
            storedFlights.removeIf(a -> !a.getDepartureAirport().equals(flightSearchForm.getDepartureAirport())
                    || !a.getDestinationAirport().equals(flightSearchForm.getDestinationAirport())
                    || !a.getDepartureDate().substring(0, a.getDepartureDate().length() - 7).equals(flightSearchForm.getDepartureDate()));
            if(storedFlights.size() > 0){
                System.out.println("Loaded data from DB");
                model.addAttribute("storedFlights", storedFlights);
                return "flightsRecent";
            }
            else{
                System.out.println("Loaded data from API");
                List<Flight> flightList = flightRepository.getFlights(flightSearchForm);
                saveRecentFlightSearches(request.getRemoteAddr(), flightList);
                model.addAttribute("flights", flightList);
                return "flights";
            }

        }

    }

    private void saveRecentFlightSearches(String userIp, List<Flight> flightList) {

        List<User> potentialUser = userRepository.findByIpAddress(userIp);
        List<StoredFlights> storedFlights = new ArrayList<>();

        if (potentialUser.size() > 0) {
            User user = potentialUser.get(0);
            for (var a : flightList) {
                StoredFlights storedFlightsTemp = new StoredFlights(a.getDepartureAirport(), a.getDestinationAirport(), a.getDepartureDate(), a.getNumberOfStopoversFirstPart(), a.getReturnDate(), a.getNumberOfStopoversSecondPart(), a.getNumberOfBookableSeats(), a.getCurrency(), a.getPrice(), user);
                storedFlightsRepository.save(storedFlightsTemp);
            }

        } else {
            User user = new User();
            user.setIpAddress(userIp);
            for (var a : flightList) {
                storedFlights.add(new StoredFlights(a.getDepartureAirport(), a.getDestinationAirport(), a.getDepartureDate(), a.getNumberOfStopoversFirstPart(), a.getReturnDate(), a.getNumberOfStopoversSecondPart(), a.getNumberOfBookableSeats(), a.getCurrency(), a.getPrice(), user));
            }
            user.setStoredFlights(storedFlights);
            userRepository.save(user);
        }

    }


}
