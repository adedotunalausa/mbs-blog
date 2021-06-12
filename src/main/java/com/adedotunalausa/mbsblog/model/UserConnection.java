package com.adedotunalausa.mbsblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "connections")
public class UserConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne
    protected User sender;

    @ManyToOne
    protected User receiver;

    public UserConnection(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }
}
