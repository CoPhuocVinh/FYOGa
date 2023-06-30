package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 6/19/2023
    Time: 3:15 PM
    
    ProjectName: demoSpring01
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Role;
import org.jio.fyoga.repository.RoleRepository;
import org.jio.fyoga.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByRoleID(int id) {

        return roleRepository.findRoleByRoleID(id);
    }
}
