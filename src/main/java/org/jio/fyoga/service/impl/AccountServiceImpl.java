package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 6/11/2023
    Time: 10:46 PM
    
    ProjectName: demoSpring
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Account;
import org.jio.fyoga.repository.AccountRepository;
import org.jio.fyoga.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public <S extends Account> S saveAndFlush(S entity) {
        return accountRepository.saveAndFlush(entity);
    }

    @Override
    public Account checkLogin(String email, String password) {
        Account account = accountRepository.findAccountByEmail(email);
        if ((account != null) && (account.getPassword().equals(password)) )

            return account;
        else
            return null;
    }

    @Override
    public Account findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    @Override
    public <S extends Account> S save(S entity) {
        return accountRepository.save(entity);
    }

    @Override
    public List<Account> findAccountByRole(int roleID) {
        return accountRepository.findAccountByRole_RoleID(roleID);
    }

    @Override
    public List<Account> findAccountByRole_RoleIDAndStatus(int roleID, int status) {
        return accountRepository.findAccountByRole_RoleIDAndStatus(roleID, status);
    }
}
