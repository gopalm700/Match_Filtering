package com.example.match.filtering.query;

import com.example.match.filtering.query.validators.InRange;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class SearchDto {
    private Boolean hasPhoto;
    private Boolean inContact;
    private Boolean favourite;
    @InRange(low = 1, high = 99)
    private Range compatibility;
    @InRange(low = 18, high = 150)
    private Range age;
    @InRange(low = 135, high = 300)
    private Range height;
    @InRange(low = 30, high = 10000)
    private Range distance;
}
