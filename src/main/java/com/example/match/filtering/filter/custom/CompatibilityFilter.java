package com.example.match.filtering.filter.custom;

import com.example.match.filtering.domain.Profile;
import java.util.function.Predicate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompatibilityFilter implements Predicate<Profile> {

    private int lowerBound;
    private int upperBound;


    @Override public boolean test(Profile profile) {
        if (null != profile.getCompatibilityScore()) {
            Integer compatible = Double.valueOf(profile.getCompatibilityScore() * 100).intValue();
            return compatible >= lowerBound && compatible <= upperBound;
        }else{
            return false;
        }
    }
}
