package org.jio.fyoga.controllers.admin;

import org.jio.fyoga.entity.Class;
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
    public String getBlogPosts(Model model) {
        List<Post> blogListON = postService.findByStatus(1);
        model.addAttribute("BLOG_ON", blogListON);

        List<Post> blogListOff = postService.findByStatus(0);
        model.addAttribute("BLOG_OFF", blogListOff);

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
        return "admin/page_blog"; // Assuming you have a template named "page_blog_edit" for editing a blog post
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

    @GetMapping("/reStatus")
    public String hoanTac(@RequestParam int postID) {
        Post blog = postService.findById(postID);
        if (blog != null) {
            blog.setStatus(1); // Set status to active (1)
            postService.save(blog);
        }
        return "redirect:/FYoGa/Login/ADMIN/post"; // Redirect back to the blog page
    }

    @GetMapping("/delete")
    public String deleteBlogPost(@RequestParam int postID) {
        Post blog = postService.findById(postID);
        if (blog != null) {
            blog.setStatus(0); // Set status to inactive (0)
            postService.save(blog);
        }
        return "redirect:/FYoGa/Login/ADMIN/post"; // Redirect back to the blog page
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


