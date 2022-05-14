package com.ndex.clonemate.domain.likes.model;

import com.ndex.clonemate.domain.todos.model.Todo;
import com.ndex.clonemate.domain.user.repository.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Like(Todo todo, User user) {
        this.todo = todo;
        this.user = user;
    }
}
