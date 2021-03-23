package com.example.letovi.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ipAddress;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<StoredFlights> storedFlights = new ArrayList<>();

    public User() {

    }



    public User(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<StoredFlights> getStoredFlights() {
        return storedFlights;
    }

    public void setStoredFlights(List<StoredFlights> storedFlights) {
        this.storedFlights = storedFlights;
    }


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
