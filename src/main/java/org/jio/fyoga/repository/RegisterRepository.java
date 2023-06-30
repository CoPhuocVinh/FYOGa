package org.jio.fyoga.repository;/*  Welcome to Jio word
    @author: Jio
    Date: 6/30/2023
    Time: 11:38 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository  extends JpaRepository<Register, Integer> {

}
