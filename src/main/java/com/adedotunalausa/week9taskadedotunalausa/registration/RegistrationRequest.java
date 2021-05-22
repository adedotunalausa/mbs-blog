package com.adedotunalausa.week9taskadedotunalausa.registration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {
    private final String firstname;
    private final String lastname;
    private final String gender;
    private final String address;
    private final String city;
    private final String state;
    private final String country;
    private final String password;
    private final String email;

}
