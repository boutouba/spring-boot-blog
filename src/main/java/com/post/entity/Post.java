package com.post.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    private User user;

    public Post() {}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }
}
