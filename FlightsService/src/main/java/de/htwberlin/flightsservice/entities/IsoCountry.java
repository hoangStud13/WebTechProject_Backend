package de.htwberlin.flightsservice.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "iso_country")
public class IsoCountry {

    @Column(name = "name"  ,nullable = false, unique = true)
    private String name;


    @Column(name = "code")
    @Id
    private String code;

    @OneToMany(mappedBy = "isoCountry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Airports> airports;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Airports> getAirports() {
        return airports;
    }

    public void setAirports(List<Airports> airports) {
        this.airports = airports;
    }
}
