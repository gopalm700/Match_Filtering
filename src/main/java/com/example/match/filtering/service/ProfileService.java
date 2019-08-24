package com.example.match.filtering.service;

import com.example.match.filtering.domain.Profile;
import com.example.match.filtering.query.SearchDto;
import java.util.List;

public interface ProfileService {
    List<Profile> getAllProfiles();
    List<Profile> getAllProfiles(int page, int size);
    Profile getProfileByName(String user);
    List<Profile> filteredProfile(SearchDto dto, int page, int size, String user);
}
