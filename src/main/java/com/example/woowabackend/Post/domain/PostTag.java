package com.example.woowabackend.Post.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

import javax.persistence.*;


import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class PostTag {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tag_id")
    @NotNull
    private Tag tag;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    @NotNull
    private Post post;

    @Builder
    public PostTag(Tag tag,Post post) {
        this.tag = tag;
        this.post = post;
    }

    public void TagSet(Tag tag){
        PostTag postTag = new PostTag();
        tag.getPostTags().add(this);
        this.setTag(tag);
    }

    public boolean match(String Name){
        return this.tag.getName().equals(Name);
    }


}
