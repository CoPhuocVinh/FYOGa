package org.jio.fyoga.service.impl;

import org.jio.fyoga.entity.Post;
import org.jio.fyoga.repository.PostRepository;
import org.jio.fyoga.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Override
    public List<Post> findByStatus(int status) {
        return postRepository.findByStatus(status);
    }

    @Override
    public void deleteById(Integer integer) {
        postRepository.deleteById(integer);
    }

    @Override
    public void saveIMG(MultipartFile file, Post post) throws IOException {
        // Kiểm tra xem tệp có tồn tại không
        if (!file.isEmpty()) {
            // Lưu tệp vào trường data của đối tượng Content
            post.setImg(file.getBytes());

            // Cập nhật các thông tin khác của đối tượng Content

            // Lưu đối tượng Content vào cơ sở dữ liệu
            postRepository.save(post);
        }
    }

    @Override
    public byte[] getIMGById(int postID) {
        // Truy vấn cơ sở dữ liệu để lấy đối tượng Content dựa trên contentId
        Post post = postRepository.findById(postID).orElse(null);

        if (post != null) {
            // Kiểm tra xem đối tượng Content có dữ liệu hình PNG không
            if (post.getImg() != null) {
                return post.getImg();
            } else {
                // Xử lý trường hợp không có dữ liệu hình PNG
                throw new RuntimeException("No PNG data found for blog: " + postID);
            }
        } else {
            // Xử lý trường hợp không tìm thấy đối tượng Content với contentId tương ứng
            throw new RuntimeException("User not found for blog: " + postID);
        }
    }


}
