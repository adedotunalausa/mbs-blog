package com.adedotunalausa.week9taskadedotunalausa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    protected String username;

    protected String postTitle;

    protected String postContent;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
    protected List<Comment> comments = new ArrayList<>();

    public Post(String username, String postTitle, String postContent) {
        this.username = username;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }
}
