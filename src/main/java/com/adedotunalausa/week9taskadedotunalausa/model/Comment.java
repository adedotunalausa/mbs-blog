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

    @ManyToOne
    protected User user;

    @Column(name = "postId")
    protected Long postId;

    @Column(name = "commentBody")
    protected String commentBody;

    public Comment(User user, Long postId, String commentBody) {
        this.user = user;
        this.postId = postId;
        this.commentBody = commentBody;
    }
}
