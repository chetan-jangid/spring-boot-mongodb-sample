package com.example.mongo.service;

import com.example.mongo.http.objects.UserDetails;

import java.util.List;

public interface UserService {
    List<UserDetails> getUsers();
    void save(UserDetails userDetails);
    void delete(String id);
}
