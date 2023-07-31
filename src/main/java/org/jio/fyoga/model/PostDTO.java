package org.jio.fyoga.model;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostDTO {
    int postID;
    String title;
    String dessciption;
    int status;
    private byte[] img;
    String author;
    Date createDay;
}
