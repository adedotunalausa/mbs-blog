package com.adedotunalausa.week9taskadedotunalausa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class User extends AuditModel {

    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(name = "user_generator",
            sequenceName = "user_sequence", initialValue = 5100)
    protected Long userId;

    @NotBlank
    @Size(max = 20)
    protected String username;

    protected String firstname;

    protected String lastname;

    protected String gender;

    protected String address;

    protected String city;

    protected String state;

    protected String country;

    @NotBlank
    @Size(max = 50)
    @Email
    protected String email;

    @NotBlank
    @Size(max = 120)
    protected String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(targetEntity = Post.class, cascade = CascadeType.ALL, mappedBy = "userId")
    protected List<Post> posts;

    @OneToMany(targetEntity = FavouritePost.class, cascade = CascadeType.ALL, mappedBy = "userId")
    protected List<FavouritePost> favouritePosts;

    @Column(name = "deactivation_date")
    protected String deactivationDate;

    @Column(name = "is_deactivated")
    protected Boolean isDeactivated;

    public User(String username, String firstname, String lastname,
                String gender, String email, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

}
