package com.example.mongo.service;

import com.example.mongo.exception.UserNotPresentException;
import com.example.mongo.http.objects.AddressDetails;
import com.example.mongo.http.objects.UserDetails;
import com.example.mongo.model.Address;
import com.example.mongo.model.User;
import com.example.mongo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDetails> getUsers() {
        return userRepository.findAll().stream().map(this::toUserDetails).toList();
    }

    private UserDetails toUserDetails(User user) {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(user.getId());
        userDetails.setFirstName(user.getFirstName());
        userDetails.setLastName(user.getLastName());
        userDetails.setAddress(toAddressDetails(user.getAddress()));
        return userDetails;
    }

    private AddressDetails toAddressDetails(Address address) {
        AddressDetails addressDetails = new AddressDetails();
        addressDetails.setAddressLine1(address.getAddressLine1());
        addressDetails.setAddressLine2(address.getAddressLine2());
        addressDetails.setCity(address.getCity());
        addressDetails.setCountry(address.getCountry());
        addressDetails.setState(address.getState());
        addressDetails.setPinCode(address.getPinCode());
        return addressDetails;
    }

    @Override
    public void save(UserDetails userDetails) {
        userRepository.save(buildUser(userDetails));
    }

    @Override
    public void delete(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotPresentException("User not present");
        }
        user.ifPresent(userRepository::delete);
    }

    private User buildUser(UserDetails userDetails) {
        return new User(null, userDetails.getFirstName(), userDetails.getLastName(), toAddress(userDetails.getAddress()));
    }

    private Address toAddress(AddressDetails addressDetails) {
        return new Address(addressDetails.getAddressLine1(), addressDetails.getAddressLine2(), addressDetails.getPinCode(),
                addressDetails.getState(), addressDetails.getCountry(), addressDetails.getCity());
    }
}
