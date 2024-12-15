package com.obsidi.yearbook.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.obsidi.yearbook.jpa.User;
import com.obsidi.yearbook.security.JwtService;
import com.obsidi.yearbook.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerTest {

  @Autowired private MockMvc mockMvc;

  // @MockitoBean
  @MockBean private UserService userService;

  @Autowired JwtService jwtService;

  @Autowired private ObjectMapper objectMapper;

  private User user;

  @BeforeEach
  public void setup() {
    user = new User();
    user.setUsername("johndoe");
    user.setEmailId("johndoe@example.com");
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setPassword("mypassword");
  }

  @Test
  public void sendInviteEmailTest() throws Exception {
    doNothing().when(userService).sendInviteEmail(user.getEmailId());
    mockMvc
        .perform(get("/user/invite/{emailId}", user.getEmailId()))
        .andExpect(status().isOk())
        .andReturn();
    verify(userService, times(1)).sendInviteEmail(user.getEmailId());
  }

  @Test
  @WithMockUser(username = "johndoe") // Simulate an authenticated user
  public void completeSignupTest() throws Exception {
    ObjectNode json = objectMapper.createObjectNode();
    json.put("password", "newpassword");

    doNothing().when(userService).completeSignup(anyString());

    mockMvc
        .perform(
            post("/user/signup/complete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(json)))
        .andExpect(status().isOk()) // Expect 200
        .andReturn();

    verify(userService, times(1)).completeSignup("newpassword");
  }

  @Test
  public void loginTest() throws Exception {
    when(userService.authenticate(any(User.class))).thenReturn(user);
    ObjectNode json = objectMapper.createObjectNode();
    json.put("username", user.getUsername());
    json.put("password", user.getPassword());
    mockMvc
        .perform(
            post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(json)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username", is(user.getUsername())))
        .andExpect(jsonPath("$.emailId", is(user.getEmailId())));
    verify(userService, times(1)).authenticate(any(User.class));
  }

  @Test
  public void sendResetPasswordEmailTest() throws Exception {
    // Mocking the service call
    doNothing().when(userService).sendResetPasswordEmail(user.getEmailId());

    // Perform the GET request
    mockMvc.perform(get("/user/reset/{emailId}", user.getEmailId())).andExpect(status().isOk());

    // Verify that the service method was called once
    verify(userService, times(1)).sendResetPasswordEmail(user.getEmailId());
  }

  @Test
  @WithMockUser(username = "johndoe") // Simulate an authenticated user
  public void resetPasswordTest() throws Exception {
    // Create a JSON object with the new password
    ObjectNode json = objectMapper.createObjectNode();
    json.put("password", "resetpassword");

    // Mocking the service call
    doNothing().when(userService).resetPassword(anyString());

    // Perform the POST request
    mockMvc
        .perform(
            post("/user/reset")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(json)))
        .andExpect(status().isOk());

    // Verify that the service method was called with the correct password
    verify(userService, times(1)).resetPassword("resetpassword");
  }

  @Test
  @WithMockUser(username = "johndoe") // Simulate an authenticated user
  public void updateUserProfileTest() throws Exception {
    ObjectNode profileJson = objectMapper.createObjectNode();
    profileJson.put("headline", "Updated Headline");
    profileJson.put("bio", "Updated Bio");
    when(userService.updateUserProfile(any())).thenReturn(user);
    mockMvc
        .perform(
            post("/user/update/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(profileJson.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username", is(user.getUsername())));
    verify(userService, times(1)).updateUserProfile(any());
  }

  @Test
  @WithMockUser(username = "johndoe") // Simulate an authenticated user
  public void deleteUserTest() throws Exception {
    doNothing().when(userService).deleteUser();
    mockMvc.perform(delete("/user/delete")).andExpect(status().isOk());
    verify(userService, times(1)).deleteUser();
  }
}
