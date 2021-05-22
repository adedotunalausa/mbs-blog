package com.adedotunalausa.week9taskadedotunalausa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AuditModel implements UserDetails {

    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(name = "user_generator",
            sequenceName = "user_sequence", initialValue = 5100)
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

    @Enumerated(EnumType.STRING)
    protected UserRole userRole;

    protected Boolean locked = false;
    protected Boolean enabled = false;

    @OneToMany(targetEntity = Post.class, cascade = CascadeType.ALL)
    protected List<Post> posts = new ArrayList<>();

    public User(String firstname, String lastname, String gender,
                String address, String city, String state, String country, String email,
                String password, UserRole userRole) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
