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
@Table(name = "comment_likes")
@EqualsAndHashCode(callSuper = true)
public class CommentLike extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "liked_by")
    protected String likedBy;

    @Column(name = "comment_id")
    protected Long commentId;

    @Column(name = "is_liked")
    protected boolean isLiked;

    public CommentLike(String likedBy, Long commentId, boolean isLiked) {
        this.likedBy = likedBy;
        this.commentId = commentId;
        this.isLiked = isLiked;
    }
}
