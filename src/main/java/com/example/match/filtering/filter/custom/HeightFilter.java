package com.example.match.filtering.filter.custom;

import com.example.match.filtering.domain.Profile;
import java.util.function.Predicate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HeightFilter implements Predicate<Profile> {
    private int lowerBound;
    private int upperBound;


    @Override public boolean test(Profile profile) {
        return profile.getHeightInCm() != null
            && profile.getHeightInCm() >= lowerBound
            && profile.getHeightInCm() <= upperBound;
    }
}
