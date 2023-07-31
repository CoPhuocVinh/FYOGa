package org.jio.fyoga.controllers.admin;

import org.jio.fyoga.entity.Post;
import org.jio.fyoga.model.PostDTO;
import org.jio.fyoga.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/FYoGa/Login/ADMIN/post")
@Controller
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping("")
    public String getAllPosts(Model model) {
        List<Post> posts = postService.findAll();
        List<PostDTO> postDTOs = posts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        model.addAttribute("posts", postDTOs);
        return "admin/page_list_blog";
    }

    private PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostID(post.getPostID());
        postDTO.setTitle(post.getTitle());
        postDTO.setImg(post.getImg());
        postDTO.setAuthor(post.getAuthor());
        postDTO.setCreateDay(post.getCreateDay());
        postDTO.setCreateDay(post.getCreateDay());

        return postDTO;
    }

    @GetMapping("/add")
    public String showAddBlogPostForm(Model model) {
        model.addAttribute("postDTO", new PostDTO());
        return "admin/page_blog";
    }

    @PostMapping("/create")
    public String createBlogPost(@ModelAttribute PostDTO postDTO,
                                 @RequestParam("thumbnail") MultipartFile thumbnailFile) throws IOException {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDessciption(postDTO.getDessciption());
        post.setImg(thumbnailFile.getBytes());
        post.setStatus(postDTO.getStatus());
        postService.save(post);

        return "redirect:/FYoga/Login/ADMIN/post";
    }


    //xử lý lấy ảnh
    @RequestMapping("/downloads-png")
    public ResponseEntity<?> downloadPngCourse(@RequestParam(defaultValue = "") int postID) {
        byte[] pngData = postService.getIMGById(postID);
        if (pngData != null) {
            ByteArrayResource resource = new ByteArrayResource(pngData);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=image.png")
                    .contentType(MediaType.IMAGE_PNG)
                    .body( resource);
        }
        // Xử lý trường hợp tệp tin không tồn tại
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/edit/{postID}")
    public String showEditBlogPostForm(@PathVariable int postID, Model model) {
        Post post = postService.findById(postID);
        if (post == null) {
            // Handle the case where the post with the given ID doesn't exist
            return "redirect:/FYoga/Login/ADMIN/post";
        }

        PostDTO postDTO = convertToDTO(post);
        model.addAttribute("postDTO", postDTO);
        return "admin/page_blog";
    }

    @PostMapping("/update")
    public String updateBlogPost(@ModelAttribute PostDTO postDTO,
                                 @RequestParam("thumbnail") MultipartFile thumbnailFile) throws IOException {
        Post post = postService.findById(postDTO.getPostID());
        if (post == null) {
            return "redirect:/FYoga/Login/ADMIN/post";
        }

        post.setTitle(postDTO.getTitle());
        post.setDessciption(postDTO.getDessciption());
        post.setImg(thumbnailFile.getBytes());
        post.setStatus(postDTO.getStatus());
        postService.save(post);

        return "redirect:/FYoga/Login/ADMIN/post";
    }

    @GetMapping("/delete")
    public String deleteBlogPost(@RequestParam int postID) {
        Post post= postService.findById(postID);
        if(post!= null){
            post.setStatus(0);
            postService.save(post);
        }


        return "redirect:/FYoga/Login/ADMIN/post";
    }

    @GetMapping("/deactivate/{postID}")
    public String deactivateBlogPost(@PathVariable int postID) {
        Post post = postService.findById(postID);
        if (post == null) {
            return "redirect:/FYoga/Login/ADMIN/post";
        }

        post.setStatus(0);
        postService.save(post);

        return "redirect:/FYoga/Login/ADMIN/post";
    }

}


