package org.jio.fyoga.repository;/*  Welcome to Jio word
    @author: Jio
    Date: 6/19/2023
    Time: 3:13 PM
    
    ProjectName: demoSpring01
    Jio: I wish you always happy with coding <3
*/


import org.jio.fyoga.entity.Role;
import org.jio.fyoga.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByRoleID(int id);


}
