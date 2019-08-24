package com.example.match.filtering.service.impl;

import com.example.match.filtering.domain.Profile;
import com.example.match.filtering.engine.ProfileEngine;
import com.example.match.filtering.exception.UserNotFoundException;
import com.example.match.filtering.query.SearchDto;
import com.example.match.filtering.service.ProfileService;
import com.example.match.filtering.storage.ProfileStorage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired private ProfileStorage storage;
    @Autowired private ProfileEngine engine;


    @Override public List<Profile> getAllProfiles() {
        return storage.getUnModifiableProfiles();
    }


    @Override public List<Profile> getAllProfiles(int page, int size) {
        return storage.getUnModifiableProfiles(page, size);
    }

    @Override public Profile getProfileByName(String user) {
        return storage.getProfileByUserName(user).orElseThrow(UserNotFoundException::new);
    }

    @Override public List<Profile> filteredProfile(SearchDto dto, int page, int size, String user) {
        return engine.filterProfiles(dto, page, size, user);
    }
}
