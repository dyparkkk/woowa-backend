package com.example.woowabackend.Post.service;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.PostTag;
import com.example.woowabackend.Post.domain.Tag;
import com.example.woowabackend.Post.repository.PostLikeRepository;
import com.example.woowabackend.Post.repository.PostRepository;
import com.example.woowabackend.Post.repository.PostTagRepository;
import com.example.woowabackend.Post.repository.TagRepository;
import com.example.woowabackend.Post.web.dto.PostResponseDto;
import com.example.woowabackend.Post.web.dto.PostSaveRequestDto;
import com.example.woowabackend.Post.web.dto.PostUpdateRequestDto;
import com.example.woowabackend.Post.web.dto.TagSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostTagRepository postTagRepository;
    private final TagRepository tagRepository;
    private final PostTagService postTagService;

       @Transactional
       public Long save(PostSaveRequestDto requestDto,List<String> tagName) {
           Post post = requestDto.toEntity();
           postRepository.save(post);

           for (String name : tagName) {
               Tag tag = tagRepository.findByName(name);
               if (tag == null) {
                   Tag newHashTag = new Tag(name);
                   newHashTag.setName(name);
                   tag = tagRepository.save(newHashTag);

                   postTagRepository.save(new PostTag(tag,post));
               }
           }
           return post.getId();
       }
        @Transactional
        public Long update (Long id, PostUpdateRequestDto requestDto){
            Post post = postRepository.findById(id).orElseThrow(() -> new
                    IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

            post.update(requestDto.getTitle(), requestDto.getContent());
            return id;
        }

        public PostResponseDto findById (Long id){
            Post entity = postRepository.findById(id).orElseThrow(() -> new
                    IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
            return new PostResponseDto(entity);
        }

        @Transactional(readOnly = true)
        public List<PostResponseDto> findAllDesc () {
            return postRepository.findAllDesc().stream()
                    .map(PostResponseDto::new)
                    .collect(Collectors.toList());
        }


        @Transactional
        public Long delete (Long id){
            Post post = postRepository.findById(id).orElseThrow(() -> new
                    IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
            List<PostTag> postTags = post.getPostTags();
            for(PostTag eachPostTag : postTags){
                if(eachPostTag.getTag().getPostTags().size()==1) tagRepository.delete(eachPostTag.getTag());
                postTagRepository.delete(eachPostTag);
            }
            post.delete();
            return id;
        }

        //조회수
        public void updateView (Long id){
            Post post = postRepository.findById(id).orElseThrow(() -> new
                    IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
            post.increaseViewCnt();
            postRepository.save(post);
        }

    public Iterable<Post> findAllByTag (String tagName){
        return postRepository.findAll().stream()
                .filter(post -> post.Tag(tagName))
                .collect(Collectors.toList());
    }

    public List<Tag> findTags () {
        return postRepository.findTags();
    }


}

