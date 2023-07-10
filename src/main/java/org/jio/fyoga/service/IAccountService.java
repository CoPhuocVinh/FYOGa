package org.jio.fyoga.service;/*  Welcome to Jio word
    @author: Jio
    Date: 6/11/2023
    Time: 10:46 PM
    
    ProjectName: demoSpring
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Account;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IAccountService {

    <S extends Account> S saveAndFlush(S entity);

    Account checkLogin(String email, String password);


    Account findAccountByEmail(String email);

    <S extends Account> S save(S entity);


    List<Account> findAccountByRole(int roleID);

    // lưu ảnh
    void saveIMGAccount(MultipartFile file, Account account) throws IOException;

    // lấy ảnh ra
    byte[] getIMGById(int accountID);
}
