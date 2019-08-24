package com.example.match.filtering.controller;

import com.example.match.filtering.domain.Profile;
import com.example.match.filtering.query.SearchDto;
import com.example.match.filtering.service.ProfileService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProfileController {

    private final static Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private ProfileService profileService;


    @GetMapping(value = "/profiles", produces = "application/json")
    public List<Profile> getProfiles(
        @RequestParam(defaultValue = "0", required = false, name = "page") int page,
        @RequestParam(defaultValue = "20", required = false, name = "size") int size,
        @RequestHeader(name = "user-id") String userName) {

        return profileService.getAllProfiles(page, size);
    }


    @PostMapping(value = "/filter", produces = "application/json", consumes = "application/json")
    public List<Profile> filterResult(
        @Valid @RequestBody SearchDto dto,
        @RequestParam(defaultValue = "0", required = false, name = "page") int page,
        @RequestParam(defaultValue = "20", required = false, name = "size") int size,
        @RequestHeader(name = "user-id") String userName) {
        return profileService.filteredProfile(dto, page, size, userName);
    }
}
