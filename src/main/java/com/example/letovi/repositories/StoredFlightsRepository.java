package com.example.letovi.repositories;

import com.example.letovi.models.StoredFlights;
import com.example.letovi.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoredFlightsRepository extends CrudRepository<StoredFlights, Long> {

    //List<StoredFlights> findStoredFlightsByDepartureAirportAndDestinationAirportAndDepartureDate(String departureAirport, String destinationAirport, String departureDate);

    List<StoredFlights> findStoredFlightsByUser(User user);
    List<StoredFlights> findStoredFlightsByDepartureAirportAndDestinationAirportAndDepartureDateContaining(String departureAirport, String destinationAirport, String departureDate);

}
