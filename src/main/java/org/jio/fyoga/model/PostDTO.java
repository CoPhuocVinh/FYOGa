package org.jio.fyoga.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostDTO {
    int postID;
    String postName;
    String dessciption;
    int staffID;
}
