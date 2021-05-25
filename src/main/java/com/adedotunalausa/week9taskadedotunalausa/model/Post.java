package com.adedotunalausa.week9taskadedotunalausa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
@EqualsAndHashCode(callSuper = true)
public class Post extends AuditModel {

    @Id
    @GeneratedValue(generator = "post_generator")
    @SequenceGenerator(name = "post_generator",
            sequenceName = "post_sequence", initialValue = 1460)
    protected Long postId;

    @Column(name = "username")
    protected String username;

    @Column(name = "user_id")
    protected Long userId;

    protected String postTitle;

    protected String postContent;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, mappedBy = "postId")
    protected Set<Comment> comments;

    @OneToMany(targetEntity = PostLike.class, cascade = CascadeType.ALL, mappedBy = "postId")
    protected Set<PostLike> likes;

    public Post(String username, Long userId, String postTitle, String postContent) {
        this.username = username;
        this.userId = userId;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }
}
