package com.adedotunalausa.week9taskadedotunalausa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post_likes")
@EqualsAndHashCode(callSuper = true)
public class PostLike extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "liked_by")
    protected String likedBy;

    @Column(name = "post_id")
    protected Long postId;

    @Column(name = "is_liked")
    protected boolean isLiked;

    public PostLike(String likedBy, Long postId, boolean isLiked) {
        this.likedBy = likedBy;
        this.postId = postId;
        this.isLiked = isLiked;
    }
}
