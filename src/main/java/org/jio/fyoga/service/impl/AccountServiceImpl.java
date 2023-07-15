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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Override
    public Account findById(Integer integer) {
        Account account = accountRepository.findById(integer).orElseThrow();
        return account;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void saveIMGAccount(MultipartFile file, Account account) throws IOException {
        // Kiểm tra xem tệp có tồn tại không
        if (!file.isEmpty()) {
            // Lưu tệp vào trường data của đối tượng Content
            account.setAvatar(file.getBytes());

            // Cập nhật các thông tin khác của đối tượng Content

            // Lưu đối tượng Content vào cơ sở dữ liệu
            accountRepository.save(account);
        }
    }

    @Override
    public byte[] getIMGById(int accountID) {
        // Truy vấn cơ sở dữ liệu để lấy đối tượng Content dựa trên contentId
        Account account = accountRepository.findById(accountID).orElse(null);

        if (account != null) {
            // Kiểm tra xem đối tượng Content có dữ liệu hình PNG không
            if (account.getAvatar() != null) {
                return account.getAvatar();
            } else {
                // Xử lý trường hợp không có dữ liệu hình PNG
                throw new RuntimeException("No PNG data found for course: " + accountID);
            }
        } else {
            // Xử lý trường hợp không tìm thấy đối tượng Content với contentId tương ứng
            throw new RuntimeException("User not found for course: " + accountID);
        }
    }
}
