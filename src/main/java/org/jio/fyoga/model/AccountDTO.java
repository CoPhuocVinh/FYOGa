package org.jio.fyoga.model;/*  Welcome to Jio word
    @author: Jio
    Date: 6/11/2023
    Time: 10:19 PM
    
    ProjectName: demoSpring
    Jio: I wish you always happy with coding <3
*/

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountDTO {

    private int accountID;

    private String email;
    private String password;
    private int status;

    private String fullName;

    private String phone;

    private byte[] avatar;
    private MultipartFile AvatarFile; // lưu hình

    private String gender;

    private Date acceptedDate;

    private int roleID;

    private Boolean isEdit = false;
    private String newPassword;

}
