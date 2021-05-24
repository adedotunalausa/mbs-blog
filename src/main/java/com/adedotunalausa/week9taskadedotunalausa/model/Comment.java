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
@Table(name = "comments")
@EqualsAndHashCode(callSuper = true)
public class Comment extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long commentId;

    @Column(name = "comment_by")
    protected String commentBy;

    @Column(name = "post_id")
    protected Long postId;

    @Column(name = "comment_body")
    protected String commentBody;

    public Comment(String commentBy, Long postId, String commentBody) {
        this.commentBy = commentBy;
        this.postId = postId;
        this.commentBody = commentBody;
    }
}
