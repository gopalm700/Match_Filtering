package com.example.match.filtering.controller;

import com.example.match.filtering.domain.Profile;
import com.example.match.filtering.query.SearchDto;
import com.example.match.filtering.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProfileService profileService;


    @Test
    public void testReturnResult() throws Exception {
        when(profileService.getAllProfiles(anyInt(), anyInt())).thenReturn(mockResult());
        MvcResult result = mvc.perform(get("/api/profiles")
            .contentType("application/json").header("user-id", "abc"))
            .andExpect(status().isOk()).andReturn();
        assertThat(result.getResponse().getContentAsString())
            .isEqualToIgnoringWhitespace(new ObjectMapper().writeValueAsString(mockResult()));
    }


    @Test
    public void testBadRequest() throws Exception {
        mvc.perform(get("/api/profiles").contentType("application/json"))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void testQueryParameter() throws Exception {
        when(profileService.getAllProfiles(anyInt(), anyInt())).thenReturn(mockResult());
        MvcResult result = mvc.perform(get("/api/profiles")
            .contentType("application/json").header("user-id", "code-name-god")
            .requestAttr("page", 0).requestAttr("size", 3))
            .andExpect(status().isOk()).andReturn();
        assertThat(result.getResponse().getContentAsString())
            .isEqualToIgnoringWhitespace(new ObjectMapper().writeValueAsString(mockResult()));
    }


    @Test
    public void testFilterResult() throws Exception {
        when(profileService.filteredProfile(any(SearchDto.class), anyInt(), anyInt(), anyString())).thenReturn(mockResult());
        MvcResult result = mvc.perform(post("/api/filter")
            .contentType("application/json").header("user-id", "code-name-god")
            .content(getQueryString())
            .param("page", "0").param("size", "3")
        )
            .andExpect(status().isOk()).andReturn();
        assertThat(result.getResponse().getContentAsString())
            .isEqualToIgnoringWhitespace(new ObjectMapper().writeValueAsString(mockResult()));
    }


    @Test
    public void shouldReturnBadRequestForInvalidAge() throws Exception {
        mvc.perform(post("/api/filter")
            .contentType("application/json").header("user-id", "code-name-god")
            .content(getInvalidAgeQueryString())
            .param("page", "0").param("size", "3")
        )
            .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldReturnBadRequestForInvalidPayload() throws Exception {
        mvc.perform(post("/api/filter")
            .contentType("application/json").header("user-id", "code-name-god")
            .content(getQueryString().replace("true","trui"))
            .param("page", "0").param("size", "3"))
            .andExpect(status().isBadRequest());
    }


    private String getQueryString() {
        return "{\"hasPhoto\":true,\"inContact\":true,\"favourite\":true,\"compatibility\":{\"low\":60,\"high\":70},\"age\":{\"low\":19,\"high\":40},\"height\":{\"low\":140,"
            + "\"high\":180},\"distance\":{\"low\":50,\"high\":150}}";
    }


    private String getInvalidAgeQueryString() {
        return "{\"hasPhoto\":true,\"inContact\":true,\"favourite\":true,\"compatibility\":{\"low\":60,\"high\":70},\"age\":{\"low\":17,\"high\":40},\"height\":{\"low\":140,"
            + "\"high\":180},\"distance\":{\"low\":50,\"high\":150}}";
    }


    private List<Profile> mockResult() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(Profile.builder().contactExchanged(1).favourite(true).displayName("John").mainPhoto("photouri").build());
        profiles.add(Profile.builder().contactExchanged(1).favourite(false).displayName("Sam").build());
        profiles.add(Profile.builder().contactExchanged(0).favourite(true).displayName("Bill").mainPhoto("photouri").build());
        return profiles;
    }

}
