package org.jio.fyoga.model;/*  Welcome to Jio word
    @author: Jio
    Date: 7/5/2023
    Time: 6:44 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class PaymentDTO implements Serializable {
    private String status;
    private String message;
    private String URL;

}
