package com.example.match.filtering.engine.impl;

import com.example.match.filtering.domain.City;
import com.example.match.filtering.domain.Profile;
import com.example.match.filtering.engine.ProfileEngine;
import com.example.match.filtering.filter.PredicateMaker;
import com.example.match.filtering.query.Range;
import com.example.match.filtering.query.SearchDto;
import com.example.match.filtering.service.ProfileService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleProfileEngineTest {

    private ProfileEngine engine;

    private ProfileService service = Mockito.mock(ProfileService.class);

    @Before
    public void setUp() {
        Mockito.reset(service);
        engine = new SimpleProfileEngine(service, new PredicateMaker(){
            @Override protected Profile getUser(String user) {
                return Profile.builder().city(City.builder().lat(52.412811).lon(-1.778197).build()).build();
            }
        });
    }

    @Test
    public void shouldGetFirstRecordByQuery() {
        SearchDto query = SearchDto.builder().favourite(true).hasPhoto(true).inContact(true).build();
        List<Profile> profiles = getMockProfile();
        when(service.getAllProfiles()).thenReturn(profiles);
        List<Profile> filteredProfile = engine.filterProfiles(query, 0, 1, "");
        assertThat(filteredProfile).isEqualTo(Arrays.asList(profiles.get(0)));
    }


    @Test
    public void shouldGetSecondRecordByQuery() {
        SearchDto query = SearchDto.builder().favourite(false).hasPhoto(false).inContact(true).build();
        List<Profile> profiles = getMockProfile();
        when(service.getAllProfiles()).thenReturn(profiles);
        List<Profile> filteredProfile = engine.filterProfiles(query, 0, 1, "");
        assertThat(filteredProfile).isEqualTo(Arrays.asList(profiles.get(1)));
    }


    @Test
    public void shouldGetThirdRecordByQuery() {
        SearchDto query = SearchDto.builder().favourite(true).hasPhoto(true).inContact(false).build();
        List<Profile> profiles = getMockProfile();
        when(service.getAllProfiles()).thenReturn(profiles);
        List<Profile> filteredProfile = engine.filterProfiles(query, 0, 3, "");
        assertThat(filteredProfile).isEqualTo(Arrays.asList(profiles.get(2)));
    }

    @Test
    public void shouldFilterByHeightQuery() {
        SearchDto query = SearchDto.builder().height(new Range(130, 170)).build();
        List<Profile> profiles = getMockProfile();
        when(service.getAllProfiles()).thenReturn(profiles);
        List<Profile> filteredProfile = engine.filterProfiles(query, 0, 3, "");
        assertThat(filteredProfile).isEqualTo(Arrays.asList(profiles.get(3), profiles.get(4)));
    }

    @Test
    public void shouldGetOneWithDistance(){
        SearchDto query = SearchDto.builder().height(new Range(130, 170))
            .age(new Range(30,31)).distance(new Range(31,180)).build();
        List<Profile> profiles = getMockProfile();
        when(service.getAllProfiles()).thenReturn(profiles);
        List<Profile> filteredProfile = engine.filterProfiles(query, 0, 3, "");
        assertThat(filteredProfile).isEqualTo(Arrays.asList(profiles.get(4)));
    }

    private List<Profile> getMockProfile() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(Profile.builder().contactExchanged(1).favourite(true).displayName("John").mainPhoto("photouri").build());
        profiles.add(Profile.builder().contactExchanged(1).favourite(false).displayName("Sam").build());
        profiles.add(Profile.builder().contactExchanged(0).favourite(true).displayName("Bill").mainPhoto("photouri").build());
        profiles.add(Profile.builder().contactExchanged(1).favourite(true).displayName("Tom").mainPhoto("photouri").heightInCm(150).build());
        profiles.add(rangeQueries());
        return profiles;
    }


    private Profile rangeQueries() {
       return Profile.builder().contactExchanged(1)
           .favourite(true).displayName("Rose")
           .mainPhoto("photouri").heightInCm(150)
           .age(30).
        compatibilityScore(0.76)
           .city(City.builder().lon(-1.548567).lat(53.801277).build()).build();
    }
}
