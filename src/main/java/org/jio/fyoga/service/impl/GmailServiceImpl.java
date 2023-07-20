package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 7/20/2023
    Time: 5:12 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.jio.fyoga.service.IGmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
@Service

public class GmailServiceImpl implements IGmailService {
    @Autowired
    private JavaMailSender mailSender;

    private Map<String, LocalDateTime> verifyCodeMap = new HashMap<>();

    // Hàm tạo verify code ngẫu nhiên và thời gian hết hạn 30 phút
    @Override
    public String generateVerifyCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 6; // Độ dài của verify code
        StringBuilder verifyCode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            verifyCode.append(characters.charAt(random.nextInt(characters.length())));
        }

        return verifyCode.toString();
    }

    // Hàm gửi email chứa verify code tới địa chỉ email của người dùng
    @Override
    public void sendVerificationEmail(String userEmail) {
        String verifyCode = generateVerifyCode();

        // Lưu verifyCode và thời gian hết hạn vào map
        verifyCodeMap.put(verifyCode, LocalDateTime.now().plusMinutes(5));

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(userEmail);
            helper.setSubject("Xác thực tài khoản");
            helper.setText("Mã xác thực của bạn là: " + verifyCode);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Hàm gửi lại verify code
    @Override
    public void resendVerificationEmail(String userEmail) {
        // Tạo verify code mới
        String newVerifyCode = generateVerifyCode();

        // Lưu verify code mới và thời gian hết hạn mới vào map
        verifyCodeMap.put(newVerifyCode, LocalDateTime.now().plusMinutes(1));

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(userEmail);
            helper.setSubject("Xác thực tài khoản - Gửi lại");
            helper.setText("Mã xác thực mới của bạn là: " + newVerifyCode);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Hàm kiểm tra mã verify code có hiệu lực hay không
    @Override
    public boolean verifyCodeIsValid(String verifyCode) {
        LocalDateTime expirationTime = verifyCodeMap.get(verifyCode);
        if (expirationTime == null || expirationTime.isBefore(LocalDateTime.now())) {
            // Mã không hợp lệ hoặc đã hết hạn
            return false;
        }

        // Mã hợp lệ
        return true;
    }
}
