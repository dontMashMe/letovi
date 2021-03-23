package com.example.letovi.models;

import javax.persistence.*;

@Entity
public class StoredFlights {
    @Id
    @GeneratedValue
    private Long id;
    private String departureAirport;
    private String destinationAirport;
    private String departureDate;
    private int stopoverFirstPart;
    private String returnDate;
    private int stopoverSecondPart;
    private int bookableSeats;
    private String currency;
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="user_id")
    private User user;

    public StoredFlights() {

    }

    public StoredFlights(String departureAirport, String destinationAirport, String departureDate, int stopoverFirstPart, String returnDate, int stopoverSecondPart, int bookableSeats, String currency, Double price, User user) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
        this.stopoverFirstPart = stopoverFirstPart;
        this.returnDate = returnDate;
        this.stopoverSecondPart = stopoverSecondPart;
        this.bookableSeats = bookableSeats;
        this.currency = currency;
        this.price = price;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public int getStopoverFirstPart() {
        return stopoverFirstPart;
    }

    public void setStopoverFirstPart(int stopoverFirstPart) {
        this.stopoverFirstPart = stopoverFirstPart;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getStopoverSecondPart() {
        return stopoverSecondPart;
    }

    public void setStopoverSecondPart(int stopoverSecondPart) {
        this.stopoverSecondPart = stopoverSecondPart;
    }

    public int getBookableSeats() {
        return bookableSeats;
    }

    public void setBookableSeats(int bookableSeats) {
        this.bookableSeats = bookableSeats;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
