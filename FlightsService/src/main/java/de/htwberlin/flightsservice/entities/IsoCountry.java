package de.htwberlin.flightsservice.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "isocountry_mapping")
public class IsoCountry {

    @Column(name = "name" )
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
