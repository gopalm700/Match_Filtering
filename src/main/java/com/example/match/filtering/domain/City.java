package com.example.match.filtering.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City {

    private String name;
    private Double lat;
    private Double lon;


    public Double distanceinKm(City city) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(city.lat - this.lat);
        double lonDistance = Math.toRadians(city.lon - this.lon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(city.lat)) * Math.cos(Math.toRadians(this.lat))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;
        double height = 0.0;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance) / 1000;
    }


    public  City copy() {
        return City.builder().name(name).lat(lat).lon(lon).build();
    }
}
