package yohi1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yohi1.service.FollowerService;
import yohi1.service.UserService;
import yohi1.service.impl.FollowerSerImpl;


import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false,updatable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Post> posts;

    @OneToOne ( cascade = CascadeType.ALL)
    private Follower follower;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Comment> comments;

    @OneToOne(mappedBy ="user",cascade = CascadeType.ALL)
    private Image image;





    public User(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
