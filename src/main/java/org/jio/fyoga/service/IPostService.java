package org.jio.fyoga.service;

import org.jio.fyoga.entity.Post;

import java.util.List;

public interface IPostService {
    <S extends Post> S save(S entity);

    Post findById(Integer integer);

    List<Post> findAll();

    void deleteAll();
}
