package tech.rent.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.rent.be.dto.PostRequestDTO;
import tech.rent.be.dto.PostResponseDTO;
import tech.rent.be.dto.UserDTO;
import tech.rent.be.entity.Post;
import tech.rent.be.repository.PostRepository;
import tech.rent.be.services.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@SecurityRequirement(name = "api")
@CrossOrigin("*")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @PostMapping
    public ResponseEntity post(@RequestBody PostRequestDTO postRequestDTO){
        Post post = postService.createPost(postRequestDTO);
        return  ResponseEntity.ok(post);
    }

    @GetMapping("/show")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        List<PostResponseDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    @PutMapping("/delete/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable Long postId) {
        Post post = postService.deletePost(postId);
        return ResponseEntity.ok(post);
    }
}