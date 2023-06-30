package org.jio.fyoga.model;/*  Welcome to Jio word
    @author: Jio
    Date: 6/11/2023
    Time: 10:31 PM
    
    ProjectName: demoSpring
    Jio: I wish you always happy with coding <3
*/

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleDTO {
    private int roleID;

    private String roleName;

    private Boolean isEdit = false;

}
