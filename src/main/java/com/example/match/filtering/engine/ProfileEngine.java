package com.example.match.filtering.engine;

import com.example.match.filtering.domain.Profile;
import com.example.match.filtering.query.SearchDto;
import java.util.List;

public interface ProfileEngine {
    List<Profile> filterProfiles(SearchDto dto,int page, int size, String user);
}
