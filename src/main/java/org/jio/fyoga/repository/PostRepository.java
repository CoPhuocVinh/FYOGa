package org.jio.fyoga.repository;


import org.jio.fyoga.entity.Class;
import org.jio.fyoga.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByStatus(int status);
}
