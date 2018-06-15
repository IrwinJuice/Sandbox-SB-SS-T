package org.springframework.gsspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.gsspringboot.model.Role;
import org.springframework.gsspringboot.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Long id){
        return roleRepository.getOne(id);
    }
}
