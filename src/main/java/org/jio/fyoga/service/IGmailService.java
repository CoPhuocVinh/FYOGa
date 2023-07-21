package org.jio.fyoga.service;/*  Welcome to Jio word
    @author: Jio
    Date: 7/20/2023
    Time: 5:12 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

public interface IGmailService {
    // Hàm tạo verify code ngẫu nhiên và thời gian hết hạn 30 phút
    String generateVerifyCode();

    // Hàm gửi email chứa verify code tới địa chỉ email của người dùng
    void sendVerificationEmail(String userEmail);

    // Hàm gửi lại verify code
    void resendVerificationEmail(String userEmail);

    // Hàm kiểm tra mã verify code có hiệu lực hay không
    boolean verifyCodeIsValid(String verifyCode);
}
