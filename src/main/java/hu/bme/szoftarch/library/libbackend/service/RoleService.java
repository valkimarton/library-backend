package hu.bme.szoftarch.library.libbackend.service;

import hu.bme.szoftarch.library.libbackend.model.Role;
import hu.bme.szoftarch.library.libbackend.model.enums.RoleType;
import hu.bme.szoftarch.library.libbackend.repository.RoleRepository;
import hu.bme.szoftarch.library.libbackend.utils.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role createRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Transactional
    public Role getRoleById(Long id) { return roleRepository.getOne(id); }

    @Transactional
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public Role updateRole(Long id, Role role) {
        Role existingRole = roleRepository.findById(id).orElse(new Role());
        NullAwareBeanUtils.copyNonNullProperties(role, existingRole);
        return roleRepository.saveAndFlush(existingRole);
    }

    @Transactional
    public void deleteRole(Long id) { roleRepository.deleteById(id); }

    @Transactional
    public Role findByRoleName(RoleType roleName) {
        return roleRepository.findByName(roleName).orElse(null);
    }

}
