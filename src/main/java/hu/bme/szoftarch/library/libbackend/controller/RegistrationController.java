package hu.bme.szoftarch.library.libbackend.controller;

import hu.bme.szoftarch.library.libbackend.model.LibUser;
import hu.bme.szoftarch.library.libbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping
    public LibUser registerUser(@Valid @NotNull @RequestBody LibUser user) {
        return userService.createUser(user);
    }
}
