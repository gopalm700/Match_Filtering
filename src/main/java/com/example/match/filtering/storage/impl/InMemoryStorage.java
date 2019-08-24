package com.example.match.filtering.storage.impl;

import com.example.match.filtering.domain.Matches;
import com.example.match.filtering.domain.Profile;
import com.example.match.filtering.storage.ProfileStorage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InMemoryStorage implements ProfileStorage {

    private List<Profile> profileList;

    private final static Logger logger = LoggerFactory.getLogger(InMemoryStorage.class);


    @PostConstruct
    public void setUp() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Matches> typeReference = new TypeReference<Matches>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/db/matches.json");

        Matches matches = mapper.readValue(inputStream, typeReference);
        profileList = Collections.unmodifiableList(matches.getMatches());
        logger.info("size :: " + matches.getMatches().size());
    }


    @Override public List<Profile> getUnModifiableProfiles(int page, int size) {
        return IntStream.rangeClosed(page * size, (page * size) + size - 1).mapToObj(
            index -> profileList.get(index)
        ).collect(Collectors.toList());
    }


    @Override public List<Profile> getUnModifiableProfiles() {
        return profileList;
    }


    @Override public Optional<Profile> getProfileByUserName(String user) {
        return profileList.stream().filter(e -> e.getDisplayName().equals(user)).findFirst();
    }
}
