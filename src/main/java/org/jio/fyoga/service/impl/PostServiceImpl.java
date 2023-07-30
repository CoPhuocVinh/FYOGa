package org.jio.fyoga.service.impl;

import org.jio.fyoga.entity.Post;
import org.jio.fyoga.repository.ClassRepository;
import org.jio.fyoga.repository.PostRepository;
import org.jio.fyoga.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository=postRepository;
    }
    @Override
    public <S extends Post> S save(S entity) {
        return postRepository.save(entity);
    }

    @Override
    public Post findById(Integer integer) {
        return this.postRepository.findById(integer).orElseThrow();
    }

    @Override
    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    @Override
    public void deleteAll() {
postRepository.deleteAll();
    }
}
