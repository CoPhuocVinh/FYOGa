package org.jio.fyoga.service;

import org.jio.fyoga.entity.Class;
import org.jio.fyoga.entity.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IPostService {
    <S extends Post> S save(S entity);

    Post findById(Integer integer);

    List<Post> findAll();

    void deleteAll();

    List<Post> findByStatus(int status);
    void deleteById(Integer integer);

    void saveIMG(MultipartFile file, Post post) throws IOException;

    byte[] getIMGById(int postID);
}
