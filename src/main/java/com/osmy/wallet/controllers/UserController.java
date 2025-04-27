package com.osmy.wallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osmy.wallet.dtos.UserDTO;
import com.osmy.wallet.services.UserService;
import com.osmy.wallet.utils.Convertions.CastingError;
import com.osmy.wallet.utils.exceptions.NotFoundException;
import com.osmy.wallet.utils.exceptions.UnauthorizedException;
import com.osmy.wallet.utils.responses.Ok;


@RestController
@RequestMapping("/api/v1/")
public class UserController implements ErrorController {
    @Autowired
    UserService service;

    @GetMapping(value = {"/users", "/users/"})
    public Ok getUsers() throws UnauthorizedException {
        try {
            return new Ok(service.getUsers());
        } catch (CastingError e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public Ok getUsers(@PathVariable("id") Long id) throws UnauthorizedException {
        try {
            return new Ok(service.getUserById(id));
        } catch (CastingError e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    @PostMapping(value = {"/users", "/users/"})
    public Ok newUser(@RequestBody UserDTO data) throws UnauthorizedException {
        try {            
            return new Ok((Object) service.saveUser(data));
        } catch (CastingError e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    @PatchMapping("/users/{id}")
    public Ok editUser(@PathVariable("id") Long id, @RequestBody UserDTO data) throws UnauthorizedException, NotFoundException {
        try {
            return new Ok(service.updateUser(id, data));
        } catch (CastingError e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    @PutMapping(value = {"/users", "/users/", "/users/{id}"})
    public Ok putUser(@PathVariable(name="id", required=false) Long id, @RequestBody UserDTO data) throws UnauthorizedException {
        try {
            return new Ok(service.putUser(id, data));
        } catch (CastingError e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public Ok deleteUser(@PathVariable("id") Long id) throws UnauthorizedException {
        try {
            return new Ok(service.deleteUser(id));
        } catch (CastingError e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }
}
