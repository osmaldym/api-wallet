package com.osmy.wallet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osmy.wallet.dtos.UserDTO;
import com.osmy.wallet.entities.User;
import com.osmy.wallet.repositories.UserRepository;
import com.osmy.wallet.utils.Convertions;
import com.osmy.wallet.utils.Convertions.CastingError;
import com.osmy.wallet.utils.exceptions.NotFoundException;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    CastingError err = null;

    public UserDTO getUserById(Long id) throws Convertions.CastingError {
        User user = userRepo.findById(id).orElse(null);
        return this.convertToDTO(user);
    }

    public List<UserDTO> getUsers() throws Convertions.CastingError {
        Iterable<User> users = userRepo.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();

        for (User user : users) usersDTO.add(convertToDTO(user));

        if (err != null) throw this.err;
        return usersDTO;
    }

    public User saveUser(UserDTO userDTO) throws Convertions.CastingError {
        User user = this.convertToEntity(userDTO);
        return userRepo.save(user);
    }

    public User updateUser(Long id, UserDTO newData) throws Convertions.CastingError, NotFoundException {
        userRepo.findById(id).orElseThrow(() -> new NotFoundException("This user not exists"));
        User user = userRepo.findById(id).get();
        return userRepo.save((User) Convertions.updateObjIfPropertyNotNull(user, newData));
    }

    public User putUser(Long id, UserDTO newData) throws Convertions.CastingError {
        User user = new User();
        user.setId(id);
        return userRepo.save((User) Convertions.updateObjIfPropertyNotNull(user, newData));
    }

    @Transactional
    public User deleteUser(Long id) throws Convertions.CastingError {
        User user = new User();
        user.setId(userRepo.softDeleteById(id).longValue());
        return user;
    }

    // All conversions:

    private User convertToEntity(UserDTO userDTO) throws Convertions.CastingError {
        return (User) Convertions.toEntity(User.class, userDTO);
    }

    private UserDTO convertToDTO(User user) throws Convertions.CastingError {
        return (UserDTO) Convertions.toDTO(UserDTO.class, user);
    }
}
