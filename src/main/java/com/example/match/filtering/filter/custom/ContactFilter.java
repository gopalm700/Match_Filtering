package com.example.match.filtering.filter.custom;

import com.example.match.filtering.domain.Profile;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

@Component
public class ContactFilter implements Predicate<Profile> {
    @Override public boolean test(Profile profile) {
        return profile.getContactExchanged()!= null
            && profile.getContactExchanged() > 0;
    }
}
