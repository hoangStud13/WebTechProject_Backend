package de.htwberlin.flightsservice.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "airports_data_formatted_csv")
public class Airports {

    @Column(name = "ident")
    private String ident;


    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Size(min = 2, max = 2)
    @Column(name = "continent")
    private String continent;

    @Size(min = 2, max = 2)
    @Column(name = "iso_country")
    private String isoCountry;

    @Size(min = 2, max = 2)
    @Column(name = "iso_country", insertable = false, updatable = false)
    private String isoCountryCode;
    @Column(name = "municipality")
    private String municipality;

    @Id
    @Size(min = 3, max = 3)
    @Column(name = "iata_code")
    private String iataCode;

    @Column(name = "coordinates")
    private String coordinate;


    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getIsoCountry() {
        return isoCountry;
    }

    public void setIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public void setIsoCountryCode(String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }
}