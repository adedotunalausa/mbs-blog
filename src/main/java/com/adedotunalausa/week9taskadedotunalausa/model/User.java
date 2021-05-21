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
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AuditModel{

    @Id
    @GeneratedValue(generator = "post_generator")
    @SequenceGenerator(name = "post_generator",
            sequenceName = "post_sequence", initialValue = 5100)
    protected Long userId;

    protected String firstname;
    protected String lastname;
    protected String gender;
    protected String address;
    protected String city;
    protected String state;
    protected String country;
    protected String email;
    protected String password;

    @OneToMany(targetEntity = Post.class, cascade = CascadeType.ALL)
    protected List<Post> posts = new ArrayList<>();
}
