package hu.bme.szoftarch.library.libbackend.controller;

import hu.bme.szoftarch.library.libbackend.dto.UserDTO;
import hu.bme.szoftarch.library.libbackend.model.LibUser;
import hu.bme.szoftarch.library.libbackend.service.UserService;
import hu.bme.szoftarch.library.libbackend.utils.DTOConverter;
import hu.bme.szoftarch.library.libbackend.utils.exceptions.IllegalDeleteRequestException;
import hu.bme.szoftarch.library.libbackend.utils.exceptions.IllegalUpdateRequestException;
import hu.bme.szoftarch.library.libbackend.utils.exceptions.LibraryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            LibUser user = dtoConverter.toUser(userDTO);
            LibUser updatedUser = userService.updateUser(id, user);
            return dtoConverter.toUserDTO(updatedUser);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new IllegalUpdateRequestException("Tried to update a field with a nonexistent ID");

        }
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new IllegalDeleteRequestException("Can not remove user, since there are books referencing it.");
        }
    }
}
