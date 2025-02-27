package yohi1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "followers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> subscribers;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> subscribtion;

}
