package com.adedotunalausa.mbsblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "favorite_posts")
public class FavouritePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    protected Post post;

    protected Long userId;

    public FavouritePost(Post post, Long userId) {
        this.post = post;
        this.userId = userId;
    }
}
