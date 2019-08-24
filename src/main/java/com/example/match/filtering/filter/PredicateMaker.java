package com.example.match.filtering.filter;

import com.example.match.filtering.domain.Profile;
import com.example.match.filtering.filter.custom.AgeFilter;
import com.example.match.filtering.filter.custom.CompatibilityFilter;
import com.example.match.filtering.filter.custom.ContactFilter;
import com.example.match.filtering.filter.custom.DistanceFilter;
import com.example.match.filtering.filter.custom.FavouriteFilter;
import com.example.match.filtering.filter.custom.HeightFilter;
import com.example.match.filtering.filter.custom.PhotoFilter;
import com.example.match.filtering.query.Range;
import com.example.match.filtering.query.SearchDto;
import com.example.match.filtering.service.ProfileService;
import java.util.function.Predicate;
import org.assertj.core.util.VisibleForTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PredicateMaker {

    private ContactFilter contactFilter = new ContactFilter();
    private FavouriteFilter favouriteFilter = new FavouriteFilter();
    private PhotoFilter photoFilter = new PhotoFilter();

    @Autowired
    private ProfileService service;


    public Predicate<Profile> getCustomFilters(SearchDto query, String user) {

        Predicate<Profile> allPredicates = null;

        if (query.getFavourite() != null) {
            Predicate<Profile> predicate = query.getFavourite() ? favouriteFilter : favouriteFilter.negate();
            allPredicates = allPredicates == null ? predicate : allPredicates.and(predicate);
        }
        if (query.getInContact() != null) {
            Predicate<Profile> predicate = query.getInContact() ? contactFilter : contactFilter.negate();
            allPredicates = allPredicates == null ? predicate : allPredicates.and(predicate);
        }
        if (query.getHasPhoto() != null) {
            Predicate<Profile> predicate = query.getHasPhoto() ? photoFilter : photoFilter.negate();
            allPredicates = allPredicates == null ? predicate : allPredicates.and(predicate);
        }
        if (query.getAge() != null) {
            AgeFilter ageFilter = new AgeFilter(query.getAge().getLow().intValue(), query.getAge().getHigh().intValue());
            allPredicates = allPredicates == null ? ageFilter : allPredicates.and(ageFilter);
        }
        if (query.getCompatibility() != null) {
            Range range = query.getCompatibility();
            CompatibilityFilter compatibilityFilter = new CompatibilityFilter(range.getLow().intValue(), range.getHigh().intValue());
            allPredicates = allPredicates == null ? compatibilityFilter : allPredicates.and(compatibilityFilter);
        }
        if (query.getHeight() != null) {
            Range range = query.getHeight();
            HeightFilter heightFilter = new HeightFilter(range.getLow().intValue(), range.getHigh().intValue());
            allPredicates = allPredicates == null ? heightFilter : allPredicates.and(heightFilter);
        }
        if (query.getDistance() != null) {
            Range range = query.getDistance();
            DistanceFilter distanceFilter = new DistanceFilter(range.getLow(), range.getHigh(), getUser(user).getCity());
            allPredicates = allPredicates == null ? distanceFilter : allPredicates.and(distanceFilter);
        }
        return allPredicates;
    }


    @VisibleForTesting
    protected Profile getUser(String user) {
        return service.getProfileByName(user);
    }

}
