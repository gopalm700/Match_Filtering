package com.example.match.filtering.filter.custom;

import com.example.match.filtering.domain.Profile;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;

@Component
public class FavouriteFilter implements Predicate<Profile> {
    @Override public boolean test(Profile profile) {
        return profile.getFavourite() != null && profile.getFavourite();
    }
}
