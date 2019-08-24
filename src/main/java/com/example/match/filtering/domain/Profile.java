package com.example.match.filtering.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @JsonProperty("display_name")
    private String displayName;
    private Integer age;
    @JsonProperty("job_title")
    private String jobTitle;
    @JsonProperty("height_in_cm")
    private Integer heightInCm;
    private City city;
    @JsonProperty("main_photo")
    private String mainPhoto;
    @JsonProperty("compatibility_score")
    private Double compatibilityScore;
    @JsonProperty("contacts_exchanged")
    private Integer contactExchanged;
    private Boolean favourite;
    private String religion;


    public Profile copy() {
        return Profile.builder()
            .displayName(displayName)
            .age(age)
            .jobTitle(jobTitle)
            .heightInCm(heightInCm)
            .city(city.copy())
            .mainPhoto(mainPhoto)
            .compatibilityScore(compatibilityScore)
            .contactExchanged(contactExchanged)
            .favourite(favourite)
            .religion(religion).build();
    }


}


