package com.example.match.filtering.filter.custom;

import com.example.match.filtering.domain.City;
import com.example.match.filtering.domain.Profile;
import java.util.function.Predicate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DistanceFilter implements Predicate<Profile> {

    private Number lowerBound;
    private Number upperBound;
    private City userCity;


    @Override public boolean test(Profile profile) {
        Integer distance = userCity.distanceinKm(profile.getCity()).intValue();
        return distance >= lowerBound.intValue() && (upperBound != null ? distance <= upperBound.intValue() : true);
    }
}
