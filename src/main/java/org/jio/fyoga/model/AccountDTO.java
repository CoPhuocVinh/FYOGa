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

    private int AccountID;

    private String Email;
    private String Password;
    private int Status;

    private String FullName;

    private String Phone;

    private String Avatar;
    private MultipartFile AvatarFile; // lưu hình

    private String Gender;

    private Date AcceptedDate;

    private int RoleID;

    private Boolean isEdit = false;
}
