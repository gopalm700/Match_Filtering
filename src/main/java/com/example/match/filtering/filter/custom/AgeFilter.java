package com.example.match.filtering.filter.custom;

import com.example.match.filtering.domain.Profile;
import java.util.function.Predicate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AgeFilter implements Predicate<Profile> {

    private int lowerBound;

    private int upperBound;

    @Override public boolean test(Profile profile) {

        return profile.getAge() != null
            && profile.getAge() >= lowerBound
            && profile.getAge() <= upperBound;
    }
}
