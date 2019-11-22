package hu.bme.szoftarch.library.libbackend.service;

import hu.bme.szoftarch.library.libbackend.model.LibUser;
import hu.bme.szoftarch.library.libbackend.model.Role;
import hu.bme.szoftarch.library.libbackend.model.enums.RoleType;
import hu.bme.szoftarch.library.libbackend.repository.RoleRepository;
import hu.bme.szoftarch.library.libbackend.repository.UserRepository;
import hu.bme.szoftarch.library.libbackend.utils.exceptions.LibraryException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(rollbackFor = LibraryException.class)
    public LibUser createUser(LibUser user) throws LibraryException {

        Role userRole = roleRepository.findByName(RoleType.USER).orElse(null);
        if (userRole == null)
            throw new LibraryException("USER role doesn't exist");

        user.setRoles(new ArrayList<Role>());
        user.addRole(userRole);
        user.setEnabled(true);

        return userRepository.saveAndFlush(user);
    }

    public LibUser getUserById(Long id){
        return userRepository.getOne(id);
    }

    public LibUser getUserByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<LibUser> getUsers(){
        return userRepository.findAll();
    }

    public LibUser updateUser(Long id, LibUser user){
        LibUser existingUser = userRepository.findById(id).orElse(new LibUser());
        BeanUtils.copyProperties(user, existingUser);
        return userRepository.saveAndFlush(existingUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
