package com.example.mongo.http.objects;

import lombok.Data;

@Data
public class UserDetails {
    private String id;
    private String firstName;
    private String lastName;
    private AddressDetails address;
}
