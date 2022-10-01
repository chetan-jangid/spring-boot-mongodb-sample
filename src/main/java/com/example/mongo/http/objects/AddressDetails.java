package com.example.mongo.http.objects;

import lombok.Data;

@Data
public class AddressDetails {
    private String addressLine1;
    private String addressLine2;
    private String pinCode;
    private String state;
    private String country;
    private String city;
}
