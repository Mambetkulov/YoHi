package yohi1.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueID;
    private String title;
    private String description;
    private LocalDate createdAt;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ToString.Exclude
    @OneToOne(mappedBy ="post",cascade = CascadeType.ALL)
    private Image image;
    @ToString.Exclude
    @OneToMany( cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Comment> comments;


    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> likes;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }
}
