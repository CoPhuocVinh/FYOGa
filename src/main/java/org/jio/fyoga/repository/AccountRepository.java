package org.jio.fyoga.repository;/*  Welcome to Jio word
    @author: Jio
    Date: 6/11/2023
    Time: 10:33 PM
    
    ProjectName: demoSpring
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountByEmail(String email);

    List<Account> findAccountByRole_RoleID (int roleID);

    List<Account> findAccountByRole_RoleIDAndStatus (int roleID, int status);
}
