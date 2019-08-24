package com.example.match.filtering.storage;

import com.example.match.filtering.domain.Profile;
import java.util.List;
import java.util.Optional;

public interface ProfileStorage {
    List<Profile> getUnModifiableProfiles(int page, int size);
    List<Profile> getUnModifiableProfiles();
    Optional<Profile> getProfileByUserName(String user);
}
