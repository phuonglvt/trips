package com.example.trips.model;


public class Trip {
    private String id, destination, description, dateOfTheTrip;
    private Boolean requireAssessment;

    public Trip() {
    }

    public Trip(String destination, Boolean requireAssessment, String description, String id, String dateOfTheTrip) {
        this.destination = destination;
        this.requireAssessment = requireAssessment;
        this.description = description;
        this.id = id;
        this.dateOfTheTrip = dateOfTheTrip;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Boolean getRequireAssessment() {
        return requireAssessment;
    }

    public void setRequireAssessment(Boolean requireAssessment) {
        this.requireAssessment = requireAssessment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateOfTheTrip() {
        return dateOfTheTrip;
    }

    public void setDateOfTheTrip(String dateOfTheTrip) {
        this.dateOfTheTrip = dateOfTheTrip;
    }
}
