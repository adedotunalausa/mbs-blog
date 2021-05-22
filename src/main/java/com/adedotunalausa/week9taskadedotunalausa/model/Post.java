package com.adedotunalausa.week9taskadedotunalausa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "posts")
@EqualsAndHashCode(callSuper = true)
public class Post extends AuditModel {

    @Id
    @GeneratedValue(generator = "post_generator")
    @SequenceGenerator(name = "post_generator",
            sequenceName = "post_sequence", initialValue = 5100)
    protected Long postId;

    protected Long userId;
    protected String postTitle;
    protected String postContent;


}
