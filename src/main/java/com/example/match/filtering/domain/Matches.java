package com.example.match.filtering.domain;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Matches {
    private ArrayList<Profile> matches = new ArrayList<>();
}
