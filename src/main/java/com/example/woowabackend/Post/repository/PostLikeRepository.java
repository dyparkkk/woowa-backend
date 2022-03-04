package com.example.woowabackend.Post.repository;

import com.example.woowabackend.Post.domain.Post;
import com.example.woowabackend.Post.domain.PostLike;
import com.example.woowabackend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<Long> countByPost(Post post);

    // 한번 돌려봐... 나도 잘 모르겟어 ㅎㅎ;
    Optional<PostLike> findByMemberIdAndPostId(Long memberId, Long postId);

    // 규칙 ...
    Optional<PostLike> findByPostId(Long postId);
}
