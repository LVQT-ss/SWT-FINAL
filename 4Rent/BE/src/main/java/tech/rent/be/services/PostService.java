package tech.rent.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.rent.be.dto.PostRequestDTO;
import tech.rent.be.dto.PostResponseDTO;
import tech.rent.be.dto.ResourceDTO;
import tech.rent.be.entity.Post;
import tech.rent.be.entity.RealEstate;
import tech.rent.be.entity.Resource;
import tech.rent.be.entity.Users;
import tech.rent.be.enums.PostStatus;
import tech.rent.be.repository.PostRepository;
import tech.rent.be.repository.RealEstateRepository;
import tech.rent.be.repository.UsersRepository;
import tech.rent.be.utils.AccountUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RealEstateRepository realEstateRepository;
    @Autowired
    AccountUtils accountUtils;
    public Post createPost(PostRequestDTO postRequestDTO){
        Users users = accountUtils.getCurrentUser();
        RealEstate realEstate = realEstateRepository.findRealEstateById(postRequestDTO.getEstateId());
        Post post = new Post();
        post.setId(postRequestDTO.getId());
        post.setContent(postRequestDTO.getContent());
        post.setTitle(postRequestDTO.getTitle());
        post.setDiscount(postRequestDTO.getDiscount());
        post.setUsers(users);
        post.setRealEstate(realEstate);
        return postRepository.save(post);

    }
    public List<PostResponseDTO> getAllPosts() {
        List<Post> postList = postRepository.findAll();
        List<PostResponseDTO> postResponseDTOList = new ArrayList<>();
        for (Post post : postList) {
            PostResponseDTO postResponseDTO = new PostResponseDTO();
            // Map fields from post to postResponseDTO
            postResponseDTO.setId(post.getId());
            postResponseDTO.setContent(post.getContent());
            postResponseDTO.setTitle(post.getTitle());
            postResponseDTO.setDiscount(post.getDiscount());
            postResponseDTO.setRealEstate(post.getRealEstate());
            postResponseDTOList.add(postResponseDTO);
        }
        return postResponseDTOList;
    }
    public Post deletePost(long postId) {
       Post post = postRepository.findById(postId);
       post.setPostStatus(PostStatus.INACTIVE);
       return  postRepository.save(post);
    }

}

