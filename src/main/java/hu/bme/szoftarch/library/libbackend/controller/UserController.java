package hu.bme.szoftarch.library.libbackend.controller;

import hu.bme.szoftarch.library.libbackend.dto.UserDTO;
import hu.bme.szoftarch.library.libbackend.model.LibUser;
import hu.bme.szoftarch.library.libbackend.service.UserService;
import hu.bme.szoftarch.library.libbackend.utils.DTOConverter;
import hu.bme.szoftarch.library.libbackend.utils.exceptions.LibraryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DTOConverter dtoConverter;

    @GetMapping("{id}")
    public UserDTO getUser(@PathVariable Long id) {
        LibUser user = userService.getUserById(id);
        return dtoConverter.toUserDTO(user);
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        List<LibUser> users = userService.getUsers();
        return dtoConverter.toUserDTOList(users);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) throws LibraryException {
        LibUser user = dtoConverter.toUser(userDTO);
        LibUser createdUser = userService.createUser(user);
        return dtoConverter.toUserDTO(createdUser);
    }

    @PutMapping("{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        LibUser user = dtoConverter.toUser(userDTO);
        LibUser updatedUser = userService.updateUser(id, user);
        return dtoConverter.toUserDTO(updatedUser);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
