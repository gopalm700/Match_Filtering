package com.example.match.filtering.engine.impl;

import com.example.match.filtering.domain.Profile;
import com.example.match.filtering.engine.ProfileEngine;
import com.example.match.filtering.filter.PredicateMaker;
import com.example.match.filtering.query.SearchDto;
import com.example.match.filtering.service.ProfileService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class SimpleProfileEngine implements ProfileEngine {

    @Autowired private ProfileService service;

    @Autowired private PredicateMaker predicateMaker;


    @Override public List<Profile> filterProfiles(SearchDto dto, int page, int size, String user) {
        List<Profile> profiles = getFilteredList(dto, user);


        int length = profiles.size();
        int start = page * size;
        int end = page * size + size - 1;
        end = end < length ? end : length - 1;
        if (start < length && end >= 0) {
            return IntStream.rangeClosed(start, end).mapToObj(
                index -> profiles.get(index)
            ).collect(Collectors.toList());
        }
        else {
            return new ArrayList<>();
        }
    }


    private List<Profile> getFilteredList(SearchDto dto, String user) {
        return service.getAllProfiles().stream().filter(
            predicateMaker.getCustomFilters(dto, user)).collect(Collectors.toList());
    }
}
